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
        con = data[@content_start...@keyword_start].map { |x| [x].pack("U*")}.join
        con.strip!
        @listener.feature(con)
      }
      
      action store_scenario_content {
        con = data[@content_start...@keyword_start].map { |x| [x].pack("U*")}.join
        con.strip!
        @listener.scenario(con)
      }
      
      action store_step_content {
        con = data[@content_start...@keyword_start].map { |x| [x].pack("U*")}.join
        con.strip!
        @listener.given(con)
      }


      FEATURE = 'Feature:';
      SCENARIO = 'Scenario:';
      GIVEN = 'Given' | 'When';
      AND = 'And';
      THEN = 'Then'; 
 
      EOL = '\r'? '\n';

      KEYWORDS = (FEATURE | SCENARIO | GIVEN);

      feature = (
        start: (
          FEATURE space* ->feature_content
        ),

        feature_content: (
          any+ >accumulate_content ->feature_content |
          EOL+ space* SCENARIO >keyword_found @store_feature_content %clear_content ->scenario_content
        ),

        scenario_content: (
          any+ >accumulate_content ->scenario_content | 
          EOL+ space* GIVEN >keyword_found @store_scenario_content %clear_content ->step_content
        ),
 
        step_content: (
          any+ >accumulate_content ->step_content |
          EOL+ >keyword_found %store_step_content -> final
        )
      );     

      main := feature;
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
