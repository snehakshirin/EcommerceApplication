package learningselenium.Tests;

import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import learningselenium.PageObjects.CartPage;
import learningselenium.PageObjects.ConfirmationPage;
import learningselenium.PageObjects.LoginPage;
import learningselenium.PageObjects.ProductCatalogue;
import learningselenium.PageObjects.ShippingPage;
import learningselenium.TestComponents.CommonTest;
import learningselenium.TestComponents.Retry;


public class ErrorValidationsTest extends CommonTest{

	@Test(groups= {"errorvalidating"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {


		loginPage.loginApplication("nallasneha3@gmail.com", "Just@me12");
		
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrormessage()); 	
		System.out.println("loginerrorvalidation");
	}


	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName="ZARA COAT 3";
		String country="india";

		ProductCatalogue productCatalogue=loginPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCart();
		
		Boolean match=cartPage.findProduct("ZARA COAT 33");
		Assert.assertFalse(match);
		System.out.println("producterrorvalidation");

	}


}
