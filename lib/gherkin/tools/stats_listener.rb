# encoding: utf-8
module Gherkin
  module Tools
    class StatsListener
      attr_reader :features, :scenarios, :steps

      def initialize
        @features  = 0
        @scenarios = 0
        @steps     = 0
      end

      def tag(name, line)
      end

      def comment(content, line)
      end

      def feature(keyword, name, line)
        @features += 1
      end

      def background(keyword, name, line)
      end

      def scenario(keyword, name, line)
        @scenarios += 1
      end

      def scenario_outline(keyword, name, line)
      end

      def examples(keyword, name, line)
      end

      def step(keyword, name, line)
        @steps += 1
      end

      def row(row, line)
      end

      def py_string(string, line)
      end

      def syntax_error(state, event, legal_events, line)
      end

      def eof
      end
    end
  end
end
