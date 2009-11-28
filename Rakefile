# encoding: utf-8
require 'rbconfig'
require 'rubygems'
require 'rake'
require 'rake/clean'

WINDOWS = Config::CONFIG['host_os'] =~ /mswin|mingw/

begin
  require 'jeweler'
  Jeweler::Tasks.new do |gem|
    gem.name = "gherkin"
    gem.summary = %Q{Fast Gherkin lexer}
    gem.description = %Q{A fast Gherkin lexer in Ragel}
    gem.email = "cukes@googlegroups.com"
    gem.homepage = "http://github.com/aslakhellesoy/gherkin"
    gem.authors = ["Mike Sassak", "Gregory Hnatiuk", "Aslak Hellesøy"]
    gem.executables = ["gherkin"]
    gem.add_development_dependency "rspec", "1.2.9"
    gem.add_development_dependency "cucumber", "0.4.4"
    gem.add_development_dependency "rake-compiler", "0.6.0" unless defined?(JRUBY_VERSION)
    
    # Jeweler only includes files in git by default. Add the generated ones.
    gem.files += FileList['lib/gherkin/rb_lexer/*.rb']

    if(defined?(JRUBY_VERSION))
      gem.platform = Gem::Platform::CURRENT
      gem.files += FileList['lib/gherkin.jar']
      gem.extensions = []
    elsif(WINDOWS)
      gem.platform = Gem::Platform::CURRENT
      gem.files += FileList['lib/*.dll']
      gem.extensions = []
    else
      gem.files += FileList['ext/**/*.c']
      gem.extensions = FileList['ext/**/extconf.rb']
    end
    
    # gem is a Gem::Specification... see http://www.rubygems.org/read/chapter/20 for additional settings
  end
  Jeweler::GemcutterTasks.new
rescue LoadError
  puts "Jeweler (or a dependency) not available. Install it with: sudo gem install jeweler"
end

Dir['tasks/**/*.rake'].each { |rake| load rake }

task :default => [:jar, :compile, :spec, :cucumber]
