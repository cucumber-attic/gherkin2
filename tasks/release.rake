namespace :release do
  desc 'Upload all packages and tag git'
  task :ALL => ['gems:sanity', :ikvm, :push_jar, :push_native_gems, :push_npm_package, :release, :post_release]

  desc 'Push all gems to rubygems.org (gemcutter)'
  task :push_native_gems do
    Dir.chdir('release') do
      Dir['*.gem'].each do |gem_file|
        sh("gem push #{gem_file}")
      end
    end
  end

  task :post_release => :ikvm do
    puts "\n\n****** Manually upload gherkin-#{GHERKIN_VERSION}.dll to http://github.com/cucumber/gherkin/downloads ******\n\n"
  end

  desc 'Push jar to cukes.info Maven repo'
  task :push_jar do
    Dir.chdir('java') do
      sh("mvn -Dmaven.wagon.provider.http=httpclient deploy")
    end
  end

  desc 'Push npm package to http://npmjs.org/'
  task :push_npm_package do
    Dir.chdir('js') do
      sh("npm publish")
    end
  end
end