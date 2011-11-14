Feature: issue 145

  Scenario: Scenario 1
    Given some condition

  Scenario Outline: Scenario 2
    Given some condition
    When I go to <page>
    Then some assertion

    Examples: 
      | page     |
      | /home    |
      | /profile |
      | /logout  |

  Scenario: Scenario 3
    Given some condition

  Scenario: Scenario 4
    Given some condition

