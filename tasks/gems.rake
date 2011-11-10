namespace :gems do
  task :win do
    unless File.directory?(File.expand_path('~/.rake-compiler'))
      STDERR.puts "[ERROR] You must install MinGW rubies to build gherkin gems for Windows. See README.rdoc"
      exit(1)
    end
    # rvm and mingw ruby versions have to match to avoid errors
    sh "rvm 1.8.7-p352@cucumber rake cross compile RUBY_CC_VERSION=1.8.7"
    sh "rake cross compile RUBY_CC_VERSION=1.9.3"
    # This will copy the .so files to the proper place
    sh "rake -t cross compile RUBY_CC_VERSION=1.8.7:1.9.3"
  end

  desc 'Prepare JRuby binares'
  task :jruby => [:jar] do
    begin
      # Need to move the Gemfile.lock out of the way because the latest json gems
      # don't exist in identical versions: https://github.com/flori/json/pull/95
      # native gem is 1.6.0 and java gem is 1.6.0.1
      mv "Gemfile.lock", "Gemfile.lock.hack"
      sh "rvm jruby@cucumber exec rspec spec"
    ensure
      mv "Gemfile.lock.hack", "Gemfile.lock"
    end
  end

  desc 'Prepare IronRuby binaries'
  task :ironruby => [:jruby, 'ikvm:dll', 'ikvm:copy_ikvm_dlls']

  task :sanity do
    raise "The jruby gem looks too small" if File.stat("release/gherkin-#{GHERKIN_VERSION}-java.gem").size < 1000000
  end

  desc "Prepare binaries for all gems"
  task :prepare => [
    :clean,
    :spec,
    :win,
    :jruby,
    :ironruby
  ]

end
