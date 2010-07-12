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

      def filter_table_body_rows(rows)
        body = rows.to_a[1..-1].select do |row|
          @lines.detect do |line|
            row.line == line
          end
        end
        [rows[0]] + body
      end
    end
  end
end