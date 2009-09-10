%%{      
  machine table_common;

  EOL = '\r'? '\n';
  BAR = '|';
  cell_content = ^('|' | EOL);

  cell = cell_content+ >begin_content BAR >store_cell_content | BAR >no_content;
  table_row = space* BAR >initialize cell+ space* %/store_row space* :>> EOL; 
  table = table_row+ @store_row;
  main := table;
}%%
