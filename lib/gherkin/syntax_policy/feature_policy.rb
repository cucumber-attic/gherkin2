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
      include FeatureState
      attr_writer :raise_on_error
      
      def initialize(listener, raise_on_error=true)
        @listener, @raise_on_error = listener, raise_on_error
        @feature, @background, @body, @scenario_outline, @step_allowed, @examples_allowed = false
      end
            
      def error(args)
        @raise_on_error ? raise(FeatureSyntaxError.new(*args)) : @listener.syntax_error(*args)
      end
    end
  end
end
