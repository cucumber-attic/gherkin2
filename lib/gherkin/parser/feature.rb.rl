module Gherkin
  module Parser
    class Feature
      %%{
        machine feature;

        action accumulate_content {
          @content_start ||= p
        }
      
        action keyword_found {
          @keyword_start = p
        }

        action clear_content {
          @content_start = nil
        }

        action store_feature_content {
          con = data[@content_start...@keyword_start].pack("U*")
          con.strip!
          @listener.feature_found(con)
        }
      
        action store_scenario_content {
          con = data[@content_start...@keyword_start].pack("U*")
          con.strip!
          @listener.scenario_found(con)
        }
      
        action store_step_content {
          con = data[@content_start...@keyword_start].pack("U*")
          con.strip!
          @listener.step_found(con)
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
    end
  end
end
