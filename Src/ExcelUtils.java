package excelExportAndFileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	 private static FileInputStream fis = null;
	 private static FileOutputStream fos = null;
	 private static XSSFWorkbook workbook = null;
	 private static XSSFSheet sheet = null;
	 private static XSSFRow row = null;
	 private static XSSFCell cell = null;
	 private static String xlFilePath;
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	 
			public static void setExcelFile(String Path,String SheetName) throws Exception {
	 
				   try {
	 
						// Open the Excel file
	 
						FileInputStream ExcelFile = new FileInputStream(Path);
	 
						// Access the required test data sheet
	 
						ExcelWBook = new XSSFWorkbook(ExcelFile);
	 
						ExcelWSheet = ExcelWBook.getSheet(SheetName);
	 
						} catch (Exception e){
	 
							throw (e);
	 
						}
	 
				}

public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   

   String[][] tabArray = null;

   try {

	   FileInputStream ExcelFile = new FileInputStream(FilePath);

	   // Access the required test data sheet

	   ExcelWBook = new XSSFWorkbook(ExcelFile);

	   ExcelWSheet = ExcelWBook.getSheet(SheetName);

	   int startRow = 1;

	   int startCol = 1;

	   int ci,cj;

	   int totalRows = ExcelWSheet.getLastRowNum();

	   // you can write a function as well to get Column count

	   int totalCols = 4;

	   tabArray=new String[totalRows][totalCols];

	   ci=0;

	   for (int i=startRow;i<=totalRows;i++, ci++) {           	   
		  
		  cj=0;

		   for (int j=startCol;j<=totalCols;j++, cj++){

			   tabArray[ci][cj]=getCellData(i,j);
			   if(tabArray[ci][cj] == null)
			   {
				   tabArray[ci][cj] = "NA";
			   }

			   System.out.println(tabArray[ci][cj]);  

				}

			}

		}

	catch (FileNotFoundException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	catch (IOException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	return(tabArray);

	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

	try{

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

	
	}
		catch (Exception e)
		{

		System.out.println(e.getMessage());

		throw (e);

		}

	}
	public static String getTestCaseName(String sTestCase)throws Exception{
		 
		String value = sTestCase;

		try{

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");	

			value = value.substring(posi + 1);

			return value;

				}catch (Exception e){

			throw (e);

					}

		}

	public static int getRowContains(String sTestCaseName, int colNum) throws Exception{

		int i;

		try {

			int rowCount = ExcelUtils.getRowUsed();

			for ( i=0 ; i<rowCount; i++){

				if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){

					break;

				}

			}

			return i;

				}catch (Exception e){

			throw(e);

			}

		}

	public static int getRowUsed() throws Exception {

			try{

				int RowCount = ExcelWSheet.getLastRowNum();

				return RowCount;

			}catch (Exception e){

				System.out.println(e.getMessage());

				throw (e);

			}

		}
	public static void writeExcel(String filePath,String sheetName, int colNumber, int rowNum, String value) throws IOException{
		
	
		fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        try
        {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            if(row==null)
                row = sheet.createRow(rowNum);
 
            cell = row.getCell(colNumber);
            if(cell == null)
                cell = row.createCell(colNumber);
 
            cell.setCellValue(value);
 
            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
          
        }
    
     }
//	public boolean setCellData(String sheetName, int colNumber, int rowNum, String value)
//    {
//        try
//        {
//            sheet = workbook.getSheet(sheetName);
//            row = sheet.getRow(rowNum);
//            if(row==null)
//                row = sheet.createRow(rowNum);
// 
//            cell = row.getCell(colNumber);
//            if(cell == null)
//                cell = row.createCell(colNumber);
// 
//            cell.setCellValue(value);
// 
//            fos = new FileOutputStream(xlFilePath);
//            workbook.write(fos);
//            fos.close();
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//            return  false;
//        }
//        return true;
//    }

 }
