%%{      
  machine table_common;

  EOL = '\r'? '\n' @inc_line_number;
  CellContent = ^('|' | EOL);

  # table is one or more rows
  # a row is space* then any number of cells followed by a '| space* EOL'
  # a cell is a '|' followed by anything that is not a '|'
  
  Cell = CellContent+ >begin_content '|' >store_cell_content | '|' >no_content;
  
  Row = space* '|' >start_row Cell+ space* %/store_row %/store_table space* :>> (EOL+ space*) %store_row; 
  
  # UnclosedRow = space* '|' >start_row Cell* space* CellContent+ %/bad_table_row %/store_table :>> (EOL+ space*);
  
  Table = Row+; # | UnclosedRow >set_bad_table_row_line %bad_table_row)+;
  
  main := Table %store_table @!end_table;
}%%
