Feature: Parsing Gherkin using the Feature policy

  Scenario: Correctly formed feature
    Given an English-language feature parser
    When the following text is parsed:
      """
      # Shout out to Calvin and Hobbes
      @cardboard_box
      Feature: Transmogrification
        Scenario: Whoozit to whatzit transfer
          Given I have a whoozit
          When I transmogrify it into a whatzit
          Then I should be ecstatic
      """
   Then there should be no errors

  Scenario: Keyword before feature
    Given an English-language feature parser
    When the following text is parsed:
      """
      Scenario: Bullying my way to the head of the line
        Given I am a big bully of a scenario
        Then I should be caught by the syntax police(y)
      
      Feature: Too timid to stand up for myself
      """
    Then there should be a syntax error on line 1

  Scenario: I18N Feature parsing
    Given a Norwegian-language feature parser
    When the following text is parsed:
      """
      Egenskap: Oh hello
      """
    Then there should be no errors
