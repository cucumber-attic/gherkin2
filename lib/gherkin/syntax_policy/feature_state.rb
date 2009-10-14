require 'gherkin/syntax_policy/feature_policy_state'

module Gherkin
  module SyntaxPolicy
    class FeatureState < FeaturePolicyState
      def initialize
        @feature, @background = false
        super
      end

      def feature
        if !@feature
          @feature = true
        end
      end
      
      def background
        if @feature and !@background 
          @step_allowed = true
          @background = true
        end
      end
            
      def scenario
        @feature
      end
      
      def scenario_outline
        @feature
      end
    end
  end
end
