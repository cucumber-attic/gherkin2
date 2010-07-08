module Gherkin
  module Formatter
    class TagCountFormatter
      attr_reader :tag_counts
      
      def initialize(formatter)
        @formatter = formatter
        @tag_counts = Hash.new{|hash, tag_name| hash[tag_name] = []}
      end

      def feature(statement, uri)
        @feature_tags = statement.tags
        @uri = uri
      end

      def scenario(statement)
        record_tags((@feature_tags.to_a + statement.tags.to_a).uniq, statement.line)
        @formatter.__send__(:scenario, statement)
      end

      def scenario_outline(statement)
        @scenario_outline_tags = statement.tags
        @formatter.__send__(:scenario_outline, statement)
      end

      def examples(statement, examples_rows)
        record_tags((@feature_tags.to_a + @scenario_outline_tags.to_a + statement.tags.to_a).uniq, statement.line)
        @formatter.__send__(:examples, statement, examples_rows)
      end

    private

      def record_tags(tags, line)
        tags.each do |tag|
          @tag_counts[tag.name] << "#{@uri}:#{line}"
        end
      end

      def method_missing(*args)
        @formatter.__send__(*args)
      end
    end
  end
end
