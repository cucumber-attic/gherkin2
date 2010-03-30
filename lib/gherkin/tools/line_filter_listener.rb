module Gherkin
  module Tools
    class LineFilterListener
      def initialize(listener, lines)
        @listener, @lines = listener, lines
        @sexps = []
        @filtered_sexps = []

        @next_uncollected_scenario_index = 0
        @current_index = -1
      end

      def method_missing(event, *args)
        @current_index += 1
        args[0] = args[0].to_a if event == :row # Special JRuby handling
        sexp = [event] + args
        @sexps << sexp
        line = sexp[-1]
        line_match = @lines.index(line)

        case(event)
        when :tag
        when :comment
        when :feature
        when :background
        when :scenario, :scenario_outline
          @scenario_ok = line_match
          @first_scenario_index ||= @current_index
          @next_uncollected_scenario_index = @current_index
        when :examples
          @examples_ok = line_match
          @included_rows = {}
          @table_state = :examples
        when :step
          @scenario_ok ||= line_match
          @included_rows = nil
          @table_state = :step
        when :row
          case(@table_state)
          when :examples
            row_ok = line_match
            if @included_rows.empty?
              # The header row is always ok
              @included_rows[@current_index] = true
            else
              @included_rows[@current_index] = @scenario_ok || @examples_ok || line_match
            end
          when :step
            @scenario_ok ||= line_match
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

      def lines
        @filtered_sexps.map{|sexp| sexp[-1]}
      end

      def collect_filtered_sexps
        # Collect Feature
        if !@feature_added && @first_scenario_index
          while [:comment, :tag].index(@sexps[@first_scenario_index - 1][0])
            @first_scenario_index -= 1
          end
          @filtered_sexps += @sexps[0...@first_scenario_index]
          @feature_added = true
        end

        # Collect Scenario
        while [:comment, :tag].index(@sexps[@next_uncollected_scenario_index - 1][0])
          @next_uncollected_scenario_index -= 1
        end
        (@next_uncollected_scenario_index..@current_index).each do |sexp_index|
          sexp = @sexps[sexp_index]
          event = sexp[0]
          included = (event != :row) || @included_rows.nil? || @included_rows[sexp_index]
          @filtered_sexps << sexp if included
        end
        
        @next_uncollected_scenario_index = @current_index + 1
      end

      def replay
        filtered_sexps.each do |sexp|
          @listener.__send__(sexp[0], *sexp[1..-1])
        end
      end
    end
  end
end
