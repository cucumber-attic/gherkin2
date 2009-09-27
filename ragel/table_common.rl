%%{      
  machine table_common;
  
  # Row = space* '|' >start_row Cell+ space* %/store_row %/store_table space* :>> (EOL+ space*) %store_row; 
    
  # UnclosedRow = space* '|' >start_row Cell* space* CellContent+ %/bad_table_row %/store_table :>> (EOL+ space*);
  
  # | UnclosedRow >set_bad_table_row_line %bad_table_row)+;

  # table is one or more rows
  # a row is space* then any number of cells followed by a '| space* EOL'
  # a cell is a '|' followed by anything that is not a '|'

  EOL = '\r'? '\n' @inc_line_number;
  
  CellContent = ^('|' EOL);
  Cell = '|' CellContent+ >begin_content %store_cell_content;
  RowEnd = ('|' EOL);
  Row = space* Cell+ >start_row RowEnd %store_row;
  Table = Row+; 
  
  main := Table %store_table @!end_table;
}%%
