# encoding: utf-8
require 'rubygems'
require 'rake'

begin
  require 'jeweler'
  Jeweler::Tasks.new do |gem|
    gem.name = "gherkin"
    gem.summary = %Q{Fast Gherkin parser}
    gem.description = %Q{A fast Gherkin parser in Ragel}
    gem.email = "aslak.hellesoy@gmail.com"
    gem.homepage = "http://github.com/aslakhellesoy/gherkin"
    gem.authors = ["Aslak Hellesøy"]
    gem.add_development_dependency "rspec"
    # gem is a Gem::Specification... see http://www.rubygems.org/read/chapter/20 for additional settings
  end
  Jeweler::GemcutterTasks.new
rescue LoadError
  puts "Jeweler (or a dependency) not available. Install it with: sudo gem install jeweler"
end

require 'spec/rake/spectask'
Spec::Rake::SpecTask.new(:spec) do |spec|
  spec.libs << 'lib' << 'spec'
  spec.spec_opts << '--color'
  spec.spec_files = FileList['spec/**/*_spec.rb']
end

Spec::Rake::SpecTask.new(:rcov) do |spec|
  spec.libs << 'lib' << 'spec'
  spec.spec_opts << '--color'
  spec.pattern = 'spec/**/*_spec.rb'
  spec.rcov = true
end

task :spec => :check_dependencies

task :default => :spec
task :spec => "ragel:gen"

require 'rake/rdoctask'
Rake::RDocTask.new do |rdoc|
  if File.exist?('VERSION')
    version = File.read('VERSION')
  else
    version = ""
  end

  rdoc.rdoc_dir = 'rdoc'
  rdoc.title = "gherkin #{version}"
  rdoc.rdoc_files.include('README*')
  rdoc.rdoc_files.include('lib/**/*.rb')
end

namespace :ragel do
  desc "Generate Ruby from the Ragel rule files"
  task :gen do
    Dir["lib/gherkin/parser/*.rl"].each do |rl|
      unless rl =~ /_common\.rl$/
        code = rl[0..-4]
        sh "ragel -R #{rl} -o #{code}" 
      end
    end
  end
  
  desc "Generate a dot file of the Ragel state machine"
  task :dot do
    Dir["lib/gherkin/parser/*rl"].each do |path|
      sh "ragel -V #{path} -o #{File.basename(path, '.rl')}.dot" unless path =~ /_common\.rl$/
    end
  end

  desc "Generate a png diagram of the Ragel state machine"
  task :png => :dot do
    Dir["*dot"].each do |path|
      sh "dot -Tpng #{path} > #{File.basename(path, '.dot')}.png" unless path =~ /_common\.rl$/
    end
  end
end

