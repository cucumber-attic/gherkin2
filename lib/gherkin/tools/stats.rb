require 'gherkin'
require 'gherkin/tools/files'
require 'gherkin/tools/stats_listener'

module Gherkin
  module Tools
    class Stats < Files
      def run
        formatter = StatsFormatter.new
        each do |f|
          parser = Gherkin::Parser::Parser.new(formatter, true)
          parser.parse(IO.read(f), f, 0)
        end
        puts "Features:  #{listener.features}"
        puts "Scenarios: #{listener.scenarios}"
        puts "Steps:     #{listener.steps}"
      end
    end
  end
end
