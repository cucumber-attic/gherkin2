Feature: Parsing Gherkin using the Feature policy

  Scenario: Correctly formed feature
    Given an 'en' feature parser
    When the following text is parsed:
      """
      Feature: Transmogrification
        Scenario: Whoozit to whatzit transfer
          Given I have a whoozit
          When I transmogrify it into a whatzit
          Then I should be ecstatic
      """
   Then there should be no errors

  Scenario: Incorrectly formed feature
    Given an 'en' feature parser
    When the following text is parsed:
      """
      Scenario: Bullying my way to the head of the line
        Given I am a big bully of a scenario
        Then I should be caught by the syntax police(y)
      
      Feature: Too timid to stand up for myself
      """
    Then there should be a syntax error on line 1
