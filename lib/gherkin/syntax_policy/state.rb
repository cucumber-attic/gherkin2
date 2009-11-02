module Gherkin
  module SyntaxPolicy
    class State      
      def initialize
        @step, @multiline = false
      end

      def feature
        false
      end
      
      def background
        false
      end

      def scenario
        false
      end

      def scenario_outline
        false
      end

      def examples
        false
      end

      def step
        if @step
          @multiline = true
        end
      end

      def table
        if @multiline
          @multiline = false
          true
        end
      end

      def py_string
        if @multiline
          @multiline = false
          true
        end
      end

      def tag
        @step = false
        true
      end

      def comment
        true
      end
      
      def allows?(event)
        lambda do |meth|
          this = self.dup
          this.send(meth)
        end.call(event)
      end
      
      def expected
        events = public_methods(false).map { |meth| meth.to_sym } # Some Rubies return strings
        events.delete(:expected) # No infinite loops, kthxbye
        (events.select { |meth| allows?(meth) } + [:tag, :comment]).uniq
      end      
    end
  end
end
