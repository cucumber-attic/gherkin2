require 'gherkin/tag_expression'

module Gherkin
  module Formatter
    class TagFilter
      def initialize(tags)
        @tag_expression = TagExpression.new(tags)
      end

      def eval(tags, names, ranges)
        @tag_expression.evaluate(tags.uniq)
      end

      def filter_table_body_rows(rows)
        rows
      end
    end
  end
end
