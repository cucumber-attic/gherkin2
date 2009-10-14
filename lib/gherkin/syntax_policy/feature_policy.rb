require 'gherkin/syntax_policy/feature_state'

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
      attr_reader :listener
      
      def initialize(listener, raise_on_error=true)
        @listener, @raise_on_error = listener, raise_on_error
        @state = FeatureState.new
      end
            
      def error(args)
        @raise_on_error ? raise(FeatureSyntaxError.new(*args)) : @listener.syntax_error(*args)
      end

      def method_missing(meth, *args)
        if @state.respond_to?(meth)
          if @state.send(meth)
           @listener.send(meth, *args)
          else
            error([meth] + args)
          end
        else
          super
        end
      end
    end
  end
end
