require 'json'
module Gherkin
  module Formatter
    class JSONFormatter
      def initialize(io)
        @io = io

      end

      def feature(keyword, name, line)
        @json_hash = {'keyword' => keyword, 'name' => name}
      end

      def scenario(keyword, name, line, location=nil)
        @json_hash['elements'] ||= []
        @json_hash['elements'] << {'keyword' => keyword, 'name' => name, 'line' => line}
      end

      def step(keyword, name, line, status=nil, exception=nil, arguments=nil, location=nil)
        element = @json_hash['elements'][-1]
        element['steps'] ||= []
        element['steps'] << {'keyword' => keyword, 'name' => name, 'line' => line}
      end

      def eof
        @io.write(@json_hash.to_json)
      end
    end
  end
end