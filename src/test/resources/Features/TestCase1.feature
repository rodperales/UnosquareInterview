#Test code for Cucumber test
@Feature1
Feature: Title of your feature
  I want to use this template for my feature file

  @Scenario1
  Scenario Outline: Title of your scenario outline
    Given Go to Amazon
    When Landed
    Then Seach for <product>
    And Verify item
    And Select First Result
    When Detail Page Loaded
    Then Compare Price
    And Add to cart
    When Go to Cart
    Then Verify Price on Cart 
    When Proceed to Checkout
    Then Delete Item
    And Close

    Examples:
      |product 	 				|
      |"Samsung Galaxy Note 20"	|