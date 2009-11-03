require 'gherkin/states/state'

module Gherkin
  module States
    class FeatureState < State
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
          @step = true
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
