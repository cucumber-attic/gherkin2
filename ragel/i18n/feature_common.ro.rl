%%{
  machine feature_common;

  # Language specific
  # FEATURE = 'Feature:' >start_keyword %end_keyword;
  FEATURE = 'Functionalitate:' >start_keyword %end_keyword; 

  # BACKGROUND = 'Background:' >start_keyword %end_keyword;
  BACKGROUND = 'Background:' >start_keyword %end_keyword;

  # SCENARIO = 'Scenario:' >start_keyword %end_keyword;
  SCENARIO = 'Scenariu:' >start_keyword %end_keyword;

  # STEP = ('Given ' | 'When ' | 'And ' | 'Then ' | 'But ') >start_keyword %end_keyword;
  STEP = ('Daca ' | 'Cand ' | 'Si ' | 'Atunci ' | 'Dar ') >start_keyword %end_keyword;
 
  EOL = ('\r'? '\n') @inc_line_number;
  BAR = '|' >start_keyword %end_keyword;

  # Terminators
  EndFeatureHeading = EOL+ space* (BACKGROUND | SCENARIO | '@' | '#');
  EndScenarioHeading = EOL+ space* ( SCENARIO | STEP | '@' | '#' );
  EndBackgroundHeading = EOL+ space* ( SCENARIO | STEP | '@' | '#' );
  StartTable = space* '|';
  EndTable = EOL space* ^('|' | space);

  FeatureHeading = space* FEATURE %begin_content %current_line ^EndFeatureHeading* %/store_feature_content :>> EndFeatureHeading >backup @store_feature_content;
  BackgroundHeading = space* BACKGROUND %begin_content %current_line ^EndBackgroundHeading* %/store_background_content :>> EndBackgroundHeading >backup @store_background_content;
  ScenarioHeading = space* SCENARIO %begin_content %current_line ^EndScenarioHeading* %/store_scenario_content :>> EndScenarioHeading >backup @store_scenario_content;

  Step = space* STEP %begin_content %current_line ^EOL+ %store_step_content %/store_step_content EOL+;  
  Comment = space* '#' >begin_content ^EOL+ %store_comment_content %/store_comment_content EOL+;
  Tag = ( '@' [^@\r\n\t ]+ >begin_content ) %store_tag_content;
  Tags = space* (Tag @current_line space*)+ EOL+;  
  Table = StartTable %begin_content %current_line any+ %/end_table :>> EndTable >backup @end_table;
  
  MultilineStep = Step Table?;
  Scenario = ScenarioHeading (Comment | MultilineStep)*;
  Background = BackgroundHeading (Comment | MultilineStep)*;

  Feature = (Tags | Comment)* FeatureHeading (Tags | Comment)* Background? ((Tags | Comment)* Scenario)*;

  main := Feature;
}%%
