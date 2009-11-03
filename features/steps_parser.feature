Feature: Gherkin Steps parser
  In order to make it easy to write compound steps
  As a Cucumber developer
  I want a steps parser

  Background:
    Given an English-language steps parser
    
  Scenario: Parsing steps
    Given the following text is parsed:
      """
      Given a one step
      And a two step
      And a one two three step
      """
    Then there should be no syntax errors
    
  Scenario: Parsing a feature with the step parser