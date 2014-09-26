#language: en
@very_important @crazy_stuff
Feature: Some terse yet descriptive text of what is desired
 Textual description of the business value of this feature
 Business rules that govern the scope of the feature
 Any additional information that will make the feature easier to understand

 Background: Some prerequisites..
   Given somebody is there
     And they do their thing

 Scenario: Some determinable business situation
   Given some precondition
     And some other precondition
    When some action by the actor
      And some other action
      And yet another action
     Then some testable outcome is achieved
      And something else we can check happens too

 @lame
 Scenario:
   Given some precondition
     And some other precondition
    When some action by the actor
      And some other action
      And yet another action
     Then some testable outcome is achieved
      And something else we can check happens too

 Scenario: scenario with a table!
   Given all these people:
     | name  | email           | phone |
     | Aslak | aslak@email.com | 123   |
     | Matt  | matt@email.com  | 234   |
     | Joe   | joe@email.org   | 456   |
     And some other precondition
    When some action by the actor
      And some other action
      And yet another action
     Then some testable outcome is achieved
      And something else we can check happens too

 Scenario Outline: eating
  Given there are <start> cucumbers
  When I eat <eat> cucumbers
  Then I should have <left> cucumbers

  Examples:
    | start | eat | left |
    |  12   |  5  |  7   |
    |  20   |  5  |  15  |