module Gherkin
  module SyntaxPolicy
    FeatureSyntaxError = Class.new(SyntaxError)
    
    class FeaturePolicy
      def initialize
        @feature, @body = false
      end
      
      def feature(keyword, content, line)
        raise FeatureSyntaxError if @feature
        @feature = true
      end
      
      def background(keyword, content, line)
        raise FeatureSyntaxError unless @feature and !@body
      end
      
      def scenario_outline(keyword, content, line)
        raise FeatureSyntaxError unless @feature
        @body = true
      end
      
      def scenario(keyword, content, line)
        raise FeatureSyntaxError unless @feature
        @body = true
      end
      
      def examples(keyword, content, line)
        raise FeatureSyntaxError unless @feature
        @body = true
      end
    end
  end
end