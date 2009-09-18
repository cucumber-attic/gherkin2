module Gherkin
  module SyntaxPolicy
    FeatureSyntaxError = Class.new(SyntaxError)
    
    class FeaturePolicy
      def initialize(strict=true)
        @strict = strict
        @feature, @body = false
      end
      
      def feature(keyword, content, line)
        error if @feature
        @feature = true
      end
      
      def background(keyword, content, line)
        error unless @feature and !@body
      end
      
      def scenario_outline(keyword, content, line)
        error unless @feature
        @body = true
      end
      
      def scenario(keyword, content, line)
        error unless @feature
        @body = true
      end
      
      def examples(keyword, content, line)
        error unless @feature
        @body = true
      end
      
      def error
        @strict ? raise(FeatureSyntaxError) : nil # Will delegate error message to listener eventually
      end
    end
  end
end