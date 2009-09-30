%w{/../lib /bench}.each do |l| 
  $LOAD_PATH << File.expand_path(File.dirname(__FILE__) + l)
end

GENERATED_FEATURES = File.expand_path(File.dirname(__FILE__) + "/bench/generated")

class RandomFeatureGenerator
  def initialize(number)
    require 'faker'
    require 'feature_builder'
    
    @number = number
  end
  
  def generate
    @number.times do
      name = catch_phrase
      feature = FeatureBuilder.new(name) do |f|
        num_scenarios = rand_in(1..10)
        num_scenarios.times do
          f.scenario(bs) do |steps|
            num_steps = rand_in(3..10)
            num_steps.times do
              steps.step(sentence)
            end
          end
        end
      end      
      write feature.to_s, name
    end    
  end
  
  def write(content, name)
    File.open(GENERATED_FEATURES + "/#{name.downcase.gsub(/[\s\-\/]/, '_')}.feature", "w+") do |file|
      file << content
    end
  end
  
  def rand_in(range)
    ary = range.to_a
    ary[rand(ary.length - 1)]
  end
  
  def catch_phrase
    Faker::Company.catch_phrase
  end
  
  def bs
    Faker::Company.bs.capitalize
  end
  
  def sentence
    Faker::Lorem.sentence
  end
end

module BenchmarkHelper
  extend self
  
  def report(&block)
    require 'benchmark'
    
    Benchmark.bm do |x|
      x.report do
        Dir[GENERATED_FEATURES + "/**/*feature"].each do |path|
          block.call(path)
        end
      end
    end
  end
end

namespace :bench do
  desc "Generate ENV['NUMBER'] Features with random content"
  task :gen do
    generator = RandomFeatureGenerator.new(ENV['NUMBER'].to_i)
    generator.generate    
  end

  task :prof_tt do
    require 'tt/en_parser'
    require 'ruby-prof'
   
    parser = Cucumber::Parser::I18n::EnParser.new 
    result = RubyProf.profile do
      Dir["features/generated/**/*feature"].each do |path|
        res = parser.parse(File.read(path))
        raise "Parsing error" unless res
      end
    end
    printer = RubyProf::FlatPrinter.new(result)
    printer.print(STDOUT, 0)
  end

  task :prof_gherkin do
    require 'gherkin'
    require 'ruby-prof'

    listener = NullListener.new   
    result = RubyProf.profile do
      Dir["features/generated/**/*feature"].each do |path|
        parser = Gherkin::Feature.new('en', listener)
        parser.scan(File.read(path))
      end
    end
    printer = RubyProf::FlatPrinter.new(result)
    printer.print(STDOUT, 0)
  end

  desc "Benchmark the Treetop parser with the features in tasks/bench/generated"
  task :tt => :stats do
    require 'tt/en_parser'

    parser = Cucumber::Parser::I18n::EnParser.new 
    BenchmarkHelper.report do |feature|
      res = parser.parse(File.read(feature))
      raise "Parsing error encountered in #{feature}" unless res
    end      
  end

  desc "Benchmark the Gherkin parser with the features in tasks/bench/generated"
  task :gherkin => :stats do
    require 'gherkin'
    require 'null_listener'

    listener = NullListener.new
    BenchmarkHelper.report do |feature|
      parser = Gherkin::Feature.new('en', listener)
      parser.scan(File.read(feature))
    end 
  end

  desc "Show basic statistics about the features in tasks/bench/generated"
  task :stats do
    ["Feature", "Scenario", "Given"].each do |kw|
      sh "grep #{kw} #{GENERATED_FEATURES}/* | wc -l"
    end
  end

  task :clean do
    puts "Delete all the files in GENERATED_FEATURES"
  end
end
