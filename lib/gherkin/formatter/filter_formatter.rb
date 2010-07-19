require 'gherkin/tag_expression'
require 'gherkin/formatter/regexp_filter'
require 'gherkin/formatter/line_filter'
require 'gherkin/formatter/tag_filter'
require 'gherkin/formatter/model'
require 'gherkin/native'

module Gherkin
  module Formatter
    class FilterFormatter
      native_impl('gherkin')
      
      def initialize(formatter, filters)
        @formatter = formatter
        @filter    = detect_filter(filters)

        @feature_tags           = []
        @feature_element_tags   = []
        @examples_tags          = []

        @feature_events         = []
        @background_events      = []
        @feature_element_events = []
        @examples_events        = []
      end

      def feature(statement, uri)
        @feature_tags   = statement.tags
        @feature_name   = statement.name
        @feature_events = [[:feature, statement, uri]]
      end

      def background(statement)
        @feature_element_name   = statement.name
        @feature_element_range  = statement.line_range
        @background_events      = [[:background, statement]]
      end

      def scenario(statement)
        replay!
        @feature_element_tags   = statement.tags
        @feature_element_name   = statement.name
        @feature_element_range  = statement.line_range
        @feature_element_events = [[:scenario, statement]]
      end

      def scenario_outline(statement)
        replay!
        @feature_element_tags   = statement.tags
        @feature_element_name   = statement.name
        @feature_element_range  = statement.line_range
        @feature_element_events = [[:scenario_outline, statement]]
      end

      def examples(statement, examples_rows)
        replay!
        @examples_tags = statement.tags
        @examples_name = statement.name

        table_body_range = examples_rows[1].line..examples_rows[-1].line
        @examples_range = statement.line_range.first..table_body_range.last
        if(@filter.eval([], [], [table_body_range]))
          examples_rows = @filter.filter_table_body_rows(examples_rows)
        end
        @examples_events = [[:examples, statement, examples_rows]]
      end

      def step(statement, multiline_arg, result)
        event = [:step, statement, multiline_arg, result]
        if @feature_element_events.any?
          @feature_element_events << event
        else
          @background_events << event
        end

        step_range = statement.line_range
        case multiline_arg
        when Model::PyString
          step_range = step_range.first..multiline_arg.line_range.last
        when Array
          step_range = step_range.first..multiline_arg[-1].line
        end
        @feature_element_range = @feature_element_range.first..step_range.last
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
          TagFilter.new(filters)
        end
      end

      def replay!
        feature_element_ok = @filter.eval(
          (@feature_tags + @feature_element_tags), 
          [@feature_name, @feature_element_name].compact, 
          [@feature_element_range].compact
        )
        examples_ok = @filter.eval(
          (@feature_tags + @feature_element_tags + @examples_tags), 
          [@feature_name, @feature_element_name, @examples_name].compact, 
          [@feature_element_range, @examples_range].compact
        )

        if feature_element_ok || examples_ok
          replay_events!(@feature_events)
          replay_events!(@background_events)
          replay_events!(@feature_element_events)

          if examples_ok
            replay_events!(@examples_events)
          end
        end

        @examples_events.clear
        @examples_tags.clear
        @examples_name = nil
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
