module Gherkin
  module SyntaxPolicy
    class ScenarioOutlineState < FeaturePolicyState
      def initialize
        @examples_allowed = false
        super
      end

      def scenario
        true
      end
      
      def scenario_outline
        @step_allowed = true
        true
      end
      
      def examples
        if @examples_allowed
          @step_allowed = false
          true
        end
      end

      def tag
        @examples_allowed = false
        super
      end

      def step
        if @step_allowed
          @examples_allowed = true
          true
        end
      end
 
      def table
        @step_allowed or @examples_allowed
      end
      
      def py_string
        @step_allowed
      end
    end
  end
end
