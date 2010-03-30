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
          @first_scenario ||= @current_index
          @next_uncollected_scenario_index = @current_index
          @scenario_ok = line_match
        when :examples
          @table_state = :examples
          @examples_ok = line_match
          @included_rows = {}
        when :step
          @table_state = :step
          @scenario_ok ||= line_match
          @included_rows = nil
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
        if !@feature_added && @first_scenario
          @filtered_sexps += @sexps[0...@first_scenario]
          @feature_added = true
        end

        # Collect Scenario
        (@next_uncollected_scenario_index..@current_index).each do |sexp_index|
          sexp = @sexps[sexp_index]
          event = sexp[0]
          included = (event != :row) || @included_rows.nil? || @included_rows[sexp_index]
          @filtered_sexps << sexp if included
        end
        
        @next_uncollected_scenario_index = @current_index + 1
      end
    end
  end
end
