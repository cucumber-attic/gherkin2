module Gherkin
  module Tools
    class FilterListener
      def initialize(listener)
        @listener = listener
        @sexps = []
      end

      def method_missing(m, *args)
        args[0] = args[0].map{|cell| cell} if m == :row
        @sexps << [m] + args
      end

      def eof
        @sexps.each do |sexp|
          @listener.__send__(sexp[0], *sexp[1..-1])
        end
      end
    end
  end
end
