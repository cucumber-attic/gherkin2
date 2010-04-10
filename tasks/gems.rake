namespace :gems do
  desc 'Release JRuby gem'
  task :jruby => :release_dir do
    sh "rvm jruby rake clean spec gemspec build PLATFORM=java"
    mv "pkg/gherkin-#{GHERKIN_VERSION}-java.gem", 'release'
  end

  desc "Release MRI/C POSIX gem"
  task :posix => :release_dir  do
    sh "rvm 1.8.7 rake clean spec gemspec build"
    mv "pkg/gherkin-#{GHERKIN_VERSION}.gem", 'release'
  end

  [:mswin32, :mingw32].each do |win_compiler|
    desc "Release MRI/C precompiled #{win_compiler} gem"
    task win_compiler => :posix do
      sh "rvm 1.8.7 rake cross compile gemspec build PLATFORM=i386-#{win_compiler} RUBY_CC_VERSION=1.8.6"
      mv "pkg/gherkin-#{GHERKIN_VERSION}-x86-#{win_compiler}.gem", 'release'
    end
  end

  task :release_dir do
    mkdir 'release' unless File.directory?('release')
  end

  task :clean_release_dir do
    rm_rf 'release' if File.directory?('release')
  end
end

task :gems => ['gems:clean_release_dir', 'gems:jruby', 'gems:posix', 'gems:mswin32', 'gems:mingw32']