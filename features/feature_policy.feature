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
