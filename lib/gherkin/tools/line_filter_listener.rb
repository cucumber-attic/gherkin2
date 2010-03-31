module Gherkin
  module Tools
    class LineFilterListener
      class Sexp < Array
        def initialize(*args)
          super
          self[1] = self[1].to_a if event == :row # Special JRuby handling
        end
        
        def event
          self[0]
        end
        
        def line
          self[-1]
        end
      end

      def initialize(listener, lines)
        @listener, @lines = listener, lines
        @sexps = []
        @filtered_sexps = []

        @next_uncollected_scenario_index = 0
        @current_index = -1
      end

      def method_missing(*args)
        sexp = Sexp.new(args)
        @sexps << sexp

        @current_index += 1
        case(sexp.event)
        when :tag
        when :comment
        when :feature
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
          @filtered_sexps << sexp
          replay if @listener
          return
        else
          super
        end

        if @lines.empty?
          @filtered_sexps << sexp
        elsif @scenario_ok || @examples_ok || row_ok
          collect_filtered_sexps
        end
      end
      
      def line_match?(sexp)
        @lines.index(sexp.line)
      end

      def lines
        @filtered_sexps.map{ |sexp| sexp.line }
      end

      def collect_filtered_sexps
        # Collect Feature
        if !@feature_added && @first_scenario_index
          @first_scenario_index = comments_before(@first_scenario_index)
          @filtered_sexps += @sexps[0...@first_scenario_index]
          @feature_added = true
        end

        # Collect Scenario
        @next_uncollected_scenario_index = comments_before(@next_uncollected_scenario_index)
        (@next_uncollected_scenario_index..@current_index).each do |sexp_index|
          sexp = @sexps[sexp_index]
          @filtered_sexps << sexp if included?(sexp.event, sexp_index)
        end
        
        @next_uncollected_scenario_index = @current_index + 1
      end

      def included?(event, index)
        (event != :row and event != :examples) || !@examples_index || index == @examples_index || @included_rows.nil? || @included_rows[index] || index == @examples_index
      end

      def comments_before(index)
        while [:comment, :tag].index(@sexps[index - 1].event)
          index -= 1
        end
        index
      end

      def replay
        filtered_sexps.each do |sexp|
          @listener.__send__(sexp[0], *sexp[1..-1])
        end
      end
    end
  end
end
