require 'gherkin/parser'

module Gherkin
  # Parses the Cucumber Feature format
  #
  # Calls methods on the listener corresponding to events as they happen. All keywords are the
  # I18N names for the event ("Feature" for English, "Egenskap" for Norwegian, etc).
  #
  #   feature(keyword, content, line_number)
  #   background(keyword, content, line_number)
  #   scenario(keyword, content, line_number)
  #   scenario_outline(keyword, content, line_number)
  #   examples(keyword, content, line_number)
  #   step(keyword, content, line_number)
  #   comment(content, line_number)
  #   tag(tag_name, line_number)
  #   table(rows, line_number)
  #   py_string(offset, content, line_number)
  #   
  # LexingError will be raised if Gherkin cannot continue parsing input.
  #
  # SyntaxError will be raised if the text to scan is well formed, but syntactically incorrect:
  #
  #   Gherkin::Feature.new('it', Listener.new) raises FeatureSyntaxError on error
  #   Gherkin::Feature.new('en', AstBuilder.new, false) sends #syntax_error message to listener
  class Root < Parser    
    def initialize(i18n_lang, listener, args={})
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
