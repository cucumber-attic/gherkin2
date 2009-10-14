module Gherkin
  module SyntaxPolicy
    class ScenarioState
      def initialize
        @feature, @background = true
        @body, @scenario_outline, @step_allowed, @examples_allowed = false
      end

      def feature
        false
      end
      
      def background
        false
      end
            
      def scenario
        if @feature
          @body = true
          @step_allowed = true
          @scenario_outline, @examples_allowed = false
          true
        else
          false
        end
      end
      
      def scenario_outline
        if @feature
          @body = true
          @scenario_outline = true
          @step_allowed = true
          true
        else
          false
        end
      end
      
      def examples
        if @feature and @examples_allowed
          @body = true
          @step_allowed = false
          true
        else
          false
        end
      end

      def step
        if @feature and @step_allowed
          @body = true
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
        if @step_allowed or @examples_allowed
          true
        else
          false
        end
      end
      
      def py_string
        if @step_allowed
          true
        else
          false
        end
      end
    end
  end
end
