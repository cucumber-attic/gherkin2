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
      
      cell = alpha @InsertCell;
      table_row = space* BAR @StartRow (cell BAR)+ space* EOL @EndRow;
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