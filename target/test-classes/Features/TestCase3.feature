#Test code for Cucumber test
@Feature3
Feature: Title of your feature
  I want to use this template for my feature file

  @Scenario3
  Scenario Outline: Title of your scenario outline
    Given Go to Amazon
    When Landed
    Then Sign In
    When Landed on Sign In
    Then Fill new user info
    And Select confitions of use
    When Landed conditions of use
    Then Seach for <search> on help page
    And  Select <supportToLookFor> on help page
    And Display sub menus
    And Close
    
    
    Examples:
      |search| supportToLookFor|
      |"Echo"| "Echo Support"  |