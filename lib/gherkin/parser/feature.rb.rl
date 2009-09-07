module Gherkin
  module Parser
    class Feature
      %%{
        machine feature;

        action begin_content {
          @content_start ||= p
        }
      
        action keyword {
          @keyword_start = p
        }

        action clear_content {
          @content_start = nil
        }

        action store_feature_content {
          @listener.feature(content(data))
        }

        action store_scenario_content {
          @listener.scenario(content(data))
        }

        action store_step_content {
          @listener.step(content(data))
        }

        include feature_common "feature_common.rl"; 
      }%%
  
      def initialize(listener)
        @listener = listener
        %% write data;
      end
  
      def scan(data)
        data = data.unpack("U*") if data.is_a?(String)
        eof = data.size
        %% write init;
        %% write exec;
      end

      def content(data)
        data[@content_start...@keyword_start].pack("U*").strip
      end
    end
  end
end
