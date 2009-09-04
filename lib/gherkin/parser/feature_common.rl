%%{
  machine feature_common;

  FEATURE = 'Feature:';
  SCENARIO = 'Scenario:';
  GIVEN = 'Given' | 'When' | 'And' | 'Then' | 'But';
  STEP = GIVEN;
  AND = 'And';
  THEN = 'Then'; 
 
  EOL = '\r'? '\n';
  text = [A-Za-z_: 0-9?#];  #THIS IS NOT EXHAUSTIVE YET
  Comment = space* '#' %begin_content text+ EOL >store_comment_content;

  Feature = space* FEATURE %begin_content ^EOL+ :>> EOL >store_feature_content;  #SINGLE LINE ONLY
  Scenario = space* SCENARIO %begin_content ^EOL+ :>> EOL >store_scenario_content;  #SINGLE LINE ONLY
  Step = space* STEP %begin_content ^EOL+ :>> EOL >store_step_content;  

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
