require 'gherkin/syntax_policy'

module Gherkin
  class StepsPolicy < SyntaxPolicy
    def initialize(listener, raise_on_error=true)
      super
      @current = StepsState.new
    end
  end
end