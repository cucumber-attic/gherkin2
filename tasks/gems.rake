namespace :gems do
  desc "Build MRI/C POSIX gem"
  task :posix => :release_dir  do
    sh "rake clean spec"
    sh "rake build"
    mv "pkg/gherkin-#{Gherkin::VERSION}.gem", 'release'
  end

  desc "Build MRI/C precompiled gems for Windows (both mswin32 and mingw32)"
  task :mriwin do
    unless File.directory?(File.expand_path('~/.rake-compiler'))
      STDERR.puts <<-EOM

You must install Windows rubies to ~/.rake-compiler with:

 rake-compiler cross-ruby VERSION=1.8.6-p287
 # (Later 1.9.1 patch levels don't compile on mingw) 
 rake-compiler cross-ruby VERSION=1.9.1-p243
EOM
      exit(1)
    end
    # rvm and mingw ruby versions have to match to avoid errors
    sh "rvm 1.8.6@cucumber rake cross compile RUBY_CC_VERSION=1.8.6"
    sh "rvm 1.9.1@cucumber rake cross compile RUBY_CC_VERSION=1.9.1"
    # This will copy the .so files to the proper place
    sh "rake cross compile RUBY_CC_VERSION=1.8.6:1.9.1"
    
    [:mswin32, :mingw32].each do |win_compiler|
      sh "GEM_PLATFORM=i386-#{win_compiler} gem build gherkin.gemspec"
      mv "gherkin-#{Gherkin::VERSION}-x86-#{win_compiler}.gem", 'release'
    end
  end

  desc 'Build JRuby gem'
  task :jruby => [:release_dir, :jar] do
    sh "rvm jruby@cucumber -S rspec spec"
    sh "GEM_PLATFORM=java gem build gherkin.gemspec"
    raise "The jruby gem looks too small" if File.stat("pkg/gherkin-#{Gherkin::VERSION}-java.gem").size < 1000000
    mv "gherkin-#{Gherkin::VERSION}-java.gem", 'release'
  end

  desc 'Build IronRuby gem'
  task :ironruby => [:jruby, 'ikvm:dll', 'ikvm:copy_ikvm_dlls'] do
    sh "GEM_PLATFORM=universal-dotnet gem build.gherkin.gemspec"
    mv "gherkin-#{Gherkin::VERSION}-universal-dotnet.gem", 'release'
  end

  task :release_dir do
    mkdir 'release' unless File.directory?('release')
  end

  task :clean_release_dir do
    rm_rf 'release' if File.directory?('release')
  end
end

desc "Build gems for all platforms"
task :gems => ['gems:clean_release_dir', 'gems:posix', 'gems:mriwin', 'gems:jruby', 'gems:ironruby']