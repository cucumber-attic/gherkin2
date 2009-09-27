%%{      
  machine table_common;
  
  # Row = space* '|' >start_row Cell+ space* %/store_row %/store_table space* :>> (EOL+ space*) %store_row; 
    
  # UnclosedRow = space* '|' >start_row Cell* space* CellContent+ %/bad_table_row %/store_table :>> (EOL+ space*);
  
  # | UnclosedRow >set_bad_table_row_line %bad_table_row)+;

  EOL = ('\r'? '\n') @inc_line_number;
  
  Cell = '|' (any - '|')* >begin_content %store_cell_content;
  Row = space* Cell* >start_row '|' :>> (space* EOL+ space*) %store_row;
  Table = Row+ %store_table @!end_table;
  main := Table;
}%%
