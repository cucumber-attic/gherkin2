# encoding: utf-8
require 'rbconfig'
require 'rubygems'
require 'rake'
require 'rake/clean'
require 'yaml'

config = YAML.load_file('VERSION.yml')
GHERKIN_VERSION = "#{config[:major]}.#{config[:minor]}.#{config[:patch]}"

begin
  require 'jeweler'
  Jeweler::Tasks.new do |gem|
    gem.name = "gherkin"
    gem.summary = %Q{Fast Gherkin lexer/parser}
    gem.description = %Q{A fast Gherkin lexer/parser based on the Ragel State Machine Compiler.}
    gem.email = "cukes@googlegroups.com"
    gem.homepage = "http://github.com/aslakhellesoy/gherkin"
    gem.authors = ["Mike Sassak", "Gregory Hnatiuk", "Aslak Hellesøy"]
    gem.executables = ["gherkin"]
    gem.add_dependency "trollop", "~> 1.16.2"
    gem.add_development_dependency 'rspec', '~> 2.0.0.beta.11'
    gem.add_development_dependency "cucumber", "~> 0.8.1"
    gem.add_development_dependency "rake-compiler", "~> 0.7.0" unless defined?(JRUBY_VERSION)

    gem.files -= FileList['ikvm/**/*']
    gem.files -= FileList['java/**/*']
    case ENV['PLATFORM']
    when 'java'
      gem.platform = 'java'
      gem.files += FileList['lib/gherkin.jar']
      gem.extensions = []
    when 'i386-mswin32', 'i386-mingw32'
      gem.platform = ENV['PLATFORM']
      gem.files += FileList['lib/*/*.so']
      gem.extensions = []
    when 'universal-dotnet'
      gem.platform = ENV['PLATFORM']
      gem.files += FileList['lib/*.dll']
      gem.extensions = []
    else
      gem.files += FileList['lib/gherkin/rb_lexer/*.rb']
      gem.files += FileList['ext/**/*.c']
      gem.extensions = FileList['ext/**/extconf.rb']
    end
    
    # gem is a Gem::Specification... see http://www.rubygems.org/read/chapter/20 for additional settings
  end
  Jeweler::GemcutterTasks.new
rescue LoadError
  warn "Jeweler (or a dependency) not available. Install it with: sudo gem install jeweler"
end

Dir['tasks/**/*.rake'].each { |rake| load File.expand_path(rake) }

task :default  => [:spec, :cucumber]
task :spec     => defined?(JRUBY_VERSION) ? :jar : :compile
task :cucumber => defined?(JRUBY_VERSION) ? :jar : :compile