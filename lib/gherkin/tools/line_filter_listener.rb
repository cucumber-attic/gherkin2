require 'gherkin/tools/sexp'

module Gherkin
  module Tools
    class LineFilterListener
      def initialize(listener, lines)
        @listener, @lines = listener, lines
        @sexps = []
        @comment_buffer = []
        @feature_buffer = []
        @scenario_buffer = []
        @examples_buffer = []
        @examples_rows_buffer = []

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
          @comment_buffer = []
          @feature_buffer << sexp
        when :background
        when :scenario, :scenario_outline
          replay_examples_rows_buffer
          @scenario_buffer = @comment_buffer
          @comment_buffer = []
          @scenario_buffer << sexp
          @scenario_ok = line_match?(sexp)
          @examples_ok = false
        when :examples
          replay_examples_rows_buffer
          
          @examples_buffer = @comment_buffer + [sexp]
          @comment_buffer = []
          @examples_rows_buffer = []
          @examples_ok = line_match?(sexp)
          @table_state = :examples
        when :step
          @scenario_ok ||= line_match?(sexp)
          @scenario_buffer << sexp
          @table_state = :step
        when :row
          case(@table_state)
          when :examples
            unless header_row_already_buffered?
              @examples_buffer << sexp
              @examples_ok = true if line_match?(sexp)
            else
              @examples_rows_buffer << sexp if @scenario_ok || @examples_ok || line_match?(sexp)
            end
          when :step
            @scenario_buffer << sexp
            @scenario_ok ||= line_match?(sexp)
          else
            raise "BAD STATE"
          end
        when :eof
          replay_examples_rows_buffer
          sexp.replay(@listener)
          return
        else
          super
        end

        if @lines.empty?
          sexp.replay(@listener)
        elsif @scenario_ok || @examples_ok || line_match?(sexp)
          replay_buffers
        end
      end
      
      def header_row_already_buffered?
        return false unless @examples_buffer.any?
        @examples_buffer[-1].event == :row
      end
      
      def line_match?(sexp)
        !!@lines.index(sexp.line)
      end
      
      def replay_buffers
        replay_feature_buffer
        replay_scenario_buffer
      end

      def replay_examples_rows_buffer
        if @examples_rows_buffer.any?
          (@examples_buffer + @examples_rows_buffer).each do |sexp|
            sexp.replay(@listener)
          end
          @examples_rows_buffer = []
        end
      end
      
      def replay_feature_buffer
        if @feature_buffer.any?
          @feature_buffer.each{|sexp| sexp.replay(@listener)}
          @feature_buffer = []
        end
      end
      
      def replay_scenario_buffer
        if @scenario_buffer.any?
          @scenario_buffer.each do |sexp| 
            sexp.replay(@listener) if included2?(sexp.event)
          end
          @scenario_buffer = [] 
        end
      end

      def included?(event, index)
        (event != :row and event != :examples) || 
        !@examples_index || 
        index == @examples_index || 
        @included_rows.nil? || 
        @included_rows[index] || 
        index == @examples_index
      end

      def included2?(event)
        (event != :row and event != :examples) || 
        !@examples_index || 
        @included_rows.nil?
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
