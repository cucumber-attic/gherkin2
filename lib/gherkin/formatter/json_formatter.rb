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
      
      def initialize(io)
        raise "Must be writeable" unless io.respond_to?(:write)
        @io = io
        @feature_hashes = []
        @current_step_or_hook = nil
      end

      def done
        @io.write(@feature_hashes.to_json)
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
        @current_step_or_hook = step.to_hash
        steps << @current_step_or_hook
      end

      def match(match)
        @current_step_or_hook['match'] = match.to_hash
      end

      def result(result)
        @current_step_or_hook['result'] = result.to_hash
      end
      
      def append_duration(timestamp)
      	#check to make sure result exists (scenario outlines do not have results yet)
      	if !@current_step_or_hook['result'].nil?
        	#convert to nanoseconds
        	timestamp = timestamp * 1000000000
        	rshash = @current_step_or_hook['result'].to_hash
        	rshash['duration'] = timestamp.to_i
        	@current_step_or_hook['result'] = rshash
       	end
      end

      def before(match, result)
        add_hook(match, result, "before")
      end

      def after(match, result)
        add_hook(match, result, "after")
      end

      def embedding(mime_type, data)
        embeddings << {'mime_type' => mime_type, 'data' => encode64s(data)}
      end

      def write(text)
        output << text
      end

      def eof
      end

    private

      def add_hook(match, result, hook)
        hooks = feature_element[hook] ||= []
        hooks << {'match' => match.to_hash, 'result' => result.to_hash}
      end

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

      def embeddings
        @current_step_or_hook['embeddings'] ||= []
      end

      def output
        @current_step_or_hook['output'] ||= []
      end

      def encode64s(data)
        # Strip newlines
        Base64.encode64(data).gsub(/\n/, '')
      end
    end
  end
end

