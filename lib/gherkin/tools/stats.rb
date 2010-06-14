require 'gherkin'
require 'gherkin/tools/files'
require 'gherkin/tools/stats_listener'

module Gherkin
  module Tools
    class Stats < Files
      def run
        listener = StatsListener.new
        each do |f|
          parser = Gherkin::Parser::Parser.new(listener, true)
          lexer = Gherkin::I18nLexer.new(parser)
          lexer.scan(IO.read(f), f, 0)
        end
        puts "Features:  #{listener.features}"
        puts "Scenarios: #{listener.scenarios}"
        puts "Steps:     #{listener.steps}"
      end
    end
  end
end
