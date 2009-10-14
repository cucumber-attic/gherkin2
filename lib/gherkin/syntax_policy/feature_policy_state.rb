module Gherkin
  module SyntaxPolicy
    class FeaturePolicyState
      def initialize
        @step_allowed = false
      end

      def feature
        false
      end
      
      def background
        false
      end

      def scenario
        false
      end

      def scenario_outline
        false
      end

      def examples
        false
      end

      def step
        if @step_allowed
          @multiline_allowed = true
          true
        end
      end

      def table
        if @multiline_allowed
          @multiline_allowed = false
          true
        end
      end

      def py_string
        if @multiline_allowed
          @multiline_allowed = false
          true
        end
      end

      def tag
        @step_allowed = false
        true
      end

      def comment
        true
      end
    end
  end
end
