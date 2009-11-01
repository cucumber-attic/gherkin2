require 'gherkin/syntax_policy/state'

module Gherkin
  module SyntaxPolicy
    class ScenarioOutlineState < State
      def initialize
        @examples = false
        super
      end

      def scenario
        true
      end
      
      def scenario_outline
        @examples = false
        @step = true
      end
      
      def examples
        if @examples
          @step = false
          true
        end
      end

      def tag
        @examples = false
        super
      end

      def table
        if @multiline
          @multiline = false
          true
        end
      end

      def step
        if super
          @examples = true
        end
      end
    end
  end
end
