package learningselenium.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import learningselenium.PageObjects.CartPage;
import learningselenium.PageObjects.ConfirmationPage;
import learningselenium.PageObjects.LoginPage;
import learningselenium.PageObjects.OrdersPage;
import learningselenium.PageObjects.ProductCatalogue;
import learningselenium.PageObjects.ShippingPage;
import learningselenium.TestComponents.CommonTest;


public class SubmitOrderTest extends CommonTest{
	
	String productName="ZARA COAT 3";
	String country="india";

	
	@Test(dataProvider="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input)  throws IOException, InterruptedException {

		ProductCatalogue productCatalogue=loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage cartPage=productCatalogue.goToCart();
		Boolean match=cartPage.findProduct(input.get("productName"));
		Assert.assertTrue(match);
		
		ShippingPage shippingPage=cartPage.checkout();
		shippingPage.enterDetails(country);
		
		ConfirmationPage confirmationPage = shippingPage.placeOrder();
		String message=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.print(input.get("productName")+" Order Placed Successfully");
			
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCatalogue=loginPage.loginApplication("nallasneha83@gmail.com", "Just@me12");
		OrdersPage ordersPage=productCatalogue.goToOrdersPage();
		Boolean match=ordersPage.orderDisplay(productName);
		Assert.assertTrue(match);
		System.out.println("found the order");


	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\learningselenium\\Data\\purchaseorder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	


}
