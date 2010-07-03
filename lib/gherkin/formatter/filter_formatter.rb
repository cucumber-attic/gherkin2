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
          @body_events = []
          @feature_tags = args[2]
        when :background
          @background = true
          @header_events << args
        when :scenario
          @background = false
          tags = (@feature_tags + args[2]).uniq
          @feature_element_ok = @filter.eval(tags)
          if @feature_element_ok
            @body_events << args
          end
        when :scenario_outline
          @background = false
        when :examples
          @background = false
        when :step
          if @background
            @header_events << args
          elsif @feature_element_ok
            @body_events << args
          end
        when :eof
          replay
        else
          super
        end
      end

      def replay
        return if @body_events.empty?
        (@header_events + @body_events).each do |event|
          @formatter.__send__(*event)
        end
      end
    end
  end
end
