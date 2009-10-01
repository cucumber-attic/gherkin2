# encoding: utf-8
require 'rubygems'
require 'rake'
require 'rake/clean'

begin
  require 'jeweler'
  Jeweler::Tasks.new do |gem|
    gem.name = "gherkin"
    gem.summary = %Q{Fast Gherkin parser}
    gem.description = %Q{A fast Gherkin parser in Ragel}
    gem.email = "cukes@googlegroups.com"
    gem.homepage = "http://github.com/aslakhellesoy/gherkin"
    gem.authors = ["Mike Sassak", "Gregory Hnatiuk", "Aslak Hellesøy"]
    gem.add_development_dependency "rspec"
    gem.add_development_dependency "cucumber"
    
    # Jeweler only includes files in git by default. Add the generated ones.
    gem.files += FileList['lib/gherkin/parser/*.rb']
    
    # gem is a Gem::Specification... see http://www.rubygems.org/read/chapter/20 for additional settings
  end
  Jeweler::GemcutterTasks.new
rescue LoadError
  puts "Jeweler (or a dependency) not available. Install it with: sudo gem install jeweler"
end

Dir['tasks/**/*.rake'].each { |rake| load rake }

task :default => [:spec, :cucumber]
