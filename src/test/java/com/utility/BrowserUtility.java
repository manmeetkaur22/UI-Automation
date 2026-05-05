package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constant.Browser;

//Parent class always have abstract keyword
// If you dont to want to create the object of class marked it as abstract
//abstract class have constrcutor, the job of child class is to call the parent class constructor
public abstract class BrowserUtility {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	// Variable inside class and non static is called instance variable.
	// instance variable created in heap memory, default value is 0
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);// Job of the constructor is used to initialize the instance variable
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching the browser" + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-sized=1920,1080");
				driver.set(new ChromeDriver(options));

			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--disbale-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("--disbale-gpu");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}
		} else {
			logger.error("Invalid browser" + browserName);
		}
	}

	public void gotowebsite(String url) {
		logger.info("Visting the website" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximize the browser view");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Findling element with locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing click operation ");

		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Findling element with locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now entering text");
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Findling element with locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning the text" + element.getText());

		return element.getText();

	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + name + " - "
				+ timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

	public void quit() {
		driver.get().quit();
	}
}
