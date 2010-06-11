Feature: JSON formatter
  In order to support greater access to features
  we want JSON

  Background:
    Given a JSON formatter
    And a "ruby" "root" parser

  Scenario: Only a Feature
    Given the following text is parsed:
      """
      Feature: OH HAI
      """
    Then the outputted JSON should be:
      """
      {
          "keyword": "Feature",
          "name": "OH HAI"
      }
      """



