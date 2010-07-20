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

      def uri(uri)
      end

      def feature(feature)
        @feature_hash = feature.to_hash
      end

      def background(background)
        feature_elements << background.to_hash
      end

      def scenario(scenario)
        feature_elements << scenario.to_hash
      end

      def scenario_outline(scenario_outline)
        feature_elements << scenario_outline.to_hash
      end

      def examples(examples)
        all_examples << examples.to_hash
      end

      def step(step)
        steps << step.to_hash
      end

      def eof
        @io.write(@feature_hash.to_json)
      end

    private

      def feature_elements
        @feature_hash['elements'] ||= []
      end

      def feature_element
        feature_elements[-1]
      end

      def all_examples
        feature_element['examples'] ||= []
      end

      def steps
        feature_element['steps'] ||= []
      end
    end
  end
end

