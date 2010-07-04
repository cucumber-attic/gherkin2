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

      def feature(comments, tags, keyword, name, description, uri)
        @header_events << [:feature, comments, tags, keyword, name, description, uri]
        @feature_tags = tags
      end

      def background(comments, keyword, name, description, line)
        @header_events << [:background, comments, keyword, name, description, line]
      end

      def scenario(comments, tags, keyword, name, description, line)
        filter_scenario(:scenario, comments, tags, keyword, name, description, line)
      end

      def scenario_outline(comments, tags, keyword, name, description, line)
        filter_scenario(:scenario_outline, comments, tags, keyword, name, description, line)
      end

      def examples(comments, tags, keyword, name, description, line, examples_table)
        replay!

        @examples_events.clear
        @examples_events << [:examples, comments, tags, keyword, name, description, line, examples_table]

        case @filter
        when TagExpression
          examples_tags = (@feature_element_tags + tags).uniq
          @feature_element_ok = @filter.eval(examples_tags)
        when RegexpFilter
          @feature_element_ok = @filter.eval([@feature_element_name, name])
        when LineFilter
          examples_range = line..examples_table.last.line
          @feature_element_ok = @filter.eval([feature_element_range, examples_range])
        end
      end

      def step(comments, keyword, name, line, multiline_arg, status, exception, arguments, stepdef_location)
        args = [:step, comments, keyword, name, line, multiline_arg, status, exception, arguments, stepdef_location]
        if @feature_element_events.any?
          @feature_element_events << args
          
          if LineFilter === @filter
            @feature_element_end = line
            @feature_element_ok = @filter.eval([feature_element_range])
          end
        else
          @header_events << args
        end
      end

      def eof
        replay!
        @formatter.eof
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

      def filter_scenario(method, comments, tags, keyword, name, description, line)
        replay!

        @feature_element_events.clear
        @feature_element_events << [method, comments, tags, keyword, name, description, line]
        
        case @filter
        when TagExpression
          @feature_element_tags = (@feature_tags + tags).uniq
          @feature_element_ok = @filter.eval(@feature_element_tags)
        when RegexpFilter
          @feature_element_name = name
          @feature_element_ok = @filter.eval([@feature_element_name])
        when LineFilter
          @feature_element_start = line
          @feature_element_end = line
          @feature_element_ok = @filter.eval([feature_element_range])
        end
      end

      def feature_element_range
        @feature_element_start..@feature_element_end
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
