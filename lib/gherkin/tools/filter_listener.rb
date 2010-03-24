require 'gherkin/tools/tag_expression'

module Gherkin
  module Tools
    class FilterListener
      def initialize(listener, lines, name_regexen, tag_expression)
        raise "lines!!!" if lines.nil?
        @listener, @lines, @name_regexen, @tag_expression = listener, lines, name_regexen, tag_expression
        @sexps = []
        @results = []
        @i = 0
      end

      def method_missing(event, *args)
        args[0] = args[0].to_a if event == :row # Special JRuby handling
        sexp = [event] + args
        @sexps << sexp
        line = sexp[-1]
        line_match = @lines.index(line)
        ok = no_filters? || line_match

        case(event)
        when :tag
          @tags ||= []
          @tags << '@'+args[0]
          @tag_index ||= @i
        when :comment
          @comment_index ||= @i
          ok ||= last_scenario_ok?
        when :feature
          @feature_tags = @tags || []
          @feature_index = @i
        when :scenario
          ok = ok || name_match?(name=args[1]) || tag_match?
          @scenario_index = @i
          if ok
            ensure_comments_added
            ensure_tags_added
          end
        when :scenario_outline
          ok ||= name_match?(name=args[1])
          @scenario_index = @i
          ensure_tags_added if ok
          
          @examples_index = nil
        when :examples
          @examples_index = @i
          @header_row_index = nil
        when :step
          @tags = nil
          @last_step_index = @i
          ok ||= last_scenario_ok?
          ensure_scenario_added if ok
        when :row
          if @examples_index
            ok ||= last_scenario_ok?
            @header_row_index ||= @i
            ensure_examples_added if ok
          end
        when :eof
          @results[@i] = true
          replay if @listener
          return
        else
          super
        end
        @results[@i] = ok
        @i += 1
      end

      def name_match?(name)
        @name_regexen.detect{|regex| name =~ regex}
      end

      def tag_match?
        tags = (@feature_tags + (@tags || [])).uniq
        !@tag_expression.empty? && @tag_expression.eval(*tags)
      end

      def ensure_comments_added
        if @comment_index && !@results[@comment_index]
          (@comment_index...@i).each do |i|
            @results[i] = true
          end
        end
      end

      def ensure_tags_added
        if @tag_index && !@results[@tag_index]
          (@tag_index...@i).each do |i|
            @results[i] = true
          end
        end
      end

      def ensure_feature_added
        if !@results[@feature_index]
          (0..@feature_index).each do |i|
            @results[i] = true
          end
        end
      end

      def last_scenario_ok?
        @scenario_index && @results[@scenario_index] && @results[@scenario_index] != :implicit
      end

      def ensure_scenario_added(how=true)
        ensure_feature_added
        if !last_scenario_ok?
          (@scenario_index..@last_step_index).each do |i|
            @results[i] = how
          end
        end
      end

      def last_examples_ok?
        @results[@examples_index]
      end

      def ensure_examples_added
        ensure_scenario_added(:implicit)
        if !last_examples_ok?
          (@examples_index..@header_row_index).each do |i|
            @results[i] = true
          end
        end
      end

      def filtered_sexps
        raise "Bad count #{@results.length} != #{@sexps.length}" if @results.length != @sexps.length
        filtered = []
        @results.each_with_index do |result, i|
          if result
            filtered << @sexps[i]
          end
        end
        filtered
      end
      
      def lines
        filtered_sexps.map{|sexp| sexp[-1]}
      end

      def no_filters?
        @lines.empty? && @name_regexen.empty? && @tag_expression.empty?
      end

      def replay
        filtered_sexps.each do |sexp|
          @listener.__send__(sexp[0], *sexp[1..-1])
        end
      end
    end
  end
end
