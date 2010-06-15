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

  Scenario: Feature with scenarios and outlines
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
            "type": "scenario",
            "line": 4,
            "steps": [
              {
                "comments": [],
                "keyword": "Given ",
                "name": "wind",
                "line": 5
              },
              {
                "comments": [],
                "keyword": "Then ",
                "name": "spirit",
                "line": 6
              }
            ]
          },
          {
            "comments": [],
            "tags": ["@two"],
            "keyword": "Scenario",
            "name": "_why",
            "description": "",
            "type": "scenario",
            "line": 9,
            "steps": [
              {
                "comments": [],
                "keyword": "Given ",
                "name": "chunky",
                "line": 10
              },
              {
                "comments": [],
                "keyword": "Then ",
                "name": "bacon",
                "line": 11
              }
            ]
          },
          {
            "comments": [],
            "tags": ["@three", "@four"],
            "keyword": "Scenario Outline",
            "name": "Life",
            "description": "",
            "type": "scenario_outline",
            "line": 14,
            "steps": [
              {
                "comments": [],
                "keyword": "Given ",
                "name": "some <boredom>",
                "line": 15
              }
            ],
            "examples": [
              {
                "comments": [],
                "tags": ["@five"],
                "keyword": "Examples",
                "name": "Real life",
                "description": "",
                "line": 18,
                "table": [
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
              }
            ]
          },
          {
            "comments": [],
            "tags": [],
            "keyword": "Scenario",
            "name": "who stole my mojo?",
            "description": "",
            "type": "scenario",
            "line": 23,
            "steps": [
              {
                "comments": [],
                "keyword": "When ",
                "name": "I was",
                "line": 24,
                "table": [
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
                "py_string": "innocent"
              }
            ]
          },
          {
            "comments": ["# The"],
            "tags": [],
            "keyword": "Scenario Outline",
            "description": "",
            "type": "scenario_outline",
            "line": 32,
            "name": "with",
            "steps": [
              {
                "comments": ["# all"],
                "keyword": "Then ",
                "line": 34,
                "name": "nice"
              }
            ],
            "examples": [
              {
                "comments": ["# comments", "# everywhere"],
                "tags": [],
                "keyword": "Examples",
                "name": "An example",
                "description": "",
                "line": 38,
                "table": [
                  {
                    "comments": ["# I mean"],
                    "line": 40,
                    "cells": ["partout"]
                  }
                ]
              }
            ]
          }
        ]
      }
      """

  Scenario:  Feature with Background
    Given the following text is parsed:
      """
      Feature: Kjapp

        Background: No idea what Kjapp means
          Given I Google it

        # Writing JSON by hand sucks
        Scenario: 
          Then I think it means "fast"
      """
    Then the outputted JSON should be:
      """
      {
        "comments": [],
        "description": "",
        "keyword": "Feature",
        "name": "Kjapp",
        "tags": [],
        "uri": "test.feature",
        "background": {
          "comments": [],
          "description": "",
          "keyword": "Background",
          "line": 3,
          "name": "No idea what Kjapp means",
          "steps": [
            {
              "comments": [],
              "keyword": "Given ",
              "line": 4,
              "name": "I Google it"
            }
          ]
        },
        "elements": [
          {
            "comments": ["# Writing JSON by hand sucks"],
            "tags": [],
            "keyword": "Scenario",
            "name": "",
            "description": "",
            "type": "scenario",
            "line": 7,
            "steps": [
              {
                "comments": [],
                "keyword": "Then ",
                "name": "I think it means \"fast\"",
                "line": 8
              }
            ]
          }
        ]
      }
      """
    
