require 'gherkin/tools/sexp'

module Gherkin
  module Tools
    class LineFilterListener
      def initialize(listener, lines)
        @listener, @lines = listener, lines
        @sexps = []
        @comment_buffer = []
        @feature_buffer = []

        @next_uncollected_scenario_index = 0
        @current_index = -1
      end
      
      private

      def method_missing(*sexp_args)
        sexp = Sexp.new(sexp_args)
        @sexps << sexp

        @current_index += 1
        case(sexp.event)
        when :tag
        when :comment
          @comment_buffer << sexp
        when :feature
          @feature_buffer = @comment_buffer + @feature_buffer
          @feature_buffer << sexp
        when :background
        when :scenario, :scenario_outline
          @scenario_ok = line_match?(sexp)
          @first_scenario_index ||= @current_index
          @next_uncollected_scenario_index = @current_index
          @examples_ok = false
        when :examples
          @examples_index = @scenario_ok ? false : @current_index
          @examples_ok = line_match?(sexp)
          @included_rows = {}
          @table_state = :examples
        when :step
          @scenario_ok ||= line_match?(sexp)
          @included_rows = nil
          @table_state = :step
        when :row
          case(@table_state)
          when :examples
            row_ok = line_match?(sexp)
            if @included_rows.empty?
              # The header row is always ok
              @included_rows[@current_index] = true
              @examples_ok = true if line_match?(sexp)
            else
              @included_rows[@current_index] = @scenario_ok || @examples_ok || row_ok
            end
          when :step
            @scenario_ok ||= line_match?(sexp)
          else
            raise "BAD STATE"
          end
        when :eof
          sexp.replay(@listener)
          return
        else
          super
        end

        if @lines.empty?
          sexp.replay(@listener)
        elsif @scenario_ok || @examples_ok || row_ok
          collect_filtered_sexps
        end
      end
      
      def line_match?(sexp)
        @lines.index(sexp.line)
      end

      def collect_filtered_sexps
        # Collect Feature
        unless feature_already_replayed?
          @feature_buffer.each{|sexp| sexp.replay(@listener)}
          @feature_replayed = true
        end

        # Collect Scenario
        @next_uncollected_scenario_index = comments_before(@next_uncollected_scenario_index)
        (@next_uncollected_scenario_index..@current_index).each do |sexp_index|
          sexp = @sexps[sexp_index]
          sexp.replay(@listener) if included?(sexp.event, sexp_index)
        end
        
        @next_uncollected_scenario_index = @current_index + 1
      end

      def feature_already_replayed?
        @feature_replayed
      end

      def included?(event, index)
        (event != :row and event != :examples) || 
        !@examples_index || 
        index == @examples_index || 
        @included_rows.nil? || 
        @included_rows[index] || 
        index == @examples_index
      end

      def comments_before(index)
        while [:comment, :tag].index(@sexps[index - 1].event)
          index -= 1
        end
        index
      end
    end
  end
end
