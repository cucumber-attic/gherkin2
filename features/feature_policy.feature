# And the following tokens should be found:
#   | Token      | Count |
#   | feature    | 1     |
#   | scenario   | 1     |
#   | tags       | 2     |
#   | background | 1     |
#   | steps      | 4     |

Feature: Parsing Gherkin using the Feature policy

  Scenario: Correctly formed feature
    Given an English-language feature parser
    When the following text is parsed:
      """
      # Apologies to Bill Watterson
      @cardboard_box @wip
      Feature: Transmogrification
        As a young boy with a hyperactive imagination
        I want a cardboard box
        In order to transform the ennui of suburban life into something
          befitting my imagination
        
        Background: 
          Given I have a transmogrifier
          
        Scenario: Whoozit to whatzit transmogrification
          Given I have a whoozit
          When I put it in the transmogrifier
          And I press the "transmogrify" button
          Then I should have a whatzit
      """
   Then there should be no errors
   # And 1 Feature token should have been found
   # And 1 Background token should have been found
   # And 1 Scenario token should have been found
   # And 5 Step tokens should have been found
   # And 2 Tag tokens should have been found
   
  Scenario: Keyword before feature
    Given an English-language feature parser
    When the following text is parsed:
      """
      Scenario: Bullying my way to the head of the line
        Given I am a big bully of a scenario
        Then I should be caught by the syntax police(y)
      
      Feature: Too timid to stand up for myself
      """
    Then there should be syntax errors on lines 1, 2 and 3

  Scenario: I18N Feature parsing
    Given a Norwegian-language feature parser
    When the following text is parsed:
      """
      Egenskap: Oh hello
      """
    Then there should be no errors
