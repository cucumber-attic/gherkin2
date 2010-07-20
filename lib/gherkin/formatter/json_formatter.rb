require 'json'
require 'json/pure' # Needed to make JSON.generate work.
require 'gherkin/formatter/model'
require 'gherkin/native'

module Gherkin
  module Formatter
    class JSONFormatter
      native_impl('gherkin')
      
      def initialize(io)
        @io = io
      end

      def feature(statement, uri)
        @feature_hash = statement_hash('feature', statement)
        @feature_hash['uri'] = uri
      end

      def background(statement)
        add_step_container('background', statement)
      end

      def scenario(statement)
        add_step_container('scenario', statement)
      end

      def scenario_outline(statement)
        add_step_container('scenario_outline', statement)
      end

      def examples(statement, examples_rows)
        table_container = add_examples(statement)
        table_container['table'] = to_hash_array(examples_rows)
      end

      def step(statement, multiline_arg, result)
        step = statement_hash(nil, statement).merge(step_arg_to_hash(multiline_arg))
        last_element['steps'] ||= []
        last_element['steps'] << step
      end

      def eof
        @io.write(@feature_hash.to_json)
      end

    private

      def statement_hash(type, statement)
        element = {
          'keyword' => statement.keyword, 
          'name' => statement.name, 
          'line' => statement.line,
        }
        element['type'] = type if type
        add_comments(element, statement)
        add_tags(element, statement)
        element['description'] = statement.description if statement.description
        element
      end

      def add_comments(element, comment_holder)
        if comment_holder.comments && comment_holder.comments.any?
          element['comments'] = comment_holder.comments.map do |comment|
            {'value' => comment.value, 'line' => comment.line}
          end
        end
      end

      def add_tags(element, statement)
        if statement.tags && statement.tags.any?
          element['tags'] = statement.tags.map do |tag|
            {'name' => tag.name, 'line' => tag.line}
          end
        end
      end

      def add_examples(statement)
        element = statement_hash('examples', statement)
        last_element['examples'] ||= []
        last_element['examples'] << element
        element
      end

      def add_step_container(type, statement)
        add_element(type, statement)
        last_element['steps'] = []
      end

      def add_element(type, statement)
        element = statement_hash(type, statement)
        @feature_hash['elements'] ||= []
        @feature_hash['elements'] << element
      end

      def last_element
        @feature_hash['elements'][-1]
      end

      def to_hash_array(rows)
        rows.map do |row|
          e = {"cells" => row.cells, "line" => row.line}
          add_comments(e, row)
          e
        end
      end

      def step_arg_to_hash(multiline_arg)
        case multiline_arg
        when Array
          {
            "multiline_arg" => {
              "type" => "table",
              "value" => to_hash_array(multiline_arg)
            }
          }
        when Model::PyString
          {
            "multiline_arg" => {
              "type" => "py_string",
              "value" => multiline_arg.value,
              "line" => multiline_arg.line
            }
          }
        else
          {}
        end
      end
    end
  end
end

