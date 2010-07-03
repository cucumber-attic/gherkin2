module Gherkin
  module Formatter
    class LineFilter
      def initialize(lines)
        @lines = lines
      end

      def eval(lines)
        @lines.detect do |l1| 
          lines.detect do |l2|
            l1 == l2
          end
        end
      end
    end
  end
end