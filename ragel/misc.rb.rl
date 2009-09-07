module Gherkin
  module Parser
    class Misc
      %%{
        machine misc;
        
        action start_tag {
          tag_start = p
        }
        
        action end_tag {
          tag = data[tag_start...p].pack("U*")
          @listener.tag(tag)
        }
        
        Tag = ( '@' [^@\r\n\t ]+ ) >start_tag %end_tag ;
        Tags = Tag space*;
        main := Tags+;
      }%%
      
      def initialize(listener)
        @listener = listener
        %% write data;
      end
      
      def scan(data)
        data = data.unpack("U*") if data.is_a?(String)
        eof = data.length
        
        %% write init;
        %% write exec;
      end
    end
  end
end