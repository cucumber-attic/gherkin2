Feature: JSON formatter
  In order to support greater access to features
  we want JSON

  Background:
    Given a JSON formatter
    And a "ruby" "root" parser

  Scenario: Only a Feature
    Given the following text is parsed:
      """
      Feature: OH HAI
      """
    Then the outputted JSON should be:
      """
      {
          "keyword": "Feature",
          "name": "OH HAI"
      }
      """

  Scenario: Feature with two scenarios
    Given the following text is parsed:
      """
      Feature: OH HAI

        Scenario: Fujin
          Given wind
          Then spirit

        Scenario: _why
          Given chunky
          Then bacon
      """
    Then the outputted JSON should be:
      """
      {
        "keyword": "Feature",
        "name": "OH HAI",
        "elements":[
          {
            "keyword": "Scenario",
            "name": "Fujin",
            "line": 3,
            "steps": [
              {
                "keyword": "Given ",
                "name": "wind",
                "line": 4
              },
              {
                "keyword": "Then ",
                "name": "spirit",
                "line": 5
              }
            ]
          },
          {
            "keyword": "Scenario",
            "name": "_why",
            "line": 7,
            "steps": [
              {
                "keyword": "Given ",
                "name": "chunky",
                "line": 8
              },
              {
                "keyword": "Then ",
                "name": "bacon",
                "line": 9
              }
            ]
          }
        ]
      }
      """



