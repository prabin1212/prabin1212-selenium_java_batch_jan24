Feature: All test cases for positive & negative scenarios

@testsample1
  Scenario: Validate positive credential
    Given user launch the browser
    When User enters username "Admin"
    And User enters password "admin123"
    Then User should be able to navigate