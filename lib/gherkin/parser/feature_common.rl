%%{
  machine feature_common;

  FEATURE = 'Feature:';
  SCENARIO = 'Scenario:';
  GIVEN = 'Given' | 'When';
  AND = 'And';
  THEN = 'Then'; 
 
  EOL = '\r'? '\n';

  KEYWORDS = (FEATURE | SCENARIO | GIVEN);

  feature = (
    start: (
      FEATURE space* ->feature_content
    ),

    feature_content: (
      any+ >accumulate_content ->feature_content |
      EOL+ space* SCENARIO >keyword_found @store_feature_content %clear_content ->scenario_content
    ),

    scenario_content: (
      any+ >accumulate_content ->scenario_content | 
      EOL+ space* GIVEN >keyword_found @store_scenario_content %clear_content ->step_content
    ),
 
    step_content: (
      any+ >accumulate_content ->step_content |
      EOL+ >keyword_found %store_step_content -> final
    )
  );     

  main := feature;
}%%
