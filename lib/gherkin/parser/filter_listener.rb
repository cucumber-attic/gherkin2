require 'gherkin/parser/sexp'
require 'gherkin/parser/tag_expression'

module Gherkin
  module Parser
    # This class filters events based on filter criteria.
    class FilterListener
      require 'gherkin/java_impl'
      java_impl('gherkin.jar')
    
      # Creates a new instance that replays events to +listener+, filtered by +filters+,
      # a Hash that can contain:
      #
      # * <tt>:lines</tt> An Array of line numbers to filter on.
      # * <tt>:name_regexen</tt> An Array of name regexen to filter on. Matches against :feature, :scenario, :scenario_outline and :examples
      # * <tt>:tag_expression</tt> A TagExpression to filter on.
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

      def method_missing(*sexp_args)
        sexp = Sexp.new(sexp_args)

        return sexp.replay(@listener) if no_filters?

        case(sexp.event)
        when :tag
          @meta_buffer << sexp
        when :comment
          @meta_buffer << sexp
        when :feature
          @feature_buffer = @meta_buffer
          @feature_buffer << sexp
          @feature_tags = extract_tags
          @meta_buffer = []
          @feature_ok = true if filter_match?(sexp)
        when :background
          @feature_buffer += @meta_buffer
          @feature_buffer << sexp
          @meta_buffer = []
          @table_state = :background
          @feature_ok = true if filter_match?(sexp)
        when :scenario
          replay_examples_rows_buffer
          @scenario_buffer = @meta_buffer
          @scenario_buffer << sexp
          @scenario_tags = extract_tags
          @example_tags = []
          @meta_buffer = []
          @scenario_ok = filter_match?(*@scenario_buffer) || tag_match?
          @examples_ok = false
          @table_state = :step
        when :scenario_outline
          replay_examples_rows_buffer
          @scenario_buffer = @meta_buffer
          @scenario_buffer << sexp
          @scenario_tags = extract_tags
          @example_tags = []
          @meta_buffer = []
          @scenario_ok = filter_match?(*@scenario_buffer)
          @examples_ok = false
          @table_state = :step
        when :examples
          replay_examples_rows_buffer
          @examples_buffer = @meta_buffer
          @examples_buffer << sexp
          @example_tags = extract_tags
          @meta_buffer = []
          @examples_rows_buffer = []
          @examples_ok = filter_match?(*@examples_buffer) || tag_match?
          @table_state = :examples
        when :step
          case(@table_state)
          when :background
            @feature_buffer += @meta_buffer
            @feature_buffer << sexp
            @meta_buffer = []
            @feature_ok = true if filter_match?(sexp)
          else
            @scenario_buffer << sexp
            @scenario_ok ||= filter_match?(*@scenario_buffer)
            @table_state = :step
          end
        when :row
          case(@table_state)
          when :examples
            unless header_row_already_buffered?
              @examples_buffer << sexp
              @examples_ok = true if filter_match?(*@examples_buffer)
            else
              @examples_rows_buffer << sexp if @scenario_ok || @examples_ok || @feature_ok || filter_match?(sexp)
            end
          when :step
            @scenario_buffer << sexp
            @scenario_ok ||= filter_match?(*@scenario_buffer)
          when :background
            @feature_buffer += @meta_buffer
            @feature_buffer << sexp
            @meta_buffer = []
          else
            raise "Bad table_state:#{@table_state.inspect}"
          end
        when :py_string
          if @table_state == :background
            @feature_buffer << sexp
            @feature_ok ||= filter_match?(*@feature_buffer)
          else
            @scenario_buffer << sexp
            @scenario_ok ||= filter_match?(*@scenario_buffer)
          end
        when :eof
          replay_examples_rows_buffer
          sexp.replay(@listener)
          return
        else
          super
        end

        if @scenario_ok || @examples_ok || @feature_ok
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
      
      def filter_match?(*sexps)
        return false unless[:name_match?, :line_match?].include?(@filter_method)
        sexps.detect{|sexp| sexp.__send__(@filter_method, @filters)}
      end

      def tag_match?
        return TagExpression === @filter_method && @filter_method.eval(current_tags)
      end

      def replay_buffers
        (@feature_buffer + @scenario_buffer).each do |sexp| 
          sexp.replay(@listener)
        end
        @feature_buffer = []
        @scenario_buffer = [] 
      end

      def replay_examples_rows_buffer
        if @examples_rows_buffer.any?
          replay_buffers
          (@examples_buffer + @examples_rows_buffer).each do |sexp|
            sexp.replay(@listener)
          end
          @examples_rows_buffer = []
        end
      end
      
      def current_tags
        @feature_tags + @scenario_tags + @example_tags
      end

      def extract_tags
        @meta_buffer.select { |sexp| sexp.event == :tag }.map { |sexp| sexp.keyword }
      end
    end
  end
end
