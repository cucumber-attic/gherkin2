module Gherkin
  module Parser
    class Table
      %%{      
        machine table;

        action start_row {
          current_row = []
          @begin_row = p
        }

        action begin_content {
          @content_start = p
        }

        action store_row {
          @rows << current_row
        }

        action store_cell_content {
          con = data[@content_start...p].pack("c*").strip
          current_row << (con.empty? ? nil : con)
        }

        action no_content {
          current_row << nil
        }
 
        action bad_table_row {
          con = data[@begin_row...p].pack("c*").strip
          @listener.table_error("Unclosed table row", con, @bad_row_line)
          # raise ParsingError.new(@bad_row_line)
        }
    
        action inc_line_number {
          @cur_line += 1
        }
 
        action set_bad_table_row_line {
          @bad_row_line = @cur_line
        }

        action store_table {
          if @rows.size != 0
            @listener.table(@rows, @line)
          end
        }
        
        action end_table {
          if cs < table_first_final
            # raise ParsingError.new("TEMP")
          end
        }

        include table_common "table_common.rl";
      }%%

      def initialize(listener,line)
        @line = line
        @cur_line = line
        @listener = listener
        %% write data;
      end

      def scan(data)
        @rows = []
        data = data.unpack("c*") if data.is_a?(String)
        pe = eof = data.length
    
        %% write init;
        %% write exec;
      end
    end
  end
end
