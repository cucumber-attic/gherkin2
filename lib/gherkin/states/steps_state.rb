require 'gherkin/states/state'

module Gherkin
  module States
    class StepsState < State
      def initialize
        super
        @step = true
      end
      
      def tag
        false
      end
    end
  end
end