require 'gherkin/syntax_policy/feature_policy_state'

module Gherkin
  module SyntaxPolicy
    class ScenarioOutlineState < FeaturePolicyState
      def initialize
        @examples, @within_examples = false
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
          @within_examples = true
        end
      end

      def tag
        @examples, @within_examples = false
        super
      end

      def table
        if @multiline or @within_examples
          @multiline = false
          true
        end
      end

      def step
        if super
          @examples = true
        end
      end

      def py_string
        super unless @within_examples
      end
    end
  end
end
