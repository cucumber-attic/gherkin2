module Gherkin
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
        @con += data[p].chr
      }

      action store_content {
        @con.strip!
        current_row << (@con.empty? ? nil : @con)
        @con = nil
      }

      action no_content {
        current_row << nil
      }

      EOL = '\r'? '\n';
      BAR = '|';
      cell_content = [^|\r\n];

      end_of_row = (any* %/store_row) :>> EOL; # match any* if there are no EOLs

      table_row = (
        start: (
          space* BAR ->start_new_cell
        ),

        start_new_cell: (
          BAR @no_content ->start_new_cell |
          cell_content @accumulate_content ->until_cell_end |
          end_of_row @store_row ->final
        ),

        until_cell_end: (
          BAR @store_content ->start_new_cell |
          cell_content @accumulate_content ->until_cell_end |
          end_of_row @store_row ->final
        )    
      ) >initialize;

      table = table_row+;
      main := table;
    }%%

    def initialize
      %% write data;
    end

    def scan(data, listener)
      @rows = []
      data = data.unpack("c*") if data.is_a?(String)
      eof = data.size
      %% write init;
      %% write exec;
      listener.table(@rows)
    end
  end
end
