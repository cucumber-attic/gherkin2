require 'json'

module Gherkin
  class JSONLexer

    def initialize(listener)
      @listener = listener
    end

    def scan(src)
      feature = JSON.parse(src)

      comments_for(feature)
      tags_for(feature)
      multiline_event(:feature, feature)

      if feature["background"]
        comments_for(feature["background"])
        multiline_event(:background, feature["background"])
        steps_for(feature["background"])
      end

      feature["elements"].each do |feature_element|
        parse_element(feature_element) 
      end if feature["elements"]

      @listener.eof
    end

    private

    def parse_element(feature_element)
      comments_for(feature_element)
      tags_for(feature_element)
      case feature_element["type"]
      when "scenario" then parse_scenario(feature_element)
      when "scenario_outline" then parse_outline(feature_element)
      end
    end

    def parse_outline(scenario_outline)
      multiline_event(:scenario_outline, scenario_outline)
      steps_for(scenario_outline)
      scenario_outline["examples"].each do |examples|
        comments_for(examples)
        tags_for(examples)
        multiline_event(:examples, examples)
        rows_for(examples)
      end
    end

    def parse_scenario(scenario)
      multiline_event(:scenario, scenario)
      steps_for(scenario)
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

    def multiline_event(type, element)
      if element["keyword"] 
        @listener.__send__(type, element["keyword"], element["name"] || "", element["description"] || "", line_for(element))
      end
    end
  end
end
