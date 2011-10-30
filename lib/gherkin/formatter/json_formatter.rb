require 'json'
require 'gherkin/formatter/model'
require 'gherkin/native'
require 'base64'

module Gherkin
  module Formatter
    # This class doesn't really generate JSON - instead it populates an Array that can easily
    # be turned into JSON.
    class JSONFormatter
      native_impl('gherkin')
      
      include Base64
      
      def initialize(feature_hashes)
        raise "Must be an Array" unless Array===feature_hashes
        @feature_hashes = feature_hashes
      end

      def to_json
        @feature_hashes.to_json
      end

      def uri(uri)
        @uri = uri
      end

      def feature(feature)
        @feature_hash = feature.to_hash
        @feature_hash['uri'] = @uri
        @feature_hashes << @feature_hash
      end

      def background(background)
        feature_elements << background.to_hash
        @step_index = 0
      end

      def scenario(scenario)
        feature_elements << scenario.to_hash
        @step_index = 0
      end

      def scenario_outline(scenario_outline)
        feature_elements << scenario_outline.to_hash
        @step_index = 0
      end

      def examples(examples)
        all_examples << examples.to_hash
      end

      def step(step)
        current_steps << step.to_hash
      end

      def match(match)
        current_steps[@step_index]['match'] = match.to_hash
      end

      def result(result)
        current_steps[@step_index]['result'] = result.to_hash
        @step_index += 1
      end

      def last_step
        current_steps[-1]
      end

      def embedding(mime_type, data)
        embeddings << {'mime_type' => mime_type, 'data' => encode64s(data)}
      end

      def eof
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

      def current_steps
        feature_element['steps'] ||= []
      end

      def embeddings
        last_step['embeddings'] ||= []
      end

      def encode64s(data)
        # Strip newlines
        Base64.encode64(data).gsub(/\n/, '')
      end
    end
  end
end

