require 'json'
require 'gherkin/i18n'

module Gherkin
  module Parser
    class JSONParser
      attr_reader :i18n_language

      def initialize(listener)
        @listener = listener
      end

      def parse(src)
        feature = JSON.parse(src)

        comments_for(feature)
        tags_for(feature)
        @listener.feature(keyword_for("feature", feature), feature["name"], feature["description"], line_for(feature)) if feature["name"]

        if feature["background"]
          comments_for(feature["background"])
          @listener.background(keyword_for("background", feature["background"]), feature["background"]["name"], feature["background"]["description"], line_for(feature["background"]))
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
        @listener.scenario_outline(keyword_for("scenario_outline", scenario_outline), scenario_outline["name"], scenario_outline["description"], line_for(scenario_outline) )
        steps_for(scenario_outline)
        scenario_outline["examples"].each do |examples|
          comments_for(examples)
          tags_for(examples)
          @listener.examples(keyword_for("examples", examples), examples["name"], examples["description"], line_for(examples))
          rows_for(examples)
        end
      end

      def parse_scenario(scenario)
        @listener.scenario(keyword_for("scenario", scenario), scenario["name"], scenario["description"], line_for(scenario))
        steps_for(scenario)
      end

      def comments_for(element)
        element["comments"].each do |comment|
          @listener.comment(comment, 0)
        end if element["comments"]
      end

      def tags_for(element)
        element["tags"].each do |tag|
          @listener.tag(tag, 0)
        end if element["tags"]
      end

      def steps_for(element)
        element["steps"].each do |step|
          @listener.step(keyword_for("given",step), step["name"], line_for(step))
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
        row["cells"].join(",")
      end

      def line_for(element)
        element["line"] || 0
      end

      def keyword_for(gherkin_keyword, element)
        element["keyword"] || "Uh Oh"
      end
    end
  end
end
