package learningselenium.TestComponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import learningselenium.PageObjects.LoginPage;


public abstract class CommonTest {
	
	public WebDriver driver;
	public LoginPage loginPage;
	public WebDriver initializedDriver() throws IOException {
		
		//properties class
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\learningselenium\\resources\\GlobalData.properties");
		prop.load(fis);
		
//		String browser=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		String browser=prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			 driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			//firefox
//			 driver= new FirefoxDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		String jsonContent=FileUtils.readFileToString(new File(filePath));
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}

	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		
		driver=initializedDriver();
		 loginPage=new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication() {
		driver.quit();

	}
	 
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver; 
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source,destination);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	

}
