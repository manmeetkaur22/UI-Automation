package com.ui.pages;

import static com.constant.Env.QA;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constant.Browser;
import com.utility.BrowserUtility;
import com.utility.JsonUtility;
import com.utility.LoggerUtility;

public final class HomePage extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);// To call the parent class constructor from the child class
		// gotowebsite(readProperty(QA, "URL"));
		gotowebsite(JsonUtility.readJson(QA).getUrl());// TODO Auto-generated constructor stub
	}

	public HomePage(WebDriver driver) {
		super(driver);
		gotowebsite(JsonUtility.readJson(QA).getUrl());
	}

	public LoginPage goToLoginPage()// Page functions// In the page design pattern void return type cannot be used
	{
		logger.info("Trying to perform login in the login page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;

	}

	
}

//Sign in locator value is not changed, so marked it as final, final variables are
//static
//Variable inside the class in instance variable
//All the locator values are inside the class