module Gherkin
  module Formatter
    class LineFilter
      def initialize(lines)
        @lines = lines
      end

      def eval(ranges)
        @lines.detect do |line|
          ranges.detect do |range|
            range.include?(line)
          end
        end
      end
    end
  end
end