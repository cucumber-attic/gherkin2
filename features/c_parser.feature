@c_parser
Feature: C Parser

  Scenario: Loading the C Parser
    Given a C-language feature parser
    When the following text is parsed:
      """
      Feature: blah
      """
    Then I should see a bunch of crap on the screen
