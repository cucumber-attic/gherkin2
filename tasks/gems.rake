namespace :gems do
  desc "Build MRI/C POSIX gem"
  task :posix => :release_dir  do
    sh "rake clean spec"
    sh "rake build"
    mv "pkg/gherkin-#{Gherkin::VERSION}.gem", 'release'
  end

  [:mswin32, :mingw32].each do |win_compiler|
    desc "Build MRI/C precompiled #{win_compiler} gem"
    task win_compiler do
      # Bootstrap with:
      #
      #   rake-compiler cross-ruby VERSION=1.8.6-p114
      #   rake-compiler cross-ruby VERSION=1.9.1-p243
      #   (rake-compiler cross-ruby VERSION=1.9.2-p0) - bundler 1.0.0 not working with 1.9.2? "Can't convert Pathname to String" error
      #
      sh "rake cross compile RUBY_CC_VERSION=1.8.6:1.9.1"
      sh "rake build PLATFORM=i386-#{win_compiler}"
      mv "pkg/gherkin-#{Gherkin::VERSION}-x86-#{win_compiler}.gem", 'release'
    end
  end

  desc 'Build JRuby gem'
  task :jruby => :release_dir do
    sh "rvm jruby rake spec"
    sh "rvm jruby rake build PLATFORM=java"
    raise "The jruby gem looks too small" if File.stat("pkg/gherkin-#{Gherkin::VERSION}-java.gem").size < 1000000
    mv "pkg/gherkin-#{Gherkin::VERSION}-java.gem", 'release'
  end

  desc 'Build IronRuby gem'
  task :ironruby => [:jruby, 'ikvm:dll', 'ikvm:copy_ikvm_dlls'] do
    sh "rake gemspec PLATFORM=universal-dotnet"
    mv "pkg/gherkin-#{Gherkin::VERSION}-universal-dotnet.gem", 'release'
  end

  task :release_dir do
    mkdir 'release' unless File.directory?('release')
  end

  task :clean_release_dir do
    rm_rf 'release' if File.directory?('release')
  end
end

desc "Build gems for all platforms"
task :gems => ['gems:clean_release_dir', 'gems:posix', 'gems:mswin32', 'gems:mingw32', 'gems:jruby', 'gems:ironruby']