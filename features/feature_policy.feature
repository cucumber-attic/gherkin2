Feature: Gherkin Feature parser/policy
  In order to make it easy to control the Gherkin syntax
  As a Gherkin developer bent on Gherkin world-domination
  I want a feature parser that uses a feature policy to
    make all the syntax decisions for me

  Background:
    Given an English-language feature parser

  Scenario: Correctly formed feature
    Given the following text is parsed:
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

          # See Cucumber #414. It's nothing personal. :-)
          @borges
          Examples:
          | boring being | exciting being |
          | Sparrow      | Alicanto       |
          | Goldfish     | Baldanders     |
          | Cow          | Hsiao          |
      """
   Then there should be no syntax errors
   
  Scenario: Keyword before feature
    Given the following text is parsed:
      """
      Scenario: Bullying my way to the head of the line
        Given I am a big bully of a scenario
        Then I should be caught by the syntax police(y)
      
      Feature: Too timid to stand up for myself
      """
    Then there should be syntax errors on lines 1 through 3

  Scenario: Tag ends a scenario
    Given the following text is parsed:
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

  Scenario: Pystrings
    Given the following text is parsed:
      """
      Feature: Pystring feature
      Scenario: Long step arguments
        Given the following excerpt:
          \"\"\"
          WOMAN:  Who are the Britons?
          ARTHUR:  Well, we all are. we're all Britons and I am your king.
          WOMAN:  I didn't know we had a king.  I thought we were an autonomous
              collective.
          DENNIS:  You're fooling yourself.  We're living in a dictatorship.
              A self-perpetuating autocracy in which the working classes--
          WOMAN:  Oh there you go, bringing class into it again.
          DENNIS:  That's what it's all about if only people would--
          ARTHUR:  Please, please good people.  I am in haste.  Who lives
              in that castle?  
          \"\"\"
        When I read it
        Then I should be amused
      """
    Then there should be no syntax errors
    
  @wip
  Scenario: Tables
    Given the following text is parsed:
      """
      Feature: Antiques Roadshow
      Scenario Outline: Table
        Given a <foo>
        Then a <bar>        
        
      Examples:
        | foo | bar   |
        | 42  | towel |
        @hello
        | 1   | prime |
      """
    Then there should be a syntax error on line 10
