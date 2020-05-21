package DataDriventTesting;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Util.TestUtil;

public class ReadDataFromExcel2 {
	
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

@Test (dataProvider = "loginData")
 public void readExcelData(String userName, String password) throws IOException
 {
	driver.findElement(By.id("txtUsername")).sendKeys(userName);
	driver.findElement(By.id("txtPassword")).sendKeys(password);
	driver.findElement(By.id("btnLogin")).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	String currentURL = driver.getCurrentUrl();
	
	if (currentURL.equals("https://opensource-demo.orangehrmlive.com/index.php/dashboard"))
	{
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='welcome']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@href='/index.php/auth/logout']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
 }

@AfterTest
public void closeBrowser()
{
	driver.close();
}

//Called DataProvider method from TestUtil Class
@DataProvider (name = "loginData")
 public Object[][] getloginData() throws EncryptedDocumentException, IOException
 {
	Object data [][] = TestUtil.getTestData("login");
	return data;
 }
	
		
	





}
