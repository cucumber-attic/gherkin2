require 'gherkin/syntax_policy/state'

module Gherkin
  module SyntaxPolicy
    class ScenarioOutlineState < State
      def initialize
        @examples, @within_examples = false
        super
      end

      def scenario
        true
      end
      
      def scenario_outline
        @examples, @within_examples = false
        @step = true
      end
      
      def examples
        if @examples
          @step = false
          @within_examples = true
        end
      end

      def tag
        @examples, @within_examples = false
        super
      end

      def table
        if @multiline or @within_examples
          @multiline = false
          true
        end
      end

      def step
        if super
          @examples = true
        end
      end

      def py_string
        super unless @within_examples
      end
    end
  end
end
