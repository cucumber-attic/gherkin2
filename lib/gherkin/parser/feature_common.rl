%%{
  machine feature_common;

  FEATURE = 'Feature:' >start_keyword %end_keyword;
  BACKGROUND = 'Background:' >start_keyword %end_keyword;
  SCENARIO = 'Scenario:' >start_keyword %end_keyword;
  STEP = ('Given ' | 'When ' | 'And ' | 'Then ' | 'But ') >start_keyword %end_keyword;
 
  EOL = ('\r'? '\n') @inc_line_number;
  BAR = '|' >start_keyword %end_keyword;

  Comment = space* '#' >begin_content ^EOL+ %store_comment_content %/store_comment_content EOL+;

  Feature_end = EOL+ space* (BACKGROUND | SCENARIO | '@' | '#');
  Scenario_end = EOL+ space* ( SCENARIO | STEP | '@' | '#' );
  Background_end = EOL+ space* ( SCENARIO | STEP | '@' | '#' );

  Feature = space* FEATURE %begin_content %current_line ^Feature_end* %/store_feature_content :>> Feature_end >backup @store_feature_content;
  Background = space* BACKGROUND %begin_content %current_line ^Background_end* %/store_background_content :>> Background_end >backup @store_background_content;
  Scenario = space* SCENARIO %begin_content %current_line ^Scenario_end* %/store_scenario_content :>> Scenario_end >backup @store_scenario_content;
  Step = space* STEP %begin_content %current_line ^EOL+ %store_step_content %/store_step_content EOL+;  

  Tag = ( '@' [^@\r\n\t ]+ >begin_content ) %store_tag_content;
  Tags = space* (Tag @current_line space*)+ EOL+;  

  Table_start = space* '|';
  Table_end = EOL space* ^('|' | space);

  Table = Table_start %begin_content %current_line any+ %/end_table :>> Table_end >backup @end_table;

  #NOTE:  The following state machine will likely ultimately be reduced to simple machines,
  #        But is useful until a full BNF is defined

  feature = (
    start: (
      Comment ->start |
      Tags ->start |
      Feature ->feature_found_looking_for_background 
    ),
    feature_found_looking_for_background: (
      Comment ->feature_found_looking_for_background |
      Tags ->feature_found |
      Background ->scenario_found |
      Scenario ->scenario_found 
    ),
    feature_found: (
      Comment ->feature_found |
      Tags ->feature_found |
      Scenario ->scenario_found 
    ),
    scenario_found: (
      Comment ->scenario_found |
      Tags ->feature_found | 
      Scenario ->scenario_found |
      Step ->step_found
    ),
    step_found: (
      Comment ->step_found |
      Tags ->feature_found |
      Scenario ->scenario_found |
      Step ->step_found |
      Table ->step_multiline_found 
    ),
    step_multiline_found: (
      Comment ->step_found |
      Tags ->feature_found |
      Scenario ->scenario_found |
      Step ->step_found
    )
  );

  main := feature;
}%%
