package learningselenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import learningselenium.AbstractComponents.CommonSteps;


public class ShippingPage extends CommonSteps {

	 WebDriver driver;

	public ShippingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement countryTextField;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeorderButton;

	By results= By.cssSelector(".ta-results");
	
	public void enterDetails(String country) {
		
		driver.manage().window().fullscreen();
		countryTextField.sendKeys(country);
		waitForElement(results);
		selectCountry.click();
	}
	
	public ConfirmationPage placeOrder() {
		
		placeorderButton.click();
		return new ConfirmationPage(driver);
		


	}

	
	

}
