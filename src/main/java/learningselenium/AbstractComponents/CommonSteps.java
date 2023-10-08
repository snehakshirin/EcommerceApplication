package learningselenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import learningselenium.PageObjects.CartPage;
import learningselenium.PageObjects.OrdersPage;

public class CommonSteps {
	
	WebDriver driver;
	public CommonSteps(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);

	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	public void waitForElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));

	}
	
	public void waitForWebElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));		
		Thread.sleep(1000);

	}
	public CartPage goToCart() {
		cartHeader.click();
		CartPage cartPage= new CartPage(driver);
		return  cartPage;
	}

	public OrdersPage goToOrdersPage() {
		ordersHeader.click();
		OrdersPage ordersPage= new OrdersPage(driver);
		return ordersPage;
		
	}

}
