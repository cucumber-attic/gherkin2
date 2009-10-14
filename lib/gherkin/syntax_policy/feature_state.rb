module Gherkin
  module SyntaxPolicy
    class FeatureState
      def initialize
        @feature, @background, @body, @scenario_outline, @step_allowed, @examples_allowed = false
      end

      def feature
        if !@feature
          @feature = true
          true
        else
          false
        end
      end
      
      def background(*args)
        if @feature and !@background and !@body
          @background = true
          @step_allowed = true
          true
        else
          false
        end
      end
            
      def scenario(*args)
        if @feature
          @body = true
          @step_allowed = true
          @scenario_outline, @examples_allowed = false
          true
        else
          false
        end
      end
      
      def scenario_outline(*args)
        if @feature
          @body = true
          @scenario_outline = true
          @step_allowed = true
          true
        else
          false
        end
      end
      
      def examples(*args)
        if @feature and @examples_allowed
          @body = true
          @step_allowed = false
          true
        else
          false
        end
      end

      def step(*args)
        if @feature and @step_allowed
          @body = true
          if @scenario_outline
            @examples_allowed = true
          end
          true
        else
          false
        end
      end
      
      def comment(*args)
        true
      end
      
      def tag(*args)
        @step_allowed, @examples_allowed = false
        true
      end

      def table(*args)
        if @step_allowed or @examples_allowed
          true
        else
          false
        end
      end
      
      def py_string(*args)
        if @step_allowed
          true
        else
          false
        end
      end
    end
  end
end
