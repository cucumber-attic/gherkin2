require 'gherkin/tag_expression'
require 'gherkin/formatter/regexp_filter'
require 'gherkin/formatter/line_filter'

module Gherkin
  module Formatter
    class FilterFormatter
      def initialize(formatter, filters)
        @formatter = formatter
        @filter = detect_filter(filters)
        
        @header_events = []
        @feature_element_events = []
        @examples_events = []
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

      def detect_filter(filters)
        raise "Inconsistent filters: #{filters.inspect}" if filters.map{|filter| filter.class}.uniq.length > 1
        case(filters[0])
        when Fixnum 
          LineFilter.new(filters)
        when Regexp 
          RegexpFilter.new(filters)
        when String 
          TagExpression.new(filters)
        end
      end

      def method_missing(*args)
        case(args[0])
        when :feature
          @header_events << args
          @feature_tags = args[2]
        when :background
          @header_events << args
        when :scenario, :scenario_outline
          replay!

          @feature_element_events.clear
          @feature_element_events << args
          
          case @filter
          when TagExpression
            @feature_element_tags = (@feature_tags + args[2]).uniq
            @feature_element_ok = @filter.eval(@feature_element_tags)
          when RegexpFilter
            @feature_element_name = args[4]
            @feature_element_ok = @filter.eval([@feature_element_name])
          when LineFilter
            @feature_element_line = args[6]
            @feature_element_ok = @filter.eval([@feature_element_line])
          end
        when :examples
          replay!

          @examples_events.clear
          @examples_events << args

          case @filter
          when TagExpression
            examples_tags = (@feature_element_tags + args[2]).uniq
            @feature_element_ok = @filter.eval(examples_tags)
          when RegexpFilter
            examples_name = args[4]
            @feature_element_ok = @filter.eval([@feature_element_name, examples_name])
          end
        when :step
          if @feature_element_events.any?
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
          if @examples_events.any?
            replay_events!(@examples_events)
          end
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
