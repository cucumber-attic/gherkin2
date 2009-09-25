Feature: Gherkin Feature parser/policy
  In order to make it easy to control the Gherkin syntax
  As a Gherkin developer bent on Gherkin world-domination
  I want a feature parser that uses a feature policy to
    makes all the syntax decisions for me

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

        Scenario Outline: Imaginary Beings
          Given I have a <boring being>
          When I transmogrify it
          Then I should have an <exciting being>

          Examples:
          | boring being | exciting being |
          | Sparrow      | Alicanto       |
          | Goldfish     | Baldanders     |
          | Cow          | Hsiao          |
      """
   Then there should be no syntax errors
   
  Scenario: Keyword before feature
    Given an English-language feature parser
    When the following text is parsed:
      """
      Scenario: Bullying my way to the head of the line
        Given I am a big bully of a scenario
        Then I should be caught by the syntax police(y)
      
      Feature: Too timid to stand up for myself
      """
    Then there should be syntax errors on lines 1 through 3

  Scenario: Tag ends a scenario
    Given an English-language feature parser
    When the following text is parsed:
      """
      Feature: test feature
      Scenario: my scenario
        @tag
        Given this is a step
        @oh_hai
        And this is a horrible idea
        Then it shouldn't work
      """
    Then there should be syntax errors on lines 4, 6 and 7

  @pending
  Scenario: Broken tables
    Given an English-language feature parser
    When the following text is parsed:
      """
      Feature: Tables
      Scenario: test
        Given I have an unclosed table:
          | foo | bar |
          | 1   | 2   
      """
    Then there should be a syntax error on line 5

