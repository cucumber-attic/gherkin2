%%{      
  machine table_common;

  EOL = '\r'? '\n';
  BAR = '|';
  cell_content = [^|\r\n];

  end_of_row = (any* %/store_row) :>> EOL; # match any* if there are no EOLs

  table_row = (
    start: (
      space* BAR ->start_new_cell
    ),

    start_new_cell: (
      BAR @no_content ->start_new_cell |
      cell_content @accumulate_content ->until_cell_end |
      end_of_row @store_row ->final
    ),

    until_cell_end: (
      BAR @store_content ->start_new_cell |
      cell_content @accumulate_content ->until_cell_end |
      end_of_row @store_row ->final
    )    
  ) >initialize;

  table = table_row+;
  main := table;
}%%
