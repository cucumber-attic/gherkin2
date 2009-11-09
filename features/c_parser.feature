@c_lexer
Feature: C Lexer

  Background:
    Given a C-language feature lexer

  Scenario: Parsing an empty feature
    Given the following text is parsed:
      """
      Feature: blah
      """
    Then there should be no syntax errors
  
  Scenario: Parsing a comment
    Given the following text is parsed:
      """
      # A comment
      Feature: Hello
      """
    Then there should be no syntax errors
