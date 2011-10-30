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
    And the following text is parsed:
      """
      # language: no
      # Yet another comment
      Egenskap: Kjappere
      """
    Then the outputted JSON should be:
      """
      [
        {
          "comments": [
            {
              "line": 1,
              "value": "# language: no"
            },
            {
              "line": 2,
              "value": "# Another comment"
            }
          ],
          "description": "",
          "keyword": "Egenskap",
          "line": 3,
          "name": "Kjapp",
          "uri": "test.feature",
          "id": "kjapp"
        },
        {
          "comments": [
            {
              "line": 1,
              "value": "# language: no"
            },
            {
              "line": 2,
              "value": "# Yet another comment"
            }
          ],
          "description": "",
          "keyword": "Egenskap",
          "line": 3,
          "name": "Kjappere",
          "uri": "test.feature",
          "id": "kjappere"
        }
      ]
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
            \"\"\"plaintext
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
      [
        {
          "uri": "test.feature",
          "description": "",
          "elements": [
            {
              "description": "",
              "keyword": "Scenario",
              "line": 4,
              "id": "oh-hai/fujin",
              "name": "Fujin",
              "steps": [
                {
                  "keyword": "Given ",
                  "line": 5,
                  "name": "wind"
                },
                {
                  "keyword": "Then ",
                  "line": 6,
                  "name": "spirit"
                }
              ],
              "type": "scenario"
            },
            {
              "description": "",
              "keyword": "Scenario",
              "line": 9,
              "id": "oh-hai/_why",
              "name": "_why",
              "steps": [
                {
                  "keyword": "Given ",
                  "line": 10,
                  "name": "chunky"
                },
                {
                  "keyword": "Then ",
                  "line": 11,
                  "name": "bacon"
                }
              ],
              "tags": [
                {
                  "line": 8,
                  "name": "@two"
                }
              ],
              "type": "scenario"
            },
            {
              "description": "",
              "examples": [
                {
                  "description": "",
                  "keyword": "Examples",
                  "line": 18,
                  "id": "oh-hai/life/real-life",
                  "name": "Real life",
                  "rows": [
                    {
                      "cells": [
                        "boredom"
                      ],
                      "line": 19
                    },
                    {
                      "cells": [
                        "airport"
                      ],
                      "line": 20
                    },
                    {
                      "cells": [
                        "meeting"
                      ],
                      "line": 21
                    }
                  ],
                  "tags": [
                    {
                      "line": 17,
                      "name": "@five"
                    }
                  ]
                }
              ],
              "keyword": "Scenario Outline",
              "line": 14,
              "id": "oh-hai/life",
              "name": "Life",
              "steps": [
                {
                  "keyword": "Given ",
                  "line": 15,
                  "name": "some <boredom>"
                }
              ],
              "tags": [
                {
                  "line": 13,
                  "name": "@three"
                },
                {
                  "line": 13,
                  "name": "@four"
                }
              ],
              "type": "scenario_outline"
            },
            {
              "description": "",
              "keyword": "Scenario",
              "line": 23,
              "id": "oh-hai/who-stole-my-mojo?",
              "name": "who stole my mojo?",
              "steps": [
                {
                  "keyword": "When ",
                  "line": 24,
                  "name": "I was",
                  "rows": [
                    {
                      "cells": [
                        "asleep"
                      ],
                      "line": 25
                    }
                  ]
                },
                {
                  "doc_string": {
                    "content_type": "plaintext",
                    "line": 27,
                    "value": "innocent"
                  },
                  "keyword": "And ",
                  "line": 26,
                  "name": "so"
                }
              ],
              "type": "scenario"
            },
            {
              "comments": [
                {
                  "line": 31,
                  "value": "# The"
                }
              ],
              "description": "",
              "examples": [
                {
                  "comments": [
                    {
                      "line": 36,
                      "value": "# comments"
                    },
                    {
                      "line": 37,
                      "value": "# everywhere"
                    }
                  ],
                  "description": "",
                  "keyword": "Examples",
                  "line": 38,
                  "id": "oh-hai/with/an-example",
                  "name": "An example",
                  "rows": [
                    {
                      "cells": [
                        "partout"
                      ],
                      "comments": [
                        {
                          "line": 39,
                          "value": "# I mean"
                        }
                      ],
                      "line": 40
                    }
                  ]
                }
              ],
              "keyword": "Scenario Outline",
              "line": 32,
              "id": "oh-hai/with",
              "name": "with",
              "steps": [
                {
                  "comments": [
                    {
                      "line": 33,
                      "value": "# all"
                    }
                  ],
                  "keyword": "Then ",
                  "line": 34,
                  "name": "nice"
                }
              ],
              "type": "scenario_outline"
            }
          ],
          "keyword": "Feature",
          "line": 2,
          "id": "oh-hai",
          "name": "OH HAI",
          "tags": [
            {
              "line": 1,
              "name": "@one"
            }
          ]
        }
      ]
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
      [
        {
          "uri": "test.feature",
          "keyword": "Feature",
          "name": "Kjapp",
          "id": "kjapp",
          "description": "",
          "line": 1,
          "elements": [
            {
              "type": "background",
              "keyword": "Background",
              "line": 3,
              "name": "No idea what Kjapp means",
              "description": "",
              "steps": [
                {
                  "keyword": "Given ",
                  "line": 4,
                  "name": "I Google it"
                }
              ]
            },
            {
              "type": "scenario",
              "comments": [{"value": "# Writing JSON by hand sucks", "line": 6}],
              "keyword": "Scenario",
              "id": "kjapp/",
              "name": "",
              "description": "",
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
      ]
      """
  
  Scenario: Feature with a description

    We want people to be able to put markdown formatting into their descriptions
    but this means we need to respect whitespace at the start and end of lines
    in the description.
    
    Pay close attention to the whitespace in this example.
    
    Given the following text is parsed:
      """
      Feature: Foo
        one line  
        another line  
        
            some pre-formatted stuff
        
        Background: b name
            test  
            test 
        
        Scenario: s name
            test  
            test 
        
        Scenario Outline: s o name
            test  
            test 
          
          Given <foo> 
            
          Examples: e name
              test  
              test 
            | foo   |
            | table |
      """
    Then the outputted JSON should be:
      """
      [
        {
          "uri": "test.feature",
          "keyword": "Feature",
          "id": "foo",
          "name": "Foo",
          "description": "one line  \nanother line  \n\n    some pre-formatted stuff",
          "line": 1,
          "elements": [
            {
              "description": "  test  \n  test",
              "keyword": "Background",
              "line": 7,
              "name": "b name",
              "type": "background"
            },
            {
              "description": "  test  \n  test",
              "keyword": "Scenario",
              "line": 11,
              "id": "foo/s-name",
              "name": "s name",
              "type": "scenario"
            },
            {
              "description": "  test  \n  test",
              "examples": [
                {
                  "description": "  test  \n  test",
                  "keyword": "Examples",
                  "line": 21,
                  "id": "foo/s-o-name/e-name",
                  "name": "e name",
                  "rows": [
                    {
                      "cells": [
                        "foo"
                      ],
                      "line": 24
                    },
                    {
                      "cells": [
                        "table"
                      ],
                      "line": 25
                    }
                  ]
                }
              ],
              "keyword": "Scenario Outline",
              "line": 15,
              "id": "foo/s-o-name",
              "name": "s o name",
              "steps": [
                {
                  "keyword": "Given ",
                  "line": 19,
                  "name": "<foo>"
                }
              ],
              "type": "scenario_outline"
            }
          ]
        }
      ]
      """


