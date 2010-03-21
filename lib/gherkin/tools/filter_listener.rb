require 'gherkin/tools/tag_expression'

module Gherkin
  module Tools
    class FilterListener
      def initialize(listener, lines, name_regexen, tag_expressions)
        @listener, @lines, @name_regexen, @tag_expressions = listener, lines, name_regexen, TagExpression.new(tag_expressions)

        @sexp_arrays = []
        @current = []
        @tags = []
        @feature_added = false
        @needs_add = true
      end

      def method_missing(event, *args)
        args[0] = args[0].to_a if event == :row # Special JRuby handling
        sexp = [event] + args
        case(event)
        when :tag
          @current << sexp
          @tags << '@' + sexp[1]
        when :feature
          @current << sexp
        when :scenario, :scenario_outline
          @feature ||= @current
          @current = []
          @current << sexp
          @line_match = nil
          @current_added = false
          
          name = args[1]
          name_match = no_filters? || @name_regexen.detect{|regex| name =~ regex}

          tag_match = no_filters? || (@tags.any? && @tag_expressions.eval(@tags))
          @tags = []
          
          add_if_matched(sexp, name_match, tag_match)
        when :examples
          @in_example = true
          @example_header_added = false
          @current << sexp
          add_if_matched(sexp, false, false)
        when :row
          if @in_example
            line = sexp[-1]
            @line_match ||= no_filters? || @lines.index(line)

            if !@example_header_added || @line_match
              @current << sexp
              @example_header_added = true
            end
          else
            @current << sexp
          end
          add_if_matched(sexp, false, false)
        when :eof
          @sexp_arrays << [sexp]
        else
          @current << sexp
          add_if_matched(sexp, false, false)
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

      def add_if_matched(sexp, name_match, tag_match)
        line = sexp[-1]
        @line_match ||= no_filters? || @lines.index(line)
        matched = @line_match || name_match || tag_match

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
        @lines.empty? && @name_regexen.empty? && @tag_expressions.empty?
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
