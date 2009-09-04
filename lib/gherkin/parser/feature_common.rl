%%{
  machine feature_common;

  FEATURE = 'Feature:';
  SCENARIO = 'Scenario:';
  GIVEN = 'Given' | 'When' | 'And' | 'Then' | 'But';
  AND = 'And';
  THEN = 'Then'; 
 
  EOL = '\r'? '\n';

  KEYWORDS = (FEATURE | SCENARIO | GIVEN);

  feature = (
    start: (
      FEATURE space* ->feature_content
    ),

    feature_content: (
      any+ >begin_content ->feature_content |
      EOL+ space* SCENARIO >keyword @store_feature_content %clear_content ->scenario_content
    ),

    scenario_content: (
      any+ >begin_content ->scenario_content | 
      EOL+ space* GIVEN >keyword @store_scenario_content %clear_content ->step_content
    ),
 
    step_content: (
      !EOL+ >begin_content EOL >keyword @store_step_content %clear_content -> step_content
      space* GIVEN ->step_content 
    )
  );     

  main := feature;
}%%
