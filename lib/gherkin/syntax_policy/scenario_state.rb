module Gherkin
  module SyntaxPolicy
    class ScenarioState
      def initialize
        @scenario_outline, @step_allowed, @examples_allowed = false
      end

      def feature
        false
      end
      
      def background
        false
      end
            
      def scenario
        @step_allowed = true
        @scenario_outline, @examples_allowed = false
        true
      end
      
      def scenario_outline
        @scenario_outline = true
        @step_allowed = true
        true
      end
      
      def examples
        if @examples_allowed
          @step_allowed = false
          true
        else
          false
        end
      end

      def step
        if @step_allowed
          if @scenario_outline
            @examples_allowed = true
          end
          true
        else
          false
        end
      end
      
      def comment
        true
      end
      
      def tag
        @step_allowed, @examples_allowed = false
        true
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
