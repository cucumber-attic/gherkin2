%%{      
  machine table_common;

  EOL = '\r'? '\n' @inc_line_number;
  BAR = '|';
  cell_content = ^('|' | EOL);

  cell = cell_content+ >begin_content BAR >store_cell_content | BAR >no_content;
  table_row = space* BAR >initialize cell+ space* %/store_row %/store_table space* :>> EOL+; 
  unclosed_table_row = space* BAR >initialize cell* space* ^(BAR|EOL)+ %/bad_table_row %/store_table :>> EOL+;
  table = (table_row %store_row | unclosed_table_row >set_bad_table_row_line %bad_table_row)+ %store_table;
  main := table;
}%%
