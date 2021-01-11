package com.ibm.mobileassignment;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AdvancedAssignment_Test {
	AndroidDriver driver;

	@BeforeMethod
	public void invokingDevice() throws MalformedURLException {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Vimala");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capability);
		System.out.println(driver + "Driver");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void function() throws IOException, InterruptedException {
		File file = new File("C:\\Users\\VimalaSubbaraj\\Desktop\\Selenium_udemy\\SS\\TestDataSheet.xlsx");
		System.out.println(file + "============");
		FileInputStream fis = new FileInputStream(file);
		System.out.println(fis + "============");

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("TestSheet");
		int rc = sheet.getLastRowNum();
		for (int i = 1; i < rc; i++) {

			String FirstName = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println("Firstname=" + FirstName);
			String LastName = sheet.getRow(i).getCell(2).getStringCellValue();
			System.out.println("LastName=" + LastName);
			String Email = sheet.getRow(i).getCell(3).getStringCellValue();
			System.out.println("Email=" + Email);
			String Phone = sheet.getRow(i).getCell(4).getStringCellValue();
			System.out.println("Phone=" + Phone);
			String Country = sheet.getRow(i).getCell(5).getStringCellValue();
			System.out.println("Country=" + Country);
			String JobRole = sheet.getRow(i).getCell(6).getStringCellValue();
			System.out.println("JobRole=" + JobRole);
			String Company = sheet.getRow(i).getCell(7).getStringCellValue();
			System.out.println("Company=" + Company);
			driver.get("https://magento.com/");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[@class='nav-menu-button js-menu-button visible-xs visible-sm']"))
					.click();
			driver.findElement(By.xpath("//*[@id='block-header']/ul/li[8]/a")).click();
			driver.findElement(By.xpath("//ul[@class='menu menu-level-1']/li/div/div[2]/p[2]/a")).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,2500)");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='FirstName']")).sendKeys(FirstName);
			driver.findElement(By.xpath("//*[@id='LastName']")).sendKeys(LastName);
			driver.findElement(By.xpath("//*[@id='Email']")).sendKeys(Email);
			driver.findElement(By.xpath("//*[@id='Phone']")).sendKeys(Phone);
			WebElement element = driver.findElement(By.xpath("//*[@id='Country']"));
			Select select = new Select(element);
			select.selectByVisibleText(Country);
			WebElement element1 = driver.findElement(By.xpath("//*[@id='Job_Role__c']"));
			Select select1 = new Select(element1);
			select1.selectByValue(JobRole);
			driver.findElement(By.xpath("//*[@id='Company']")).sendKeys(Company);
			WebElement element2 = driver.findElement(By.xpath("//*[@id='Organizational_Role__c']"));
			Select select2 = new Select(element2);
			select2.selectByIndex(2);
			WebElement element3 = driver.findElement(By.xpath("//*[@id='MktoPersonNotes']"));
			Select select3 = new Select(element3);
			select3.selectByIndex(3);
			driver.hideKeyboard();
			js.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.xpath("//*[@id='top-target']/div[1]/div/div[1]/form/div[48]/span/button")).click();

		}
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		String ActualError = driver.findElement(By.xpath("//*[@id='ValidMsgEmail']")).getText();
		String ExpectedError = "MUST BE VALID EMAIL.\n" + "EXAMPLE@YOURDOMAIN.COM";
		Assert.assertEquals(ActualError, ExpectedError);
		System.out.println("Invalid Email :" + ActualError);
		File file1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file1, new File("C:\\Users\\VimalaSubbaraj\\Desktop\\Selenium_udemy\\SS\\Screen2.jpg"));

	}

}
