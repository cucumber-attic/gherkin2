require 'gherkin/states/state'

module Gherkin
  module States
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
