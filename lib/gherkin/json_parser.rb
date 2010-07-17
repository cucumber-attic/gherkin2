require 'json'
require 'gherkin/listener/formatter_listener'

module Gherkin
  class JSONParser

    def initialize(formatter)
      @formatter = formatter
    end

    def parse(src, feature_uri='unknown.json', line_offset=0)
      @listener = Listener::FormatterListener.new(@formatter)
      _parse(src, feature_uri, line_offset)
    end

    def parse_with_listener(src, listener)
      @listener = listener
      _parse(src, 'unknown.json', 0)
    end

    private

    def _parse(src, feature_uri, line_offset)
      @listener.location(feature_uri)
      feature = JSON.parse(src)

      comments_for(feature)
      tags_for(feature)
      multiline_event(feature)

      (feature["elements"] || []).each do |feature_element|
        parse_element(feature_element) 
      end

      @listener.eof
    end

    def parse_element(feature_element)
      comments_for(feature_element)
      tags_for(feature_element)
      multiline_event(feature_element)
      steps_for(feature_element)

      if feature_element["type"] == "scenario_outline"
        (feature_element["examples"] || []).each do |examples|
          comments_for(examples)
          tags_for(examples)
          multiline_event(examples)
          rows_for(examples['table'])
        end
      end
    end

    def comments_for(element)
      (element["comments"] || []).each do |comment|
        @listener.comment(comment['value'], comment['line'])
      end
    end

    def tags_for(element)
      (element["tags"] || []).each do |tag|
        @listener.tag(tag['name'], tag['line'])
      end
    end

    def steps_for(element)
      element["steps"].each do |step|
        comments_for(step)
        @listener.step(step["keyword"], step["name"], step['line'])
        multiline_arg_for(step)    
      end
    end

    def multiline_arg_for(element)
      if ma = element["multiline_arg"]
        case ma["type"]
        when "py_string"
          @listener.py_string(ma["value"], ma["line"])
        when "table"
          rows_for(ma["value"])
        end
      end
    end

    def rows_for(rows)
      (rows || []).each do |row|
        comments_for(row)
        @listener.row(cells_for(row), row['line'])
      end
    end

    def cells_for(row)
      row["cells"]
    end

    def multiline_event(element)
      if element["keyword"] 
        @listener.__send__(element['type'].to_sym, element["keyword"], element["name"] || "", element["description"] || "", element['line'])
      end
    end
  end
end
