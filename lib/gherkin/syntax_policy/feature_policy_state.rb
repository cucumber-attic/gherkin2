module Gherkin
  module SyntaxPolicy
    class FeaturePolicyState
      def initialize
        @step, @multiline = false
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
        if @step
          @multiline = true
        end
      end

      def table
        if @multiline
          @multiline = false
          true
        end
      end

      def py_string
        if @multiline
          @multiline = false
          true
        end
      end

      def tag
        @step = false
        true
      end

      def comment
        true
      end
    end
  end
end
