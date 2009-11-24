Feature: Pretty printer
  In order to have pretty gherkin
  I want to verify that all prettified cucumber features parse OK

  Scenario: Parse all the features in Cucumber
    Given I have Cucumber's home dir defined in CUCUMBER_HOME
    When I find all of the .feature files
    And I parse the prettified representation of each of them
    # Of course, we don't really want all those errors, but these are the current ones.
    Then the following files should have errors:
      | Path                                                       | Error                                        |
