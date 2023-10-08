package learningselenium.PageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningselenium.AbstractComponents.CommonSteps;


public class ProductCatalogue extends CommonSteps {
	
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;


	
	By productsBy=By.xpath("//div[@class='card-body']");
	
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	
	By toastMsg=By.cssSelector("#toast-container");
	
	public  List<WebElement> getProductsList() {
		waitForElement(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod=getProductsList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElement(toastMsg);
		waitForElementToDisappear(spinner);
		

	}
	

}
