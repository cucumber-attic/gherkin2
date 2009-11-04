require 'gherkin/syntax_policy'

module Gherkin
  class Steps < SyntaxPolicy    
    def initialize(i18n_lang, listener, args={})
      super
      @current = StepsState.new
    end
  end
end