module Gherkin
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
        @listener.feature(con)
      }
      
      action store_scenario_content {
        con = data[@content_start...@keyword_start].pack("U*")
        con.strip!
        @listener.scenario(con)
      }
      
      action store_step_content {
        con = data[@content_start...@keyword_start].pack("U*")
        con.strip!
        @listener.given(con)
      }

      include feature_common "feature_common.rl"; 
    }%%
  
    def initialize
      %% write data;
    end
  
    def scan(data, listener)
      @listener = listener
      data = data.unpack("U*") if data.is_a?(String)
      eof = data.size
      %% write init;
      %% write exec;
    end
  end
end
