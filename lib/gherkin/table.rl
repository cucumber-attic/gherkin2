module Gherkin
  class Table
    %%{
      machine table;
      
      action InsertCell {
        current_row << data[p].chr
      }
      
      action StartRow {
        current_row = []
      }
      
      action EndRow {
        @rows << current_row
      }
      
      action cell_char {
        @_cbuf ||= [] 
        @_cbuf << data[p]; 
      }
      
      action term_cell {
        current_row << @_cbuf.pack('c*')
        @_cbuf = []
      }
      
      EOL = '\r'? '\n';
      BAR = '|';
      
      cell = ( alnum @cell_char )+ %term_cell;
      table_row = space* BAR @StartRow (cell BAR)+ %EndRow space* EOL;
      table = table_row+;
      
      main := table;
    }%%

    def initialize
      %% write data;
    end

    def parse(data)
      @rows = current_row = []
      data = data.unpack("c*") if data.is_a?(String)
      %% write init;
      %% write exec;
      @rows
    end
  end
end