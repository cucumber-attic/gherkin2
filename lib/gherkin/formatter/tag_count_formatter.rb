module Gherkin
  module Formatter
    class TagCountFormatter
      def initialize(formatter, tag_counts)
        @formatter = formatter
        @tag_counts = tag_counts
      end

      def uri(uri)
        @uri = uri
      end

      def feature(statement)
        @feature_tags = statement.tags
        @formatter.feature(statement)
      end

      def scenario(statement)
        record_tags((@feature_tags.to_a + statement.tags.to_a).uniq, statement.line)
        @formatter.scenario(statement)
      end

      def scenario_outline(statement)
        @scenario_outline_tags = statement.tags
        @formatter.scenario_outline(statement)
      end

      def examples(examples)
        record_tags((@feature_tags.to_a + @scenario_outline_tags.to_a + examples.tags.to_a).uniq, examples.line)
        @formatter.examples(examples)
      end

    private

      def record_tags(tags, line)
        tags.each do |tag|
          @tag_counts[tag.name] ||= []
          @tag_counts[tag.name] << "#{@uri}:#{line}"
        end
      end

      def method_missing(*args)
        @formatter.__send__(*args)
      end
    end
  end
end
