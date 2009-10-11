module Gherkin
  module SyntaxPolicy

    class FeatureSyntaxError < SyntaxError
      attr_reader :keyword, :content, :line
      
      def initialize(event, keyword, content, line, *args)
        @event, @keyword, @content, @line = event, keyword, content, line
        super "Syntax error on line #{@line}: '#{@keyword}: #{@content}'."
      end
    end
    
    class FeaturePolicy
      attr_writer :raise_on_error
      
      def initialize(listener, raise_on_error=true)
        @listener, @raise_on_error = listener, raise_on_error
        @feature, @background, @body, @scenario_outline, @step_allowed, @examples_allowed = false
      end
      
      def feature(*args)
        if !@feature
          @feature = true
          @listener.feature(*args)
        else
          error([:feature] + args) if @feature
        end
      end
      
      def background(*args)
        if @feature and !@background and !@body
          @background = true
          @step_allowed = true
          @listener.background(*args)
        else
          error([:background] + args)
        end
      end
            
      def scenario(*args)
        if @feature
          @body = true
          @step_allowed = true
          @scenario_outline, @examples_allowed = false
          @listener.scenario(*args)
        else
          error([:scenario] + args)
        end
      end
      
      def scenario_outline(*args)
        if @feature
          @body = true
          @scenario_outline = true
          @step_allowed = true
          @listener.scenario_outline(*args)
        else
          error([:scenario_outline] + args)
        end
      end
      
      def examples(*args)
        if @feature and @examples_allowed
          @body = true
          @step_allowed = false
          @listener.examples(*args)
        else
          error([:examples] + args)
        end
      end

      def step(*args)
        if @feature and @step_allowed
          @body = true
          if @scenario_outline
            @examples_allowed = true
          end
          @listener.step(*args)
        else
          error([:step] + args)
        end
      end
      
      def comment(*args)
        @listener.comment(*args)
      end
      
      def tag(*args)
        @step_allowed, @examples_allowed = false
        @listener.tag(*args)
      end

      def table(*args)
        if @step_allowed or @examples_allowed
          @listener.table(*args)
        else
          error([:table] + args)
        end
      end
      
      def py_string(*args)
        if @step_allowed
          @listener.py_string(*args)
        else
          error([:py_string] + args)
        end
      end
      
      def error(args)
        @raise_on_error ? raise(FeatureSyntaxError.new(*args)) : @listener.syntax_error(*args)
      end
    end
  end
end
