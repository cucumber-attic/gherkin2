$LOAD_PATH << File.expand_path(File.dirname(__FILE__) + "/lib")
require 'faker'

class NullListener
  def method_missing(*args)
  end
end

catch_phrase = lambda { Faker::Company.catch_phrase }
bs = lambda { Faker::Company.bs.capitalize }
sentence = lambda { Faker::Lorem.sentence }

desc "Generate ENV['NUMBER'] random Features"
task :gen do
  require 'feature_builder'
  
  ENV['NUMBER'].to_i.times do 
    name = catch_phrase.call
    feature = FeatureBuilder.new(name) do |f|
      num_scenarios = [*1..10][rand(9)]
      num_scenarios.times do
        f.scenario(bs.call) do |steps|
          num_steps = [*3..10][rand(7)]
          num_steps.times do
            steps.step(sentence.call)
          end
        end
      end
    end
    File.open("./features/generated/#{name.downcase.gsub(/[\s\-\/]/, '_')}.feature", "w+") do |file|
      file << feature.to_s
    end
  end
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

task :bench_tt => :stats do
  require 'tt/en_parser'
  require 'benchmark'
 
  Benchmark.bm do |x|
    x.report do
      parser = Cucumber::Parser::I18n::EnParser.new
      Dir["features/generated/**/*feature"].each do |path|
        res = parser.parse(File.read(path))
        raise "Parsing error" unless res
      end
    end
  end
end

task :bench_gherkin => :stats do
  require 'gherkin'
  require 'benchmark'
    
  listener = NullListener.new
  Benchmark.bm do |x|
    x.report do
      Dir["features/generated/**/*feature"].each do |path|
        parser = Gherkin::Feature.new('en', listener)
        parser.scan(File.read(path))
      end
    end
  end 
end

task :stats do
  ["Feature", "Scenario", "Given"].each do |kw|
    sh "grep #{kw} features/generated/* | wc -l"
  end
end
