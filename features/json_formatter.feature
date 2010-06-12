Feature: JSON formatter
  In order to support greater access to features
  we want JSON

  Background:
    Given a JSON formatter
    And a "ruby" "root" parser

  Scenario: Only a Feature
    Given the following text is parsed:
      """
      # language: no
      Egenskap: Kjapp
      """
    Then the outputted JSON should be:
      """
      {
        "comments": ["# language: no"],
        "tags": [],
        "keyword": "Egenskap",
        "name": "Kjapp"
      }
      """

  Scenario: Feature with two scenarios
    Given the following text is parsed:
      """
      @one
      Feature: OH HAI

        Scenario: Fujin
          Given wind
          Then spirit

        @two
        Scenario: _why
          Given chunky
          Then bacon

        @three @four
        Scenario Outline: Life
          Given some <boredom>

        @five
        Examples: Real life
          |boredom|
          |airport|
          |meeting|

        Scenario: who stole my mojo?
          When I was
            |asleep|
          And so
            \"\"\"
            innocent
            \"\"\"

        # The
        Scenario Outline: with
          # all
          Then nice
          
        # comments
        # everywhere
        Examples:
          # I mean
          | partout |
      """
    Then the outputted JSON should be:
      """
      {
        "comments": [],
        "tags": ["@one"],
        "keyword": "Feature",
        "name": "OH HAI",
        "elements":[
          {
            "comments": [],
            "tags": [],
            "keyword": "Scenario",
            "name": "Fujin",
            "line": 4,
            "steps": [
              {
                "comments": [],
                "keyword": "Given ",
                "name": "wind",
                "line": 5,
                "multiline_arg": null
              },
              {
                "comments": [],
                "keyword": "Then ",
                "name": "spirit",
                "line": 6,
                "multiline_arg": null
              }
            ]
          },
          {
            "comments": [],
            "tags": ["@two"],
            "keyword": "Scenario",
            "name": "_why",
            "line": 9,
            "steps": [
              {
                "comments": [],
                "keyword": "Given ",
                "name": "chunky",
                "line": 10,
                "multiline_arg": null
              },
              {
                "comments": [],
                "keyword": "Then ",
                "name": "bacon",
                "line": 11,
                "multiline_arg": null
              }
            ]
          },
          {
            "comments": [],
            "tags": ["@three", "@four"],
            "keyword": "Scenario Outline",
            "name": "Life",
            "line": 14,
            "steps": [
              {
                "comments": [],
                "keyword": "Given ",
                "name": "some <boredom>",
                "line": 15,
                "multiline_arg": null
              }
            ]
          },
          {
            "comments": [],
            "tags": ["@five"],
            "keyword": "Examples",
            "name": "Real life",
            "line": 18,
            "table": [
              ["boredom"], 
              ["airport"], 
              ["meeting"]
            ]
          },
          {
            "comments": [],
            "tags": [],
            "keyword": "Scenario",
            "name": "who stole my mojo?",
            "line": 23,
            "steps": [
              {
                "comments": [],
                "keyword": "When ",
                "name": "I was",
                "line": 24,
                "multiline_arg": [
                  [
                    "asleep"
                  ]
                ]
              },
              {
                "comments": [],
                "keyword": "And ",
                "name": "so",
                "line": 26,
                "multiline_arg": "innocent"
              }
            ]
          },
          {
            "comments": ["# The"],
            "tags": [],
            "keyword": "Scenario Outline",
            "line": 32,
            "name": "with",
            "steps": [
              {
                "comments": ["# all"],
                "keyword": "Then ",
                "line": 34,
                "name": "nice",
                "multiline_arg": null
              }
            ]
          },
          {
            "comments": ["# comments", "# everywhere"],
            "tags": [],
            "keyword": "Examples",
            "line": 38,
            "name": "# I mean",
            "table": [
              [
                "partout"
              ]
            ]
          }
        ]
      }
      """
