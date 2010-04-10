namespace :gems do
  desc "Build MRI/C POSIX gem"
  task :posix => :release_dir  do
    sh "rake clean spec gemspec build"
    mv "pkg/gherkin-#{GHERKIN_VERSION}.gem", 'release'
  end

  [:mswin32, :mingw32].each do |win_compiler|
    desc "Build MRI/C precompiled #{win_compiler} gem"
    task win_compiler do
      sh "rake cross compile RUBY_CC_VERSION=1.8.6"
      sh "rake gemspec build PLATFORM=i386-#{win_compiler}"
      mv "pkg/gherkin-#{GHERKIN_VERSION}-x86-#{win_compiler}.gem", 'release'
    end
  end

  desc 'Build JRuby gem'
  task :jruby => :release_dir do
    sh "rvm jruby rake spec gemspec build PLATFORM=java"
    mv "pkg/gherkin-#{GHERKIN_VERSION}-java.gem", 'release'
  end

  task :release_dir do
    mkdir 'release' unless File.directory?('release')
  end

  task :clean_release_dir do
    rm_rf 'release' if File.directory?('release')
  end
end

task :gems => ['gems:clean_release_dir', 'gems:posix', 'gems:mswin32', 'gems:mingw32', 'gems:jruby']