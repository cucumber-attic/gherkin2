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
  def self.report(&block)
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

task :bench => ["bench:gen", "bench:stats", "bench:cucumber", "bench:tt", "bench:rb_gherkin"]

namespace :bench do
  desc "Generate ENV['NUMBER'] Features with random content"
  task :gen => :clean do
    generator = RandomFeatureGenerator.new(ENV['NUMBER'].to_i)
    generator.generate    
  end

  desc "Benchmark Cucumber AST building from the features in tasks/bench/generated"
  task :cucumber do
    require 'cucumber'
    require 'logger'
    require 'benchmark'
    
    step_mother = Cucumber::StepMother.new
    logger = Logger.new(STDOUT)
    logger.level = Logger::INFO
    step_mother.log = logger
    Benchmark.bm do |x|
      x.report do
        step_mother.load_plain_text_features(Dir[GENERATED_FEATURES + "/**/*feature"])
      end
    end
  end
  
  desc "Benchmark the Treetop parser with the features in tasks/bench/generated"
  task :tt do
    require 'tt/en_parser'

    parser = Cucumber::Parser::I18n::EnParser.new 
    BenchmarkHelper.report do |feature|
      res = parser.parse(File.read(feature))
      raise "Parsing error encountered in #{feature}" unless res
    end      
  end

  desc "Benchmark the Ruby Gherkin parser with the features in tasks/bench/generated"
  task :rb_gherkin do
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

  desc "Remove all generated features in tasks/bench/generated"
  task :clean do
    rm_f FileList[GENERATED_FEATURES + "/**/*feature"]
  end
end
