module Gherkin
  module SyntaxPolicy
    module StepOrder
      def step
        if @step_allowed
          @multiline_allowed = true
          @step_allowed
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
    end
  end
end
