require 'json'
module Gherkin
  module Formatter
    class JSONFormatter
      def initialize(io)
        @io = io
      end

      def comment(content, line)
        @comments ||= []
        @comments << content
      end

      def tag(name, line)
        @tags ||= []
        @tags << name
      end

      def feature(keyword, name, line)
        @json_hash = {'keyword' => keyword, 'name' => name, 'line' => line, 'elements' => []}
        add_comments_and_tags_to!(@json_hash)
      end

      def scenario(keyword, name, line, location=nil)
        add_step_container(keyword, name, line)
      end

      def scenario_outline(keyword, name, line)
        add_step_container(keyword, name, line)
      end

      def examples(keyword, name, line)
        @table_container = add_element(keyword, name, line)
      end

      def row(row, line)
        @table_container['table'] ||= []
        @table_container['table'] << row.to_a
      end

      def py_string(string, line)
        @table_container['py_string'] = string
      end

      def step(keyword, name, line, status=nil, exception=nil, arguments=nil, location=nil)
        @table_container = {'keyword' => keyword, 'name' => name, 'line' => line}
        add_comments_and_tags_to!(@table_container)
        last_element['steps'] << @table_container
      end

      def eof
        @io.write(@json_hash.to_json)
      end

    private

      def add_element(keyword, name, line)
        element = {'keyword' => keyword, 'name' => name, 'line' => line}
        add_comments_and_tags_to!(element)
        @json_hash['elements'] << element
        element
      end

      def add_step_container(keyword, name, line)
        add_element(keyword, name, line)
        last_element['steps'] = []
      end

      def last_element
        @json_hash['elements'][-1]
      end

      def add_tags_to!(hash)
        if(@tags)
          hash['tags'] = @tags
          @tags = nil
        end
      end

      def add_comments_and_tags_to!(hash)
        if(@comments)
          hash['comments'] = @comments
          @comments = nil
        end
        add_tags_to!(hash)
      end
    end
  end
end