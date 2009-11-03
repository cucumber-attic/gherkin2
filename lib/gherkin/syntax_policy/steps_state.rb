require 'gherkin/syntax_policy/state'

module Gherkin
  module SyntaxPolicy
    class StepsState < State
      def initialize
        super
        @step = true
      end
      
      def tag
        false
      end
    end
  end
end