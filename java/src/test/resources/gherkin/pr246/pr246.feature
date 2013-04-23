Feature: Pull Request 246

  Scenario: Create tax receipt

    Given a user has won a prize and 1000 euros correspond to taxes
    When I request the creation of a tax receipt
    Then the tax receipt is created correctly
