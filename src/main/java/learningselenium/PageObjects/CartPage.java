package learningselenium.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutButton;

	public Boolean findProduct(String productName) {
		
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public ShippingPage checkout() {
		
		checkoutButton.click();
		ShippingPage shippingPage= new ShippingPage(driver);
		return shippingPage;
		
	}
	
	

}
