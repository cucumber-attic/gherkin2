%w{/../lib /bench}.each do |l| 
  $LOAD_PATH << File.expand_path(File.dirname(__FILE__) + l)
end

require 'benchmark'

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

class Benchmarker
  def initialize
    @features = Dir[GENERATED_FEATURES + "/**/*feature"]
  end
  
  def report(parser)
    Benchmark.bm do |x|
      x.report("#{parser}:") { send :"run_#{parser}" }
    end
  end
  
  def report_all 
    Benchmark.bmbm do |x|
      x.report("cucumber:") { run_cucumber }
      x.report("tt:") { run_tt }
      x.report("rb_gherkin:") { run_rb_gherkin }
    end
  end
  
  def run_cucumber
    require 'cucumber'
    require 'logger'
    step_mother = Cucumber::StepMother.new
    logger = Logger.new(STDOUT)
    logger.level = Logger::INFO
    step_mother.log = logger
    step_mother.load_plain_text_features(@features)
  end
  
  def run_tt
    require 'tt/en_parser'
    parser = Cuke::Parser::I18n::EnParser.new 
    @features.each do |feature|
      res = parser.parse(IO.read(feature))
      raise "Parsing error encountered in #{feature}" unless res
    end
  end
  
  def run_rb_gherkin    
    require 'gherkin'
    require 'null_listener'
    listener = NullListener.new
    @features.each do |feature|
      parser = Gherkin::Feature.new('en', listener)
      parser.scan(File.read(feature))
    end
  end
end

desc "Generate 500 random features and benchmark Cucumber, Treetop and Gherkin with them"
task :bench => ["bench:clean", "bench:gen"] do
  benchmarker = Benchmarker.new
  benchmarker.report_all
end

namespace :bench do
  desc "Generate [number] features with random content, or 500 features if number is not provided"
  task :gen, :number do |t, args|
    args.with_defaults(:number => 500)
    generator = RandomFeatureGenerator.new(args.number.to_i)
    generator.generate    
  end

  desc "Benchmark Cucumber AST building from the features in tasks/bench/generated"
  task :cucumber do
    benchmarker = Benchmarker.new
    benchmarker.report("cucumber")
  end
  
  desc "Benchmark the Treetop parser with the features in tasks/bench/generated"
  task :tt do
    benchmarker = Benchmarker.new
    benchmarker.report("tt")
  end

  desc "Benchmark the Ruby Gherkin parser with the features in tasks/bench/generated"
  task :rb_gherkin do
    benchmarker = Benchmarker.new
    benchmarker.report("rb_gherkin")
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
