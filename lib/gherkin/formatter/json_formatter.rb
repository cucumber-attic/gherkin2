require 'json'
module Gherkin
  module Formatter
    class JSONFormatter
      def initialize(io)
        @io = io
      end

      def feature(comments, tags, keyword, name, uri)
        @json_hash = {'comments' => comments, 'tags' => tags, 'keyword' => keyword, 'name' => name, 'uri' => uri}
      end

      def scenario(comments, tags, keyword, name, line)
        add_step_container(comments, tags, keyword, name, line)
      end

      def scenario_outline(comments, tags, keyword, name, line)
        add_step_container(comments, tags, keyword, name, line)
      end

      def examples(comments, tags, keyword, name, line, rows)
        @table_container = add_element(comments, tags, keyword, name, line)
        @table_container['table'] = rows
      end

      def py_string(comments, string, line)
        @table_container['py_string'] = string
      end

      def step(comments, keyword, name, line, multiline_arg, status=nil, exception=nil, arguments=nil, stepdef_location=nil)
        @table_container = {'comments' => comments, 'keyword' => keyword, 'name' => name, 'line' => line, 'multiline_arg' => multiline_arg}
        last_element['steps'] ||= []
        last_element['steps'] << @table_container
      end

      def eof
        @io.write(@json_hash.to_json)
      end

    private

      def add_element(comments, tags, keyword, name, line)
        element = {'comments' => comments, 'tags' => tags, 'keyword' => keyword, 'name' => name, 'line' => line}
        @json_hash['elements'] ||= []
        @json_hash['elements'] << element
        element
      end

      def add_step_container(comments, tags, keyword, name, line)
        add_element(comments, tags, keyword, name, line)
        last_element['steps'] = []
      end

      def last_element
        @json_hash['elements'][-1]
      end
    end
  end
end