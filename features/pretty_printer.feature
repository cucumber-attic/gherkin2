Feature: Pretty printer
  In order to have pretty gherkin
  I want to verify that all prettified cucumber features parse OK

  Scenario: Parse all the features in Cucumber
    Given I have Cucumber's home dir defined in CUCUMBER_HOME
    When I find all of the .feature files
    And I parse the prettified representation of each of them
    # Of course, we don't really want any errors. The last
    # two files that were not identical are written to p1.feature
    # and p2.feature. Do a diff -u p1.feature p2.feature
    #
    # On Both C and Java we get an extra/missing empty line 
    # in some pystrings.
    #
    # Both need to be fixed so we can have a test suite that
    # verifies consistent parsing and prettifying of lots ot
    # features.
    #
    Then the following files should have errors:
      | Path | Error |
