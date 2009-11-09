require 'gherkin/parser'

module Gherkin
  class Steps < Parser    
    def initialize(i18n_lang, listener, args={})
      super
      @current = StepsState.new
    end
  end
end