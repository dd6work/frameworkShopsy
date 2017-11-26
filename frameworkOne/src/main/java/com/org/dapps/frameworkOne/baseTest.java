package com.org.dapps.frameworkOne;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pages.contactUs;
import pages.homePage;

public class baseTest{
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest testLog;
	homePage homeObj;
	
//	public baseTest(WebDriver driver) {
//		this.driver=driver;
//	}
	
	@BeforeSuite
	public void config() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMddhhmmss");
		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
		report = new ExtentReports(System.getProperty("user.dir")+"/Reports/Samplereport"+(dateFormat.format(new Date()))+".html", true);

	}
	@BeforeTest
	public void initialize() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.navigate().refresh();
		testLog = report.startTest("Zoplay");
		this.homeObj = new contactUs(driver,testLog);
	}
	
	@AfterTest
	public void termination() {
		driver.quit();
		report.endTest(testLog);
		
	}
	
	@AfterSuite
	public void finish() {
		report.flush();
		report.close();
	}
	

}
