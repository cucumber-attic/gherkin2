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
          rows_for(examples)
        end
      end
    end

    def comments_for(element)
      element["comments"].each do |comment|
        @listener.comment(comment, line_for(comment))
      end if element["comments"]
    end

    def tags_for(element)
      element["tags"].each do |tag|
        @listener.tag(tag, line_for(tag))
      end if element["tags"]
    end

    def steps_for(element)
      element["steps"].each do |step|
        comments_for(step)
        @listener.step(step["keyword"], step["name"], line_for(step))
        py_string_for(step)    
        rows_for(step)
      end
    end

    def py_string_for(element)
      @listener.py_string(element["py_string"], 0) if element["py_string"]
    end

    def rows_for(element)
      element["table"].each do |row|
        comments_for(row)
        @listener.row(cells_for(row), 0)
      end if element["table"]
    end

    def cells_for(row)
      row["cells"]
    end

    def line_for(element)
      element["line"].to_i || 0
    end

    def multiline_event(element)
      if element["keyword"] 
        @listener.__send__(element['type'].to_sym, element["keyword"], element["name"] || "", element["description"] || "", line_for(element))
      end
    end
  end
end
