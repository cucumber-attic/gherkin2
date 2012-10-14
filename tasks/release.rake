namespace :release do
  desc 'Upload all packages and tag git'
  task :ALL => ['gems:sanity', 'ikvm:check', :mvn_deploy_jar, :push_native_gems, :push_npm_package, 'ikvm:push', :release, 'api:doc', :post_release]

  desc 'Push all gems to rubygems.org'
  task :push_native_gems do
    Dir.chdir('release') do
      Dir['*.gem'].each do |gem_file|
        sh("gem push #{gem_file}")
      end
    end
  end

  task :post_release => :ikvm do
    puts "\n\n****** Manually close and release at https://oss.sonatype.org/index.html#stagingRepositories ******\n\n"
  end

  desc 'Push jar to central Maven repo'
  task :mvn_deploy_jar do
    Dir.chdir('java') do
      sh("mvn clean source:jar javadoc:jar deploy")
    end
  end
  
  desc 'Push npm package to http://npmjs.org/'
  task :push_npm_package do
    en_min_js = File.expand_path(File.dirname(__FILE__) + '/../js/lib/gherkin/lexer/en.min.js')
    raise "No #{en_min_js}. Did you set GHERKIN_JS=true in your shell?" unless File.file?(en_min_js)

    Dir.chdir('js') do
      sh("npm publish")
    end
  end
end