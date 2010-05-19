require 'gherkin/parser/event'
require 'gherkin/parser/tag_expression'
require 'gherkin/bridge'

module Gherkin
  module Parser
    # This class filters events based on filter criteria.
    class FilterListener
      native_impl('gherkin')
    
      # Creates a new instance that replays events to +listener+, filtered by +filters+,
      # an Array that can contain one of the following:
      #
      # * Line numbers (Fixnum) to filter on.
      # * Name regexen (Regexp) to filter on. Matches against :feature, :background, :scenario, :scenario_outline and :examples
      # * Tag expressions (String) to filter on.
      #
      def initialize(listener, filters)
        @listener = listener
        @filter_method = detect_filter(filters)
        
        @meta_buffer = []
        @feature_buffer = []
        @scenario_buffer = []
        @examples_buffer = []
        @examples_rows_buffer = []

        @feature_tags = []
        @scenario_tags = []
        @example_tags = []

        @table_state = :step
      end
      
      private

      def method_missing(*event_args)
        event = Event.new(event_args)

        return event.replay(@listener) if no_filters?

        case(event.event)
        when :tag
          @meta_buffer << event
        when :comment
          @meta_buffer << event
        when :feature
          @feature_buffer = @meta_buffer
          @feature_buffer << event
          @feature_tags = extract_tags
          @meta_buffer = []
          @feature_ok = true if filter_match?(event)
        when :background
          @feature_buffer += @meta_buffer
          @feature_buffer << event
          @meta_buffer = []
          @table_state = :background
          @background_ok = true if filter_match?(event)
        when :scenario
          replay_examples_rows_buffer
          @scenario_buffer = @meta_buffer
          @scenario_buffer << event
          @scenario_tags = extract_tags
          @example_tags = []
          @meta_buffer = []
          @scenario_ok = filter_match?(*@scenario_buffer) || tag_match?
          @examples_ok = false
          @background_ok = false
          @table_state = :step
        when :scenario_outline
          replay_examples_rows_buffer
          @scenario_buffer = @meta_buffer
          @scenario_buffer << event
          @scenario_tags = extract_tags
          @example_tags = []
          @meta_buffer = []
          @scenario_ok = filter_match?(*@scenario_buffer)
          @examples_ok = false
          @background_ok = false
          @table_state = :step
        when :examples
          replay_examples_rows_buffer
          @examples_buffer = @meta_buffer
          @examples_buffer << event
          @example_tags = extract_tags
          @meta_buffer = []
          @examples_rows_buffer = []
          @examples_ok = filter_match?(*@examples_buffer) || tag_match?
          @table_state = :examples
        when :step
          case(@table_state)
          when :background
            @feature_buffer += @meta_buffer
            @feature_buffer << event
            @meta_buffer = []
            @background_ok = true if filter_match?(event)
          else
            @scenario_buffer << event
            @scenario_ok ||= filter_match?(*@scenario_buffer)
            @table_state = :step
          end
        when :row
          case(@table_state)
          when :examples
            unless header_row_already_buffered?
              @examples_buffer << event
              @examples_ok = true if filter_match?(*@examples_buffer)
            else
              @examples_rows_buffer << event if @scenario_ok || @examples_ok || @feature_ok || filter_match?(event)
            end
          when :step
            @scenario_buffer << event
            @scenario_ok ||= filter_match?(*@scenario_buffer)
          when :background
            @feature_buffer += @meta_buffer
            @feature_buffer << event
            @meta_buffer = []
          else
            raise "Bad table_state:#{@table_state.inspect}"
          end
        when :py_string
          if @table_state == :background
            @feature_buffer << event
            @feature_ok ||= filter_match?(*@feature_buffer)
          else
            @scenario_buffer << event
            @scenario_ok ||= filter_match?(*@scenario_buffer)
          end
        when :eof
          replay_examples_rows_buffer
          event.replay(@listener)
          return
        else
          super
        end

        if @scenario_ok || @examples_ok || @feature_ok || @background_ok
          replay_buffers
        end
      end

      def detect_filter(filters)
        @filters = filters
        raise "Bad filter: #{filters.inspect}" if filters.map{|filter| filter.class}.uniq.length > 1
        @filter_method = case(filters[0])
        when Fixnum 
          :line_match?
        when Regexp 
          :name_match?
        when String 
          TagExpression.new(filters)
        end
      end

      def no_filters?
        @filters.empty?
      end

      def header_row_already_buffered?
        return @examples_buffer.any? && @examples_buffer[-1].event == :row
      end
      
      def filter_match?(*events)
        return false unless[:name_match?, :line_match?].include?(@filter_method)
        events.detect{|event| event.__send__(@filter_method, @filters)}
      end

      def tag_match?
        return TagExpression === @filter_method && @filter_method.eval(current_tags)
      end

      def replay_buffers
        (@feature_buffer + @scenario_buffer).each do |event| 
          event.replay(@listener)
        end
        @feature_buffer = []
        @scenario_buffer = [] 
      end

      def replay_examples_rows_buffer
        if @examples_rows_buffer.any?
          replay_buffers
          (@examples_buffer + @examples_rows_buffer).each do |event|
            event.replay(@listener)
          end
          @examples_rows_buffer = []
        end
      end
      
      def current_tags
        @feature_tags + @scenario_tags + @example_tags
      end

      def extract_tags
        @meta_buffer.select { |event| event.event == :tag }.map { |event| event.keyword }
      end
    end
  end
end
