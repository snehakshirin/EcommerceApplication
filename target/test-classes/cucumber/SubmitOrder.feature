
@SubmitOrder
Feature: submit the order from e-commerce website
  I want to use this template for my feature file

Background: 
Given I landed on E-Commerce page


  @SubmitOrderPositiveScenario
  Scenario Outline: positive test of submitting order
    Given Logged in with <username> and <password>
    Then I add the product <productName> to the cart
    And I verify the product <productName> added to cart
    And I checkout and submit the order
		Then <message> message is displayed on the confiormation page
		Then I quit browser

    Examples: 
      | username  						| password | productName |message|
			|nallasneha83@gmail.com |Just@me12 |ZARA COAT 3  |THANKYOU FOR THE ORDER.|