module Gherkin
  class Table
    %%{
      machine table;
      
      EOL = '\r'? '\n';
      BAR = '|';
      
      cell = alpha;
      table_row = space* BAR (cell BAR)+ space* EOL;
      table = table_row+;
      
      main := table;
    }%%

    def initialize
      %% write data;
    end

    def scan(data, listener)
      data = data.unpack("c*") if data.is_a?(String)
      %% write init;
      %% write exec;
    end
  end
end