namespace :gems do
  task :win do
    unless File.directory?(File.expand_path('~/.rake-compiler'))
      STDERR.puts "[ERROR] You must install MinGW rubies to build gherkin gems for Windows. See README.md"
      exit(1)
    end
    # rvm and mingw ruby versions have to match to avoid errors
    rbenv('1.9.3-p448') do
      sh "bundle exec rake cross compile RUBY_CC_VERSION=1.9.3"
    end
    rbenv('2.0.0-p247') do
      sh "bundle exec rake cross compile RUBY_CC_VERSION=2.0.0"
    end
    # This will copy the .so files to the proper place
    sh "bundle exec rake -t cross compile RUBY_CC_VERSION=1.9.3:2.0.0"
  end

  desc 'Prepare JRuby binares'
  task :jruby => [:jar] do
    rbenv('jruby-1.7.4') do
      sh "bundle exec rspec spec"
    end
  end

  task :sanity do
    raise "The jruby gem looks too small" if File.stat("release/gherkin-#{GHERKIN_VERSION}-java.gem").size < 1000000
  end

  desc "Prepare binaries for all gems"
  task :prepare => [
    :clean,
    :spec,
    :win,
    :jruby
  ]

  def rbenv(version)
    old_version = ENV['RBENV_VERSION']
    yield
    ENV['RBENV_VERSION'] = version
  end

end
