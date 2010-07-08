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
        @json_hash = statement_hash(nil, statement)
        @json_hash['uri'] = uri
      end

      def background(statement)
        @json_hash['background'] = statement_hash(nil, statement) 
        @in_background = true
      end

      def scenario(statement)
        @in_background = false
        add_step_container('scenario', statement)
      end

      def scenario_outline(statement)
        @in_background = false
        add_step_container('scenario_outline', statement)
      end

      def examples(statement, examples_rows)
        @table_container = add_examples(statement)
        @table_container['table'] = to_hash_array(examples_rows)
      end

      def step(statement, multiline_arg, result)
        @table_container = statement_hash(nil, statement).merge(step_arg_to_hash(multiline_arg))
        last_element['steps'] ||= []
        last_element['steps'] << @table_container
      end

      def eof
        @io.write(@json_hash.to_json)
      end

    private

      def statement_hash(type, statement)
        element = {
          'comments' => statement.comments.map{|comment| comment.value}, 
          'tags' => statement.tags.map{|tag| tag.name}, 
          'keyword' => statement.keyword, 
          'name' => statement.name, 
          'description' => statement.description,
          'line' => statement.line,
        }
        element['type'] = type
        compact(element)
      end

      def add_element(type, statement)
        element = statement_hash(type, statement)
        @json_hash['elements'] ||= []
        @json_hash['elements'] << element
        element
      end

      def add_examples(statement)
        element = statement_hash(nil, statement)
        last_element['examples'] ||= []
        last_element['examples'] << element
        element
      end

      def add_step_container(type, statement)
        add_element(type, statement)
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
          compact({"cells" => row.cells.to_a, "comments" => row.comments.map{|comment| comment.value}, "line" => row.line})
        end
      end

      def step_arg_to_hash(multiline_arg)
        return {} if multiline_arg.nil?
        multiline_arg = rubify(multiline_arg)
        Array === multiline_arg ? {"table" => to_hash_array(multiline_arg) } : { "py_string" => multiline_arg.value }
      end

      def compact(hash)
        hash.reject{|k,v| [[], "", nil].index(v)}
      end
    end
  end
end

