module Gherkin
  module Formatter
    class TagCountFormatter
      attr_reader :tag_counts
      
      def initialize(formatter)
        @formatter = formatter
        @tag_counts = Hash.new{|hash, tag_name| hash[tag_name] = []}
      end

      def feature(comments, tags, keyword, name, description, uri)
        @feature_tags = tags
        @uri = uri
      end

      def scenario(comments, tags, keyword, name, description, line)
        record_tags((@feature_tags.to_a + tags.to_a).uniq, line)
        @formatter.__send__(:scenario, comments, tags, keyword, name, description, line)
      end

      def scenario_outline(comments, tags, keyword, name, description, line)
        @scenario_outline_tags = tags
        @formatter.__send__(:scenario_outline, comments, tags, keyword, name, description, line)
      end

      def examples(comments, tags, keyword, name, description, line, examples_table)
        record_tags((@feature_tags.to_a + @scenario_outline_tags.to_a + tags.to_a).uniq, line)
        @formatter.__send__(:examples, tags, keyword, name, description, line)
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
