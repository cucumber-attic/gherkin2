require 'gherkin/syntax_policy/step_order'

module Gherkin
  module SyntaxPolicy
    class ScenarioState
      include StepOrder

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

      def comment
        true
      end
      
      def tag
        @step_allowed = false
        true
      end
    end
  end
end
