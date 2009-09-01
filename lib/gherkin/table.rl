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
        d = data[@_s..(p-1)].pack('c*')
        current_row << d
      }
      
      action start_cell {
        @_s = p
      }
            
      action nil_cell {
        current_row << nil
      }
  
      EOL = '\r'? '\n';
      BAR = '|';
      
      cell = ( alnum @start_cell ) alnum* %term_cell;
      table_row = space* BAR @start_row (space* cell space* BAR)+ space* (zlen %/end_row)? :>> EOL @end_row;
      table = table_row+;
      
      main := table;
    }%%

    def initialize
      %% write data;
    end

    def parse(data)
      @rows = current_row = []
      data = data.unpack("c*") if data.is_a?(String)
      eof = data.size
      %% write init;
      %% write exec;
      @rows
    end
  end
end
