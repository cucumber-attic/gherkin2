module Gherkin
  module SyntaxPolicy
    class ScenarioState
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
        @step_allowed = true
        true
      end
      
      def scenario_outline
        true
      end
      
      def examples
        false
      end

      def step
        @step_allowed
      end
      
      def comment
        true
      end
      
      def tag
        @step_allowed = false
        true
      end

      def table
        @step_allowed 
      end
      
      def py_string
        @step_allowed
      end
    end
  end
end
