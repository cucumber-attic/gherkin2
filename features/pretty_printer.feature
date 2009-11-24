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
      | examples/i18n/ar/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/bg/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/bg/features/consecutive_calculations.feature | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/bg/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/cat/features/suma.feature                    | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/da/features/sammenlaegning.feature           | gherkin.LexingError: Lexing error on line 10 |
      | examples/i18n/de/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/de/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/es/features/adicion.feature                  | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/fr/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/fr/features/addition2.feature                | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/he/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/he/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/hu/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/hu/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/it/features/somma.feature                    | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/ja/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/ja/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/ko/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/ko/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/lt/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/lt/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/lv/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/lv/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/no/features/summering.feature                | gherkin.LexingError: Lexing error on line 10 |
      | examples/i18n/pl/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/pl/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/ru/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/ru/features/consecutive_calculations.feature | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/ru/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/se/features/summering.feature                | gherkin.LexingError: Lexing error on line 10 |
      | examples/i18n/sk/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/sk/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/sr/features/sabiranje.feature                | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/tr/features/bolme.feature                    | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/tr/features/toplama.feature                  | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/uz/features/addition.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/uz/features/consecutive_calculations.feature | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/uz/features/division.feature                 | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/zh-CN/features/addition.feature              | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/zh-TW/features/addition.feature              | gherkin.LexingError: Lexing error on line 2  |
      | examples/i18n/zh-TW/features/division.feature              | gherkin.LexingError: Lexing error on line 2  |
      | spec/cucumber/treetop_parser/fit_scenario.feature          | gherkin.LexingError: Lexing error on line 1  |
      | spec/cucumber/treetop_parser/given_scenario.feature        | gherkin.LexingError: Lexing error on line 1  |
      | spec/cucumber/treetop_parser/multiple_tables.feature       | gherkin.LexingError: Lexing error on line 12 |
      | spec/cucumber/treetop_parser/test_dos.feature              | gherkin.LexingError: Lexing error on line 11 |
