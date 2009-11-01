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
        @step = true
      end
      
      def examples
        @examples
      end

      def tag
        @examples = false
        super
      end

      def step
        if super
          @examples = true
        end
      end
    end
  end
end
