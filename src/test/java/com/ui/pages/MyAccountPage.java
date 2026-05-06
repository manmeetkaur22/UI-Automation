package com.ui.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility{
	private static final By USER_NAME_LOCATOR=By.cssSelector(".header_user_info span");
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	public String getUserName()
	{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

	    WebElement element = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector(".header_user_info span")
	        )
	    );

	    return element.getText();	}
	

}
