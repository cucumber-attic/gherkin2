module Gherkin
  class Table
    %%{
      machine table;
      
      action insert_cell {
        current_row << data[p].chr
      }
      
      action start_row {
        current_row = []
      }
      
      action end_row {
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
      table_row = space* BAR @start_row (cell BAR)+ %end_row space* EOL;
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