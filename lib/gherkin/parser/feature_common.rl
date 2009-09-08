%%{
  machine feature_common;

  FEATURE = 'Feature:' >start_keyword %end_keyword;
  SCENARIO = 'Scenario:' >start_keyword %end_keyword;
  STEP = ('Given' | 'When' | 'And' | 'Then' | 'But') >start_keyword %end_keyword;
 
  EOL = ('\r'? '\n') @inc_line_number;
  Comment = space* '#' %begin_content ^EOL+ %store_comment_content EOL+;

  Feature = space* FEATURE %begin_content ^EOL+ %store_feature_content EOL+;  #SINGLE LINE ONLY
  Scenario = space* SCENARIO %begin_content ^EOL+ %store_scenario_content EOL+;  #SINGLE LINE ONLY
  Step = space* STEP %begin_content ^EOL+ %store_step_content EOL+;  

  Tag = ( '@' [^@\r\n\t ]+ ) >begin_content %store_tag_content;
  Tags = space* (Tag space*)+ EOL;  

  feature = (
    start: (
      Comment ->start |
      Tags ->start |
      Feature ->feature_found 
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
      Step ->scenario_found
    )
  );

  main := feature;
}%%
