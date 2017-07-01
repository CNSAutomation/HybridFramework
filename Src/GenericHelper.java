package Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class GenericHelper extends Base 
{
	
	public static void takeScreenShot(String filename)
	{
		try{
			File src = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("Screensot\\"+filename+".jpg"));
		
		   }catch(IOException e){
				e.printStackTrace();
    
		   }
	}
	
	public void wiatForSometime(int x)
	{
		try{
			Thread.sleep(x);
			
		}catch(InterruptedException e){
			
			e.printStackTrace();
		}
		
	}
	public static String currentDateTime() {
	    Calendar currentDate  = Calendar.getInstance();
	    SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy/MMM/dd HH:mm:ss");
        String dateN = formatter.format(currentDate.getTime()).replace("/","_");
        String dateNow = dateN.replace(":","_");
	    return dateNow;
	}
	public static String currentDateYear() {
	    Calendar currentDate  = Calendar.getInstance();
	    SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy/MMM/dd");
        String dateN = formatter.format(currentDate.getTime()).replace("/","_");
        String dateYear = dateN.replace(":","_");
	    return dateYear;
	}
	public static void AcceptAlert(WebDriver driver) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			System.out.println("Alert Accepted");
		} catch (Exception e) {
			System.out.println("Sorry Alet not found");
		}
	}
}