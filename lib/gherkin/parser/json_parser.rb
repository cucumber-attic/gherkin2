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

        @i18n_language = Gherkin::I18n.get(feature["language"] || "en" )

        tags_for(feature)
        @listener.feature(keyword_for("feature", feature), feature["name"], line_for(feature)) if feature["name"]

        if feature["background"]
          @listener.background(keyword_for("background", feature["background"]), feature["background"]["name"], line_for(feature["background"]))
          steps_for(feature["background"])
        end

        feature["elements"].each do |feature_element|
          parse_element(feature_element) 
        end if feature["elements"]

        @listener.eof
      end

      private

      def parse_element(feature_element)
        case feature_element["type"]
        when "Scenario" then parse_scenario(feature_element)
        when "Scenario Outline" then parse_outline(feature_element)
        end
      end

      def parse_outline(scenario_outline)
        tags_for(scenario_outline)
        @listener.scenario_outline(keyword_for("scenario_outline", scenario_outline), scenario_outline["name"], line_for(scenario_outline) )
        steps_for(scenario_outline)
        scenario_outline["examples"].each do |examples|
          tags_for(examples)
          @listener.examples(keyword_for("examples", examples), examples["name"], line_for(examples))
          rows_for(examples)
        end
      end

      def parse_scenario(scenario)
        tags_for(scenario)
        @listener.scenario(keyword_for("scenario", scenario), scenario["name"], line_for(scenario))
        steps_for(scenario)
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
          @listener.row(cells_for(row), 0)
        end if element["table"]
      end

      def cells_for(row)
        row["cells"].inject([]) { |col, ele| col << ele["text"] }
      end

      def line_for(element)
        if element["line"]
          element["line"]
        elsif element["file_colon_line"]
          element["file_colon_line"].split(':')[1].to_i
        else
          0
        end
      end

      def keyword_for(gherkin_keyword, element)
        element["keyword"] || i18n_language.keywords(gherkin_keyword).reject { |kw| kw == "* " }.first
      end
    end
  end
end
