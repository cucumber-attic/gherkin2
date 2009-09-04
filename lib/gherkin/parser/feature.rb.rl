module Gherkin
  module Parser
    class Feature
      %%{
        machine feature;

        action begin_content {
          @content_start = p
        }
      
        action store_feature_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          if $debug  
            puts "FOUND FEATURE CONTENT"
            puts con
          end
          @listener.feature(con)
        }
      
        action store_scenario_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          if $debug
            puts "FOUND SCENARIO CONTENT"
            puts con
          end
          @listener.scenario(con)
        }
      
        action store_step_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          if $debug
            puts "FOUND STEP CONTENT"
            puts con
          end
          @listener.step(con)
        }
        
        action store_comment_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          if $debug
            puts "FOUND STEP CONTENT"
            puts con
          end
          @listener.comment(con)
        }
        
        action store_tag_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          if $debug
            puts "FOUND TAG CONTENT"
            puts con
          end
          @listener.tag(con)
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
