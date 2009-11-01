require 'gherkin/syntax_policy/state'

module Gherkin
  module SyntaxPolicy
    class ScenarioState < State
      def scenario
        @step = true
      end
      
      def scenario_outline
        true
      end
    end
  end
end
