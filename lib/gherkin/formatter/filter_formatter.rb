require 'gherkin/tag_expression'
require 'gherkin/formatter/regexp_filter'
require 'gherkin/formatter/line_filter'

module Gherkin
  module Formatter
    class FilterFormatter
      def initialize(formatter, filters)
        @formatter = formatter
        @filter = detect_filter(filters)
        
        @feature_events = []
        @background_events = []
        @feature_element_events = []
        @examples_events = []
      end

      def feature(statement, uri)
        @background_ok = false
        
        @feature_events << [:feature, statement, uri]
        @feature_tags = statement.tags
      end

      def background(statement)
        @feature_element_start = statement.line
        @feature_element_end = statement.line

        case @filter
        when RegexpFilter
          @background_ok = @filter.eval([@feature_element_name, statement.name])
        end

        @background_events << [:background, statement]
      end

      def scenario(statement)
        filter_scenario(:scenario, statement)
      end

      def scenario_outline(statement)
        filter_scenario(:scenario_outline, statement)
      end

      def examples(statement, examples_rows)
        replay!

        case @filter
        when TagExpression
          examples_tags = (@feature_element_tags + statement.tags).uniq
          @feature_element_ok = @filter.eval(examples_tags.map{|tag| tag.name})
        when RegexpFilter
          @feature_element_ok = @filter.eval([@feature_element_name, statement.name])
        when LineFilter
          table_body_range = examples_rows[1].line..examples_rows[-1].line
          if @filter.eval([table_body_range])
            examples_rows = @filter.filter_table_body_rows(examples_rows)
          end

          examples_range = statement.line..examples_rows[-1].line
          @feature_element_ok = @filter.eval([feature_element_range, examples_range])
        end

        if @feature_element_ok
          @examples_events << [:examples, statement, examples_rows]
        end
      end

      def step(statement, multiline_arg, result)
        args = [:step, statement, multiline_arg, result]
        if @feature_element_events.any?
          @feature_element_events << args
        else
          @background_events << args
        end

        if LineFilter === @filter
          @feature_element_end = statement.line
          @feature_element_ok = @filter.eval([feature_element_range])
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

      def filter_scenario(method, statement)
        replay!

        @examples_events.clear
        @feature_element_events.clear
        @feature_element_events << [method, statement]
        
        case @filter
        when TagExpression
          @feature_element_tags = (@feature_tags + statement.tags).uniq
          @feature_element_ok = @filter.eval(@feature_element_tags.map{|tag| tag.name})
        when RegexpFilter
          @feature_element_name = statement.name
          @feature_element_ok = @filter.eval([@feature_element_name])
        when LineFilter
          @feature_element_start = statement.line
          @feature_element_end = statement.line
          @feature_element_ok = @filter.eval([feature_element_range])
        end
      end

      def feature_element_range
        @feature_element_start..@feature_element_end
      end

      def replay!
        if @feature_element_ok || @background_ok
          replay_events!(@feature_events)

          if @background_ok
            replay_events!(@background_events)
          end
          if @feature_element_ok
            replay_events!(@background_events)
            replay_events!(@feature_element_events)
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
