package learningselenium.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import learningselenium.resources.ExtentReporterNG;

public class Listeners extends CommonTest implements ITestListener {
	
	ExtentReports report=ExtentReporterNG.getReportObject();
	ExtentTest test;
	
	//Thread safe - each test has it's own thread
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();

	@Override 
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		
		//unique thread id is allocated  
		thread.set(test);
		  }

	@Override
		  public void onTestSuccess(ITestResult result) {
			thread.get().log(Status.PASS, "Test Passed");
			}

	@Override
		  public void onTestFailure(ITestResult result) {
		
			thread.get().fail(result.getThrowable());
			String filePath = null;
			try {
				driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			thread.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		  }

	@Override
		  public void onTestSkipped(ITestResult result) {
		    // not implemented
		  }

	@Override
		  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

	@Override
		  public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }

	@Override
		  public void onStart(ITestContext context) {
		    // not implemented
		  }

	@Override
		  public  void onFinish(ITestContext context) {
			report.flush();
			}


}
