module Gherkin
  module Tools
    class FilterListener
      def initialize(listener, lines)
        @listener = listener
        @lines = lines

        @sexp_arrays = []
        @current = []
        @feature_added = false
        @needs_add = true
      end

      def method_missing(event, *args)
        args[0] = args[0].to_a if event == :row # Special JRuby handling
        sexp = [event] + args

        case(event)
        when :feature
          @current << sexp
        when :scenario, :eof
          @feature ||= @current
          @current = []
          @current << sexp
          @line_match = nil
          @current_added = false
          detect(sexp)
        else
          @current << sexp
          detect(sexp)
        end
      end

      def lines
#        p @sexp_arrays
        @sexp_arrays.map do |sexp_array| 
          sexp_array.map do |sexp| 
            sexp[-1]
          end
        end.flatten
      end

  private

      def detect(sexp)
        if(sexp[0] == :eof)
          @sexp_arrays << @current
          return
        end
        
        line = sexp[-1]
        @line_match ||= @lines.empty? || line == @lines[0]

        matched = @line_match

        if matched
          if !@feature_added
            @sexp_arrays << @feature
            @feature_added = true
          end
          if !@current_added
#            puts "ADDING #{@current.inspect}"
            @sexp_arrays << @current
            @current_added = true
          end
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
