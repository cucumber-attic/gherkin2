require 'gherkin/syntax_policy'

module Gherkin    
  class FeaturePolicy < SyntaxPolicy    
    def initialize(listener, raise_on_error=true)
      super
      @states = { 
        :feature => FeatureState.new, :scenario => ScenarioState.new, 
        :scenario_outline => ScenarioOutlineState.new, :examples => ExamplesState.new 
      }
      @current = @states[:feature]
    end
          
    def scenario(*args)
      change_state(:scenario)
      dispatch(:scenario, *args)
    end

    def scenario_outline(*args)
      change_state(:scenario_outline)
      dispatch(:scenario_outline, *args)
    end
    
    def examples(*args)
      change_state(:examples)
      dispatch(:examples, *args)
    end
  end
end
