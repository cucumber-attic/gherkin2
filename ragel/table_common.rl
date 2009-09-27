%%{      
  machine table_common;
  
  EOL = ('\r'? '\n') @inc_line_number;
  Cell = '|' (any - '|')* >begin_content %store_cell_content;
  Row = space* Cell* >start_row '|' :>> (space* EOL+ space*) %store_row;
  Table = Row+ %store_table @!end_table;
  main := Table;
}%%
