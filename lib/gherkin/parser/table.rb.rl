module Gherkin
  module Parser
    class Table
      %%{      
        machine table;

        action initialize {
          current_row = []
        }

        action store_row {
          @rows << current_row
        }

        action accumulate_content {
          @con ||= ''
          @con += [data[p]].pack("U*")
        }

        action store_content {
          @con.strip!
          current_row << (@con.empty? ? nil : @con)
          @con = nil
        }

        action no_content {
          current_row << nil
        }

        include table_common "table_common.rl";
      }%%

      def initialize(listener)
        @listener = listener
        %% write data;
      end

      def scan(data)
        @rows = []
        data = data.unpack("U*") if data.is_a?(String)
        eof = data.size
    
        %% write init;
        %% write exec;
      
        @listener.table(@rows)
      end
    end
  end
end
