package testCases;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import operation.ReadObject;
import operation.UIOperation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excelExportAndFileIO.ExcelUtils;


public class LoginTest 
{
	 private String sTestCaseName;
	 
	 private int iTestCaseRow;
	 public static WebDriver webdriver = null;
	 @BeforeTest
	 
	    public void beforeMethod() throws Exception {
		 
		 System.setProperty("webdriver.gecko.driver","C:\\BrowserDriver\\geckodriver-v0.13.0-win64\\geckodriver.exe");
         webdriver=new FirefoxDriver();

	//	 webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// webdriver.get("http://www.store.demoqa.com");	 

		}
	    
//	 @DataProvider(name="hybridData")
//	 public Object[][] getDataFromDataprovider() throws IOException
//	 {
//		 
//		 Object[][] object = null;
//		 ReadExcelFile file = new ReadExcelFile();
//	    //Read keyword sheet
//	   Sheet guru99Sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestCase.xlsx" , "KeywordFramework");
//	    //Find number of rows in excel file
//	    int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
//	    //object = new Object[rowCount][5];
//	    for (int i = 1; i < rowCount; i++) {
//	        //Loop over all the rows
//	        Row row = guru99Sheet.getRow(i);
//	        //Create a loop to print cell values in a row
//	        if(row.getFirstCellNum()!= 0)
//	        {
//        	
//        	for (int j = 1; j < row.getLastCellNum(); j++) {
//        		object[i][j] = row.getCell(j).toString();
//	            	System.out.println("object["+i+"]["+j+"] : "+object[i][j]);
//	            	
//	           
//	         }
//	        }
//	         else{
//	             //Print the new testcase name when it started
//	                 System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
//                 
//             }
//	    }
//	    System.out.println("");
//	    return (object);
//	 }
	 @DataProvider(name="Home")

	    public Object[][] Authentication() throws Exception{
		 
		// ReadExcelFile file = new ReadExcelFile();
		    //Read keyword sheet
		  // Sheet guru99Sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestCase.xlsx" , "KeywordFramework");
		   
	      ExcelUtils.setExcelFile(System.getProperty("user.dir")+"\\TestCase.xlsx","Home");
	 	  sTestCaseName = this.toString();
	 	  iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,0);
	 	  Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")+"\\TestCase.xlsx","Home");
	        return (testObjArray);

			}
	 
	
     @Test(dataProvider="Home")
    public void Login(String keyword,String objectName,String objectType,String value) throws Exception {
        // TODO Auto-generated method stub
     // System.out.println("Test Case Name : "+testcaseName);
      System.out.println("keyword : "+keyword);
      System.out.println("objectName : "+objectName);
      System.out.println("objectType : "+objectType);
    
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UIOperation operation = new UIOperation(webdriver);
		    //Call perform function to perform operation on UI
		operation.perform(allObjects, keyword, objectName,objectType, value);
	//	ExcelUtils.writeExcel(System.getProperty("user.dir")+"\\TestCase.xlsx","Home",5,2,"PASS");
	//	ExcelUtils.writeExcel(System.getProperty("user.dir")+"\\TestCase.xlsx","Home",5,3,"PASS");
    }
    
     @AfterTest
     
     public void afterMethod() {
  
    	 webdriver.quit();
  
     	}

}
