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
      BAR = '|';
     
      non_empty_cell = ( alnum @start_cell ) alnum* %term_cell;
      empty_cell = space* %nil_cell;
      cell_contents = (space* non_empty_cell space* BAR)? (space* empty_cell? BAR)?;
      table_row = space* BAR @start_row (cell_contents)+ space* (zlen %/end_row)? :>> EOL @end_row;
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
