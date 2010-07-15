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
        "type": "feature", 
        "comments": ["# language: no", "# Another comment"],
        "keyword": "Egenskap",
        "name": "Kjapp",
        "line": 3,
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
        "type": "feature", 
        "keyword": "Feature",
        "name": "OH HAI",
        "tags": ["@one"],
        "line": 2,
        "uri": "test.feature",
        "elements":[
          {
            "keyword": "Scenario",
            "name": "Fujin",
            "type": "scenario",
            "line": 4,
            "steps": [
              {
                "keyword": "Given ",
                "name": "wind",
                "line": 5
              },
              {
                "keyword": "Then ",
                "name": "spirit",
                "line": 6
              }
            ]
          },
          {
            "tags": ["@two"],
            "keyword": "Scenario",
            "name": "_why",
            "type": "scenario",
            "line": 9,
            "steps": [
              {
                "keyword": "Given ",
                "name": "chunky",
                "line": 10
              },
              {
                "keyword": "Then ",
                "name": "bacon",
                "line": 11
              }
            ]
          },
          {
            "tags": ["@three", "@four"],
            "keyword": "Scenario Outline",
            "name": "Life",
            "type": "scenario_outline",
            "line": 14,
            "steps": [
              {
                "keyword": "Given ",
                "name": "some <boredom>",
                "line": 15
              }
            ],
            "examples": [
              {
                "type": "examples", 
                "tags": ["@five"],
                "keyword": "Examples",
                "name": "Real life",
                "line": 18,
                "table": [
                  {
                    "cells": ["boredom"],
                    "line": 19
                  },
                  {
                    "cells": ["airport"],
                    "line": 20
                  },
                  {
                    "cells": ["meeting"],
                    "line": 21
                  }
                ]
              }
            ]
          },
          {
            "keyword": "Scenario",
            "name": "who stole my mojo?",
            "type": "scenario",
            "line": 23,
            "steps": [
              {
                "keyword": "When ",
                "name": "I was",
                "line": 24,
                "table": [
                  {
                    "line": 25,
                    "cells": ["asleep"]
                  }
                ]
              },
              {
                "keyword": "And ",
                "name": "so",
                "line": 26,
                "py_string": "innocent"
              }
            ]
          },
          {
            "comments": ["# The"],
            "keyword": "Scenario Outline",
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
                "type": "examples", 
                "comments": ["# comments", "# everywhere"],
                "keyword": "Examples",
                "name": "An example",
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
        "type": "feature", 
        "keyword": "Feature",
        "name": "Kjapp",
        "line": 1,
        "uri": "test.feature",
        "elements": [
          {
            "type": "background",
            "keyword": "Background",
            "line": 3,
            "name": "No idea what Kjapp means",
            "steps": [
              {
                "keyword": "Given ",
                "line": 4,
                "name": "I Google it"
              }
            ]
          },
          {
            "comments": ["# Writing JSON by hand sucks"],
            "keyword": "Scenario",
            "type": "scenario",
            "line": 7,
            "steps": [
              {
                "keyword": "Then ",
                "name": "I think it means \"fast\"",
                "line": 8
              }
            ]
          }
        ]
      }
      """
    
