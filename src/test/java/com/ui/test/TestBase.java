package com.ui.test;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constant.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LamdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	protected String browser;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLamdaTest;

	@Parameters({ "browser", "isLamdaTest", "isHeadLess" })
	@BeforeMethod(description = "Load the home page of the website")
	public void setup(
			@Optional("chrome") String browser, 
			@Optional("false") boolean isLamdaTest, 
			@Optional("true") boolean isHeadLess) {
		this.isLamdaTest = isLamdaTest;
		this.browser=browser;
		String testName = Thread.currentThread().getName();
		WebDriver lamdaDriver;
		if (isLamdaTest) {
			lamdaDriver = LamdaTestUtility.intializeLamdaTestSession(browser,testName);
			homePage = new HomePage(lamdaDriver);
		}

		else {
			//running test on local machine
			logger.info("Load the home page of the website");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadLess);
		}
	}

	public BrowserUtility getInstance() {	
		return homePage;
	}

	@AfterMethod(description = "tear down to browser")
	public void tearDown() {
		if (isLamdaTest) {
			LamdaTestUtility.quitSession();
		} else {
			homePage.quit();
		}
	}
}
