package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Base {
	public static WebDriver webdriver = null;
	public static ExtentReports report;
	public static ExtentTest logger;
	@BeforeClass
	public void _beforeTest(){
		report = new ExtentReports("D:\\eclips\\workspace\\DemoProjects\\HybridFramework\\Reports\\TestReport.html");
		System.setProperty("webdriver.gecko.driver","C:\\BrowserDriver\\geckodriver-v0.13.0-win64\\geckodriver.exe");
        webdriver=new FirefoxDriver();
       // webdriver.navigate().to("http://stage.internal.sncproperties.com/MonaLisa/");
        webdriver.manage().window().maximize();
		
		
	
	}
	
	@AfterClass
	public void _afterMethod(){
		try{
			webdriver.quit();
			webdriver = null;
			report.endTest(logger);
			report.flush();
		}catch(Exception ignore){
			
		}
	}
	
	public static WebDriver getDriver() {
		return webdriver;
	}
	
	
}
