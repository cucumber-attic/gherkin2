module Gherkin
  module Tools
    class FilterListener
      def initialize(listener, lines)
        @listener = listener
        @lines = lines
        @sexps = []
      end

      def method_missing(m, *args)
        args[0] = args[0].map{|cell| cell} if m == :row
        @sexps << [m] + args
      end

      def eof
        sexps = @sexps.reverse.reject do |sexp|
          !accept?(sexp)
        end.reverse
        
        sexps.each do |sexp|
          @listener.__send__(sexp[0], *sexp[1..-1])
        end
      end

      private
      
      def accept?(sexp)
        line = sexp[-1]
        line_match = @lines.empty? || line == @lines[0]
        
        accepted = line_match || @accept_next
        @accept_next = accepted
        
        accepted
      end
    end
  end
end
