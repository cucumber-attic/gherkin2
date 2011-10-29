require 'json'
require 'gherkin/formatter/model'
require 'gherkin/native'
require 'base64'

module Gherkin
  module Formatter
    class JSONFormatter
      native_impl('gherkin')
      
      include Base64
      attr_reader :gherkin_object
      
      # Creates a new instance that writes the resulting JSON to +io+.
      # If +io+ is nil, the JSON will not be written, but instead a Ruby
      # object can be retrieved with #gherkin_object
      def initialize(io)
        @io = io
        @array = []
      end

      def uri(uri)
        @uri = uri
      end

      def feature(feature)
        @gherkin_object = feature.to_hash
        @gherkin_object['uri'] = @uri
        @array << @gherkin_object
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
        @io.write(@array.to_json) if @io
      end

    private

      def feature_elements
        @gherkin_object['elements'] ||= []
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

