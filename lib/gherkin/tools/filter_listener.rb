module Gherkin
  module Tools
    class FilterListener
      def initialize(listener, lines)
        @listener = listener
        @lines = lines

        @sexp_arrays = []

        @feature = []
        @feature_added = false

        @current = @feature
      end

      def method_missing(event, *args)
        args[0] = args[0].to_a if event == :row # Special JRuby handling
        sexp = [event] + args

        case(event)
        when :feature
          @current << sexp
        when :scenario
          @current = []
          @current << sexp
          detect_line(args)
        when :step
          @current << sexp
          detect_line(args)
        else
        end
      end

      def lines
        @sexp_arrays.map do |sexp_array| 
          sexp_array.map do |sexp| 
            sexp[-1]
          end
        end.flatten
      end

  private

      def detect_line(args)
        line = args[-1]
        @line_match ||= @lines.empty? || line == @lines[0]

        if @line_match && !@feature_element_added
          if !@feature_added
            @sexp_arrays << @feature
            @feature_added = true
          end
          @sexp_arrays << @current
          @feature_element_added = true
        end
      end

      def replay
        @sexp_arrays.each do |sexp_array|
          sexp_array.each do |sexp|
            @listener.__send__(sexp[0], *sexp[1..-1]) if @listener
          end
        end
      end
    end
  end
end
