require 'gherkin/rubify'
require 'gherkin/tag_expression'
require 'gherkin/formatter/regexp_filter'
require 'gherkin/formatter/line_filter'
require 'gherkin/formatter/model'

module Gherkin
  module Formatter
    class FilterFormatter
      include Rubify
      
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
        @examples_events.clear
      end

      def scenario_outline(statement)
        replay!
        @feature_element_tags   = statement.tags
        @feature_element_name   = statement.name
        @feature_element_range  = statement.line_range
        @feature_element_events = [[:scenario_outline, statement]]
        @examples_events.clear
      end

      def examples(statement, examples_rows)
        replay!
        @examples_tags = statement.tags
        @examples_name = statement.name

        table_body_range = examples_rows.to_a[1].line..examples_rows.to_a[-1].line
        @examples_range = statement.line_range.first..table_body_range.last
        if(LineFilter === @filter && @filter.eval([table_body_range]))
          examples_rows = @filter.filter_table_body_rows(examples_rows)
        end
        @examples_events = [[:examples, statement, examples_rows]]
      end

      def step(statement, multiline_arg, result)
        args = [:step, statement, multiline_arg, result]
        if @feature_element_events.any?
          @feature_element_events << args
        else
          @background_events << args
        end

        if LineFilter === @filter
          step_range = statement.line_range
          case rubify(multiline_arg)
          when Model::PyString
            step_range = step_range.first..multiline_arg.line_range.last
          when Array
            step_range = step_range.first..multiline_arg.to_a[-1].line
          end
          @feature_element_range = @feature_element_range.first..step_range.last
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

      def replay!
        case @filter
        when TagExpression
          feature_element_ok = @filter.eval(tag_names(@feature_tags.to_a + @feature_element_tags.to_a))
          examples_ok        = @filter.eval(tag_names(@feature_tags.to_a + @feature_element_tags.to_a + @examples_tags.to_a)) if @examples_tags
        when RegexpFilter
          feature_element_ok = @filter.eval([@feature_element_name])
          examples_ok        = @filter.eval([@feature_element_name, @examples_name]) if @examples_name
        when LineFilter
          feature_element_ok = @filter.eval([@feature_element_range]) if @feature_element_range
          examples_ok        = @filter.eval([@feature_element_range, @examples_range]) if @examples_range
        end

        if feature_element_ok || examples_ok
          replay_events!(@feature_events)
          replay_events!(@background_events)
          replay_events!(@feature_element_events)

          if examples_ok
            replay_events!(@examples_events)
          end
        end
      end

      def tag_names(tags)
        tags.to_a.uniq.map{|tag| tag.name}
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
