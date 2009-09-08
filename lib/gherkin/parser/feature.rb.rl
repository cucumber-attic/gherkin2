module Gherkin
  module Parser
    class Feature
      %%{
        machine feature;
 
        action begin_content {
          @content_start = p
        }
      
        action store_feature_content {
          con = data[@content_start...@backup].pack("U*")
          con.strip!
          @listener.feature(@keyword, con, @current_line)
          p = @backup;
          @backup = nil
        }
      
        action store_scenario_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          @listener.scenario(@keyword, con, @current_line)
        }
      
        action store_step_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          @listener.step(@keyword, con, @current_line)
        }
        
        action store_comment_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          @listener.comment(con, @line_number)
        }
        
        action store_tag_content {
          con = data[@content_start...p].pack("U*")
          con.strip!
          @listener.tag(con, @current_line)
        }
  
        action inc_line_number {
          @line_number += 1
        }
 
        action current_line {
          @current_line = @line_number
        }

        action start_keyword {
          @keyword_start ||= p
        }
 
        action end_keyword {
          @keyword = data[@keyword_start...p].pack("U*").sub(/:$/,'')
          @keyword_start = nil
        }
 
        action backup {
          @backup = p
        }

        include feature_common "feature_common.rl"; 
      }%%
  
      def initialize(listener)
        @listener = listener
        %% write data;
      end
  
      def scan(data)
        data = data.unpack("U*") if data.is_a?(String)
        @line_number = 1
        eof = data.size
        %% write init;
        %% write exec;
      end
    end
  end
end
