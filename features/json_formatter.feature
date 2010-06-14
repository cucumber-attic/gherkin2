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
      # Another comment
      Egenskap: Kjapp
      """
    Then the outputted JSON should be:
      """
      {
        "comments": ["# language: no", "# Another comment"],
        "description": "",
        "keyword": "Egenskap",
        "name": "Kjapp",
        "tags": [],
        "uri": "test.feature"
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
        Examples: An example
          # I mean
          | partout |
      """
    Then the outputted JSON should be:
      """
      {
        "comments": [],
        "keyword": "Feature",
        "name": "OH HAI",
        "tags": ["@one"],
        "uri": "test.feature",
        "description": "",
        "elements":[
          {
            "comments": [],
            "tags": [],
            "keyword": "Scenario",
            "name": "Fujin",
            "description": "",
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
            "description": "",
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
            "description": "",
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
            "description": "",
            "line": 18,
            "examples_table": [
              {
                "comments": [],
                "cells": ["boredom"],
                "line": 19
              },
              {
                "comments": [],
                "cells": ["airport"],
                "line": 20
              },
              {
                "comments": [],
                "cells": ["meeting"],
                "line": 21
              }
            ]
          },
          {
            "comments": [],
            "tags": [],
            "keyword": "Scenario",
            "name": "who stole my mojo?",
            "description": "",
            "line": 23,
            "steps": [
              {
                "comments": [],
                "keyword": "When ",
                "name": "I was",
                "line": 24,
                "multiline_arg": [
                  {
                    "comments": [],
                    "line": 25,
                    "cells": ["asleep"]
                  }
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
            "description": "",
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
            "name": "An example",
            // TODO - the description should now be the comment
            // It should be on the first row of the examples_table!
            "description": "# I mean",
            "line": 38,
            "examples_table": [
              {
                "comments": [],
                "line": 40,
                "cells": ["partout"]
              }
            ]
          }
        ]
      }
      """
