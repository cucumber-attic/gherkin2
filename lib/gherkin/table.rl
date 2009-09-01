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
      
      EOL = '\r'? '\n';
      BAR = '|';
      
      cell = alnum @InsertCell;
      table_row = space* BAR @StartRow (cell BAR)+ %EndRow space* EOL;
      table = table_row+;
      
      main := table;
    }%%

    def initialize
      %% write data;
    end

    def scan(data, listener)
      @rows = current_row = []
      data = data.unpack("c*") if data.is_a?(String)
      %% write init;
      %% write exec;
      listener.table(@rows)
    end
  end
end