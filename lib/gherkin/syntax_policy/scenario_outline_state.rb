require 'gherkin/syntax_policy/feature_policy_state'

module Gherkin
  module SyntaxPolicy
    class ScenarioOutlineState < FeaturePolicyState
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
        if @examples
          @step = false
          true
        end
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

      def py_string
        super unless @examples
      end
    end
  end
end
