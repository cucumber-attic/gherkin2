namespace :gems do
  desc "Build MRI/C POSIX gem"
  task :posix => :release_dir  do
    sh "rake clean spec"
    sh "rake gemspec build"
    mv "pkg/gherkin-#{GHERKIN_VERSION}.gem", 'release'
  end

  [:mswin32, :mingw32].each do |win_compiler|
    desc "Build MRI/C precompiled #{win_compiler} gem"
    task win_compiler do
      # Bootstrap with:
      #
      #   rake-compiler cross-ruby VERSION=1.9.1-p243
      #   rake-compiler cross-ruby VERSION=1.8.6-p114
      #
      sh "rake cross compile RUBY_CC_VERSION=1.8.6:1.9.1"
      sh "rake gemspec build PLATFORM=i386-#{win_compiler}"
      mv "pkg/gherkin-#{GHERKIN_VERSION}-x86-#{win_compiler}.gem", 'release'
    end
  end

  desc 'Build JRuby gem'
  task :jruby => :release_dir do
    sh "rvm jruby rake spec"
    sh "rvm jruby rake gemspec build PLATFORM=java"
    mv "pkg/gherkin-#{GHERKIN_VERSION}-java.gem", 'release'
  end

  desc 'Build IronRuby gem'
  task :ironruby => [:jruby, 'ikvm:dll', 'ikvm:copy_ikvm_dlls'] do
    sh "rake gemspec build PLATFORM=universal-dotnet"
    mv "pkg/gherkin-#{GHERKIN_VERSION}-universal-dotnet.gem", 'release'
  end

  task :release_dir do
    mkdir 'release' unless File.directory?('release')
  end

  task :clean_release_dir do
    rm_rf 'release' if File.directory?('release')
  end
end

task :gems => ['gems:clean_release_dir', 'gems:posix', 'gems:mswin32', 'gems:mingw32', 'gems:jruby', 'gems:ironruby']