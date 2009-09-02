module Gherkin
  class Table
    %%{
      machine table;
            
      action start_row {
        current_row = []
      }
      
      action end_row {
        @rows << current_row
      }
      
      action term_cell {
        current_row << data[@_s..(p-1)].pack('c*')
      }
      
      action start_cell {
        @_s = p
      }
            
      action nil_cell {
        current_row << nil
      }
  
      EOL = '\r'? '\n';
      BAR = space* '|';
      word = alnum+;

      non_empty_cell = space* word >start_cell %term_cell BAR;
      empty_cell = BAR %nil_cell;
      cell = (non_empty_cell | empty_cell);
      
      start_of_row = BAR @start_row;
      end_of_row = space* (zlen %/end_row)? :>> EOL %end_row;

      table_row = start_of_row cell+ :>> end_of_row;
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
