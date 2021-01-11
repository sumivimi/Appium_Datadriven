package com.ibm.mobileassignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DataDriven {

	public static void main(String[] args) throws IOException {
		DesiredCapabilities capability= new DesiredCapabilities();
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Vimala");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		AndroidDriver driver= new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		File file= new File("C:\\Users\\VimalaSubbaraj\\Desktop\\Selenium_udemy\\SS\\TestData.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("TestSheet");
		int rc= sheet.getLastRowNum();
		System.out.println("total number of rows having data= "+rc);
		for(int i=1;i<=rc;i++) {
			String username=sheet.getRow(i).getCell(0).getStringCellValue();
			String password=sheet.getRow(i).getCell(1).getStringCellValue();
			driver.get("http://demowebshop.tricentis.com/login");        


		}

	}
}
