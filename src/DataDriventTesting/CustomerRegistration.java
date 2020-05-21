package DataDriventTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.junit.Assert;

public class CustomerRegistration {

	WebDriver driver;
	File file;
	XSSFWorkbook wBook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	int i;
@BeforeTest
 public void setUp()
 {
	System.setProperty("webdriver.chrome.driver", "K:\\Selenium-GCReddy\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://newtours.demoaut.com/");
	driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	//driver.findElement(By.linkText("REGISTER")).click();
	
 }
	
@Test (dataProvider = "cRegistration")

 public void CustRegistration (String fName, String lName,String eMail, String add1, 
		 String add2, String city, String state, String pCode, String Country, String uName, String pwd, String confirmPwd, String pNo) throws InterruptedException, FileNotFoundException
 {
	driver.findElement(By.linkText("REGISTER")).click();
	driver.findElement(By.name("firstName")).sendKeys(fName);
	driver.findElement(By.name("lastName")).sendKeys(lName);
	driver.findElement(By.name("phone")).sendKeys(pNo);
	driver.findElement(By.id("userName")).sendKeys(eMail);
	driver.findElement(By.name("address1")).sendKeys(add1);
	driver.findElement(By.name("address2")).sendKeys(add2);
	driver.findElement(By.name("city")).sendKeys(city);
	driver.findElement(By.name("state")).sendKeys(state);
	driver.findElement(By.name("postalCode")).sendKeys(pCode);
	Select item = new Select(driver.findElement(By.name("country")));
	item.selectByVisibleText(Country);
	driver.findElement(By.id("email")).sendKeys(uName);
	driver.findElement(By.name("password")).sendKeys(pwd);
	driver.findElement(By.name("confirmPassword")).sendKeys(confirmPwd);
	Thread.sleep(5000);
	driver.findElement(By.name("register")).click();
	
	Thread.sleep(5000);
	
	/*//writing into file
	if (driver.getCurrentUrl().contains("http://newtours.demoaut.com/create_account_success.php"))
	{
		file = new File("K:\\JAVA_WORKSPACE\\DataDriven\\src\\testData\\RegistrationFlight.xlsx");
		
		FileOutputStream fOutput = new FileOutputStream(file);
		
		for ( i=0; i<sheet.getLastRowNum(); i++)
		{
			sheet.getRow(i+1).createCell(sheet.getRow(i+1).getLastCellNum()+1).setCellValue("Pass");
		}
	}
	else
	{
		sheet.getRow(i+1).createCell(sheet.getRow(i+1).getLastCellNum()+1).setCellValue("Fail");
	}
		
	Thread.sleep(10000);*/
		
 }

@AfterTest
public void closeApp()
{
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.quit();
}


@DataProvider (name = "cRegistration")
 public Object[][] cusReg() throws IOException
 {
	file = new File("K:\\JAVA_WORKSPACE\\DataDriven\\src\\testData\\RegistrationFlight.xlsx");
	
	FileInputStream fInpStr = new FileInputStream(file);
	
	wBook = new XSSFWorkbook(fInpStr);
	
	sheet = wBook.getSheet("RegistrationFlight");
	
	Object [][] data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	
	for (int i=0; i<sheet.getLastRowNum(); i++)
	{
		for (int j=0; j<sheet.getRow(0).getLastCellNum(); j++)
		{
			data [i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
			//System.out.println(data[i][j]);
		}
		//System.out.println("");
	}
	return data;
 }





















}
