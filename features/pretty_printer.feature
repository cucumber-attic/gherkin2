Feature: Pretty printer
  In order to have pretty gherkin
  I want to verify that all prettified cucumber features parse OK

  Scenario: Parse all the features in Cucumber
    Given I have Cucumber's source code next to Gherkin's
    When I find all of the .feature files
    And I parse the prettified representation of each of them
    Then they should all be identical to the pretty output
