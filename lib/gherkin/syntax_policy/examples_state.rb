require 'gherkin/syntax_policy/state'

module Gherkin
  module SyntaxPolicy
    class ExamplesState < State
      def initialize
        @examples = false
        super
      end

      def scenario
        true
      end
      
      def scenario_outline
        true
      end
      
      def examples
        @examples = true
      end

      def tag
        @examples = false
        super
      end

      def table
        @examples
      end
    end
  end
end