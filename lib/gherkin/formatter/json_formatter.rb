require 'json'
require 'json/pure' # Needed to make JSON.generate work.
require 'gherkin/rubify'

module Gherkin
  module Formatter
    class JSONFormatter
      include Rubify
      
      def initialize(io)
        @io = io
      end

      def feature(statement, uri)
        @json_hash = {
          'comments' => statement.comments.to_a, 
          'tags' => statement.tags.to_a, 
          'keyword' => statement.keyword, 
          'name' => statement.name, 
          'description' => statement.description, 
          'uri' => uri
        }
      end

      def background(comments, keyword, name, description, line)
        background = {
          'comments' => comments.to_a, 
          'keyword' => keyword, 
          'name' => name, 
          'description' => description,
          'line' => line,
          'steps' => [],
        }
        @json_hash['background'] = background 
        @in_background = true
      end

      def scenario(comments, tags, keyword, name, description, line)
        @in_background = false
        add_step_container(comments, tags, keyword, name, description, line, 'scenario')
      end

      def scenario_outline(comments, tags, keyword, name, description, line)
        @in_background = false
        add_step_container(comments, tags, keyword, name, description, line, 'scenario_outline')
      end

      def examples(comments, tags, keyword, name, description, line, table)
        @table_container = add_examples(comments, tags, keyword, name, description, line)
        @table_container['table'] = to_hash_array(table)
      end

      def step(comments, keyword, name, line, multiline_arg, status, exception, arguments, stepdef_location)
        @table_container = {'comments' => comments.to_a, 'keyword' => keyword, 'name' => name, 'line' => line}.merge(step_arg_to_hash(multiline_arg))
        last_element['steps'] ||= []
        last_element['steps'] << @table_container
      end

      def eof
        @io.write(@json_hash.to_json)
      end

    private

      def element_hash(comments, tags, keyword, name, description, line, type=nil)
        element = {
          'comments' => comments.to_a, 
          'tags' => tags.to_a, 
          'keyword' => keyword, 
          'name' => name, 
          'description' => description,
          'line' => line,
        }
        element['type'] = type if type
        element
      end

      def add_element(comments, tags, keyword, name, description, line, type)
        element = element_hash(comments, tags, keyword, name, description, line, type)
        @json_hash['elements'] ||= []
        @json_hash['elements'] << element
        element
      end

      def add_examples(comments, tags, keyword, name, description, line)
        element = element_hash(comments, tags, keyword, name, description, line)
        last_element['examples'] ||= []
        last_element['examples'] << element
        element
      end

      def add_step_container(type, comments, tags, keyword, name, description, line)
        add_element(type, comments, tags, keyword, name, description, line)
        last_element['steps'] = []
      end

      def last_element
        if @in_background
          @json_hash['background']
        else
          @json_hash['elements'][-1]
        end
      end

      def to_hash_array(rows)
        rows.map do |row|
          {"cells" => row.cells.to_a, "comments" => row.comments.to_a, "line" => row.line}
        end
      end

      def step_arg_to_hash(multiline_arg)
        return {} if multiline_arg.nil?
        multiline_arg = rubify(multiline_arg)
        Array === multiline_arg ? {"table" => to_hash_array(multiline_arg) } : { "py_string" => multiline_arg }
      end
    end
  end
end

