unless ENV['RUBY_CC_VERSION'] || defined?(JRUBY_VERSION)
  require 'yard'
  require 'yard/rake/yardoc_task'

  SITE_DIR = File.expand_path(File.dirname(__FILE__) + '/../../cucumber.github.com')
  API_DIR  = File.join(SITE_DIR, 'api', 'gherkin', GHERKIN_VERSION)

  namespace :api do
    file :dir do
      raise "You need to git clone git@github.com:cucumber/cucumber.github.com.git #{SITE_DIR}" unless File.directory?(SITE_DIR)
      mkdir_p API_DIR
    end

    YARD::Templates::Engine.register_template_path(File.expand_path(File.join(File.dirname(__FILE__), 'yard')))
    YARD::Rake::YardocTask.new(:yard) do |yard|
      dir = File.join(API_DIR, 'ruby')
      mkdir_p dir
      yard.options = ["--out", dir]
    end
    task :yard => :dir

    task :mvn_javadoc => :dir do
      Dir.chdir('java') do
        sh("mvn javadoc:javadoc")
        dir = File.join(API_DIR, 'java')
        mv 'target/site/apidocs', dir
      end
    end

    task :doc => [:mvn_javadoc, :yard]
  end
end