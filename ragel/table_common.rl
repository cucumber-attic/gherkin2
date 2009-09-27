%%{      
  machine table_common;

  EOL = '\r'? '\n' @inc_line_number;
  cell_content = ^('|' | EOL);

  # table is one or more rows
  # a row is space* then any number of cells followed by a '| space* EOL'
  # a cell is a '|' followed by anything that is not a '|'
  
  Cell = cell_content+ >begin_content '|' >store_cell_content | '|' >no_content;
  
  table_row = space* '|' >initialize Cell+ space* %/store_row %/store_table space* :>> (EOL+ space*); 
  
  unclosed_table_row = space* '|' >initialize Cell* space* cell_content+ %/bad_table_row %/store_table :>> (EOL+ space*);
  
  Table = (table_row %store_row | unclosed_table_row >set_bad_table_row_line %bad_table_row)+ %store_table;
  
  main := Table;
}%%
