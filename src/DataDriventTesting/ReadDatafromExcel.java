package DataDriventTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadDatafromExcel {
	
	WebDriver driver;
	
@BeforeTest
 public void testSetup()
 {
	System.setProperty("webdriver.chrome.driver", "K:\\Selenium-GCReddy\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://opensource-demo.orangehrmlive.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 }

@Test (dataProvider = "login")
 public void readExcelData(String userName, String password) throws IOException
 {
	driver.findElement(By.id("txtUsername")).sendKeys(userName);
	driver.findElement(By.id("txtPassword")).sendKeys(password);
	driver.findElement(By.id("btnLogin")).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	System.out.println(driver.getCurrentUrl());
 }
	
@AfterTest
public void closeBrowser()
{
	driver.close();
}
	
@DataProvider (name = "login")

 public Object [][] getTestData() throws IOException
 {
	//Import Excel File
	File fData = new File("K:\\Selenium-GCReddy\\TestData.xlsx");
	FileInputStream fInputStream = new FileInputStream(fData); //Create object of FileInputStream to read Excel file
	XSSFWorkbook wBook = new XSSFWorkbook(fInputStream); //Load workbook
	//XSSFSheet sheet = workbook.getSheetAt(0); //Load sheet in which data stored
	XSSFSheet sheet = wBook.getSheet("login");
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
