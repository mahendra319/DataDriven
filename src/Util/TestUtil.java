package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {
	
	static XSSFWorkbook book;
	static XSSFSheet sheet;
	static XSSFRow row;
	public static String TestData_Path = "K:\\JAVA_WORKSPACE\\DataDriven\\src\\testData\\TestData_2.xlsx";
	
	public static Object[][] getTestData(String sheetName) throws EncryptedDocumentException, IOException
	{
		File fData = new File(TestData_Path);
		
		FileInputStream fInputStream = new FileInputStream(fData); //Create object of FileInputStream to read Excel file
		
		XSSFWorkbook workbook = new XSSFWorkbook(fInputStream); //Load workbook
		
		XSSFSheet sheet = workbook.getSheetAt(0); //Load sheet in which data stored
		
		 // Why Object array instead of String array or Integer Array?- Object array can hold any type of data type.
		Object [][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i<sheet.getLastRowNum(); i++)
		{
		   for (int j=0; j<sheet.getRow(0).getLastCellNum(); j++)
			{
				
				data [i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	

}
