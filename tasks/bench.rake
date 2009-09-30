%w{/../lib /bench}.each do |l| 
  $LOAD_PATH << File.expand_path(File.dirname(__FILE__) + l)
end

GENERATED_FEATURES = File.expand_path(File.dirname(__FILE__) + "/bench/generated")

namespace :bench do
  desc "Generate ENV['NUMBER'] random Features"
  task :gen do
    require 'faker'
    require 'feature_builder'

    catch_phrase = lambda { Faker::Company.catch_phrase }
    bs = lambda { Faker::Company.bs.capitalize }
    sentence = lambda { Faker::Lorem.sentence }
  
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

  desc "Benchmark the Treetop parser with the features in tasks/bench/generated"
  task :tt => :stats do
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

  desc "Benchmark the Gherkin parser with the features in tasks/bench/generated"
  task :gherkin => :stats do
    require 'gherkin'
    require 'null_listener'
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

  desc "Show basic statistics about the features in tasks/bench/generated"
  task :stats do
    ["Feature", "Scenario", "Given"].each do |kw|
      sh "grep #{kw} #{GENERATED_FEATURES}/* | wc -l" # Yuck. 
    end
  end

  task :clean do
    puts "Delete all the files in GENERATED_FEATURES"
  end
end
