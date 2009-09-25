module Gherkin
  module SyntaxPolicy

    class FeatureSyntaxError < SyntaxError
      attr_reader :line
      
      def initialize(line)
        @line = line
        super "Syntax error on line #{@line}."
      end
    end
    
    class FeaturePolicy
      attr_writer :permissive
      
      def initialize(listener, permissive=false)
        @listener, @permissive = listener, permissive
        @feature, @background, @body = false
      end
      
      def feature(*args)
        if !@feature
          @feature = true
          @listener.feature(*args)
        else
          error(:feature, args) if @feature
        end
      end
      
      def background(*args)
        if @feature and !@background and !@body
          @background = true
          @listener.background(*args)
        else
          error(:background, args)
        end
      end
      
      def scenario_outline(*args)
        if @feature
          @body = true
          @listener.scenario_outline(*args)
        else
          error(:scenario_outline, args)
        end
      end
      
      def scenario(*args)
        if @feature
          @body = true
          @listener.scenario_outline(*args)
        else
          error(:scenario, args)
        end
      end
      
      def examples(*args)
        if @feature
          @body = true
          @listener.examples(*args)
        else
          error(:keyword, args)
        end
      end

      def step(*args)
        if @feature
          @body = true
          @listener.examples(*args)
        else
          error(:step, args)
        end
      end
      
      def error(event, args)
        @permissive ? @listener.error(event, args) : raise(FeatureSyntaxError.new(args.last))
      end
    end
  end
end
