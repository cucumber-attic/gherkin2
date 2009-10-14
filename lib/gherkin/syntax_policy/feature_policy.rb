require 'gherkin/syntax_policy/feature_state'
require 'gherkin/syntax_policy/scenario_state'
require 'gherkin/syntax_policy/scenario_outline_state'

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
        @states = { :feature => FeatureState.new, :scenario => ScenarioState.new, :scenario_outline => ScenarioOutlineState.new }
        @current = @states[:feature]
      end
            
      def error(args)
        @raise_on_error ? raise(FeatureSyntaxError.new(*args)) : @listener.syntax_error(*args)
      end

      def scenario(*args)
        if @current.scenario
          @current = @states[:scenario] # Switch states if allowed
        end

        if @current.send(:scenario)
          @listener.send(:scenario, *args)
        else
          error([:scenario] + args)
        end
      end

      def scenario_outline(*args)
        if @current.scenario_outline
          @current = @states[:scenario_outline]
        end

        if @current.send(:scenario_outline)
          @listener.send(:scenario_outline, *args)
        else
          error([:scenario] + args)
        end
      end

      def method_missing(meth, *args)
        if @current.respond_to?(meth)
          if @current.send(meth)
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
