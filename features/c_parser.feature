@c_parser
Feature: C Parser

  Background:
    Given a C-language feature parser

  Scenario: Parsing an empty feature
    Given the following text is parsed:
      """
      Feature: blah
      %_FEATURE_END_%
      """
    Then there should be no syntax errors
  
  @wip
  Scenario: Parsing a comment
    Given the following text is parsed:
      """
      # A comment
      Feature: Hello
      %_FEATURE_END_%
      """
    Then there should be syntax errors on lines 1 and 2
