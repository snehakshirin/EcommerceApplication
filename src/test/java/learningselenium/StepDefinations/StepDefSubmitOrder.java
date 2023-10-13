package learningselenium.StepDefinations;
import learningselenium.PageObjects.CartPage;
import learningselenium.PageObjects.ConfirmationPage;
import learningselenium.PageObjects.LoginPage;
import learningselenium.PageObjects.ProductCatalogue;
import learningselenium.PageObjects.ShippingPage;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learningselenium.TestComponents.CommonTest;

public class StepDefSubmitOrder extends CommonTest{
	
	public LoginPage loginPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on E-Commerce page")
	public void I_landed_on_ecommerge() throws IOException {
		
		loginPage=launchApplication();
	}
	
	@Given("^Logged in with (.*) and (.*)$")
	public void Login(String username, String password) {
		 productCatalogue=loginPage.loginApplication(username, password);
	}
	
	
	@When("^I add the product (.*) to the cart$")
	public void add_product(String productname) throws InterruptedException {
		
		productCatalogue.addProductToCart(productname);
	
	}
	
	@When("^I verify the product (.*) added to cart$")
	public void verify_product(String productname) {
		
		cartPage=productCatalogue.goToCart();
		Boolean match=cartPage.findProduct(productname);
		Assert.assertTrue(match);
	}
	
	@When("I checkout and submit the order")
	public void checkout_submit() {
		
		ShippingPage shippingPage=cartPage.checkout();
		shippingPage.enterDetails("India");
		
		confirmationPage = shippingPage.placeOrder();
	}
	
	@Then("^(.*) message is displayed on the confiormation page$")
	public void message_displayed(String string) {
		
		String message=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		
	}
	
	@Then("I quit browser")
	public void quit_bowser() {
		driver.quit();
	}
	
	
	
	
}


