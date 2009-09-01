module Gherkin
  class Table
    %%{
      machine table;
      
      EOL = '\r'? '\n';
      BAR = '|';
      
      cell = alpha @ { puts data[p, fpc].pack("c*") };
      table_row = space* BAR (cell BAR)+ space* EOL;
      table = table_row+;
      
      main := table @ { puts "TABLE DONE" } ;
    }%%

    def initialize
      %% write data;
    end

    def parse(data)
      data = data.unpack("c*") if data.is_a?(String)
      %% write init;
      %% write exec;
    end
  end
end