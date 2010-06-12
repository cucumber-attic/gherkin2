require 'json'
module Gherkin
  module Formatter
    class JSONFormatter
      def initialize(io)
        @io = io

      end

      def feature(keyword, name, line)
        @json_hash = {'keyword' => keyword, 'name' => name, 'elements' => []}
      end

      def scenario(keyword, name, line, location=nil)
        add_step_container(keyword, name, line)
      end

      def scenario_outline(keyword, name, line)
        add_step_container(keyword, name, line)
      end

      def examples(keyword, name, line)
        add_element(keyword, name, line)
      end

      def row(row, line)
        last_element['table'] ||= []
        last_element['table'] << row.to_a
      end

      def step(keyword, name, line, status=nil, exception=nil, arguments=nil, location=nil)
        last_element['steps'] << {'keyword' => keyword, 'name' => name, 'line' => line}
      end

      def eof
        @io.write(@json_hash.to_json)
      end

    private
      def add_element(keyword, name, line)
        @json_hash['elements'] << {'keyword' => keyword, 'name' => name, 'line' => line}
      end

      def add_step_container(keyword, name, line)
        add_element(keyword, name, line)
        last_element['steps'] = []
      end

      def last_element
        @json_hash['elements'][-1]
      end
    end
  end
end