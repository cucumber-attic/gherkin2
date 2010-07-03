require 'gherkin/tag_expression'

module Gherkin
  module Formatter
    class FilterFormatter
      def initialize(formatter, filters)
        @formatter = formatter
        @filter = TagExpression.new(filters)
      end

      def _feature(comments, tags, keyword, name, description, uri)
      end

      def _background(comments, keyword, name, description, line)
      end

      def _scenario(comments, tags, keyword, name, description, line)
      end

      def _scenario_outline(comments, tags, keyword, name, description, line)
      end

      def _examples(comments, tags, keyword, name, description, line, examples_table)
      end

      def _step(comments, keyword, name, line, multiline_arg, status, exception, arguments, stepdef_location)
      end

      def _eof
      end

    private
    
      def method_missing(*args)
        case(args[0])
        when :feature
          @header_events = [args]
          @feature_tags = args[2]
        when :background
          @header_events << args
        when :scenario, :scenario_outline
          replay!

          @feature_element_events = []
          @feature_element_events << args
          
          @feature_element_tags = (@feature_tags + args[2]).uniq
          @feature_element_ok = @filter.eval(@feature_element_tags)
        when :examples
          @feature_element_events << args
          #@examples_tags = (@feature_element_tags + args[2]).uniq
          #@feature_element_ok = @filter.eval(@feature_element_tags)
        when :step
          if @feature_element_events
            @feature_element_events << args
          else
            @header_events << args
          end
        when :eof
          replay!
        else
          super
        end
      end

      def replay!
        if @feature_element_ok
          if @header_events.any?
            replay_events!(@header_events)
          end
          replay_events!(@feature_element_events)
        end
      end
      
      def replay_events!(events)
        events.each do |event|
          @formatter.__send__(*event)
        end
        events.clear
      end
    end
  end
end
