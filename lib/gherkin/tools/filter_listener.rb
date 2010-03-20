module Gherkin
  module Tools
    class FilterListener
      def initialize(listener, lines, name_regexen)
        @listener = listener
        @lines = lines
        @name_regexen = name_regexen

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
        when :scenario
          @feature ||= @current
          @current = []
          @current << sexp
          @line_match = nil
          @current_added = false
          
          name = args[1]
          name_match = no_filters? || @name_regexen.detect{|regex| name =~ regex}
          
          add_if_matched(sexp, name_match)
        when :eof
          @sexp_arrays << [sexp]
        else
          @current << sexp
          add_if_matched(sexp, false)
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

      def add_if_matched(sexp, name_match)
        line = sexp[-1]
        @line_match ||= no_filters? || @lines.index(line)

        matched = @line_match || name_match

        if matched
          if !@feature_added
            @sexp_arrays << @feature
            @feature_added = true
          end
          if !@current_added
            @sexp_arrays << @current
            @current_added = true
          end
        end
      end

      def no_filters?
        @lines.empty? && @name_regexen.empty?
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
