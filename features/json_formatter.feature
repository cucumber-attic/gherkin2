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
          "name": "OH HAI",
          "elements": []
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

        Scenario Outline: Life
          Given some <boredom>
          
          Examples: Real life
            |boredom|
            |airport|
            |meeting|

        Scenario: who stole my mojo?
          When I was
            |asleep|
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
          },
          {
            "keyword": "Scenario Outline",
            "name": "Life",
            "line": 11,
            "steps": [
              {
                "keyword": "Given ",
                "name": "some <boredom>",
                "line": 12
              }
            ]
          },
          {
            "keyword": "Examples",
            "name": "Real life",
            "line": 14,
            "table": [
              ["boredom"], 
              ["airport"], 
              ["meeting"]
            ]
          },
          {
            "name": "who stole my mojo?",
            "steps": [
              {
                "name": "I was",
                "line": 20,
                "table": [
                  [
                    "asleep"
                  ]
                ],
                "keyword": "When "
              }
            ],
            "line": 19,
            "keyword": "Scenario"
          }
        ]
      }
      """
