package learningselenium.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningselenium.AbstractComponents.CommonSteps;

public class LoginPage extends CommonSteps{

WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
		
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("http://rahulshettyacademy.com/client/");

	}
	public ProductCatalogue loginApplication(String emailId, String pwd) {
		email.sendKeys(emailId);
		password.sendKeys(pwd);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	
	public String getErrormessage() {
		waitForWebElement(errorMessage);
		String errorMsg=errorMessage.getText();
		return errorMsg;
	}

}
