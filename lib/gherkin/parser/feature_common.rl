%%{
  machine feature_common;

  FEATURE = 'Feature:';
  SCENARIO = 'Scenario:';
  GIVEN = 'Given' | 'When' | 'And' | 'Then' | 'But';
  STEP = GIVEN;
  AND = 'And';
  THEN = 'Then'; 
 
  EOL = '\r'? '\n';
  text = [A-Za-z_: 0-9?];  #THIS IS NOT EXHAUSTIVE YET

  Feature = space* FEATURE %begin_content text+ EOL >store_feature_content;  #SINGLE LINE ONLY
  Scenario = space* SCENARIO %begin_content text+ EOL >store_scenario_content;  #SINGLE LINE ONLY
  Step = space* STEP %begin_content text+ EOL >store_step_content;  

  feature = (
    start: (
      Feature ->start |
      Scenario ->start |
      Step ->start
    )
  );

  main := feature;
}%%
