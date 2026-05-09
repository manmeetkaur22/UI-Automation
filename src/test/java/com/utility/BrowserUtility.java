package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constant.Browser;

//Parent class always have abstract keyword
// If you dont to want to create the object of class marked it as abstract
//abstract class have constrcutor, the job of child class is to call the parent class constructor
public abstract class BrowserUtility {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	// Variable inside class and non static is called instance variable.
	// instance variable created in heap memory, default value is 0
	private Logger logger = LoggerUtility.getLogger(this.getClass());
	private WebDriverWait wait;

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);// Job of the constructor is used to initialize the instance variable
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching the browser" + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				// options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));

			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20L));

			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20L));

			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20L));

			}
		} else {
			logger.error("Invalid browser" + browserName);
		}
	    wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20L));

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
		logger.info("Finding element with locator" + locator);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.info("Element found and now performing click operation ");
		element.click();
	}

	public void clickOnCheckBox(By locator) {
		logger.info("Finding element with locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element found and now performing click operation ");
		element.click();
	}

	public void clickOn(WebElement element) {
		logger.info("Element found and now performing click operation ");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element found and now entering text");
		element.sendKeys(textToEnter);
	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {
		logger.info("Finding element with locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element found and now special key");
		element.sendKeys(keyToEnter);
	}

	public List<String> getAllVisibleText(By locator) {
		logger.info("Finding All Elements with the locator" + locator);

		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Elements Found and now printing the List of Elements");
		List<String> visibleTextList = new ArrayList<String>();
		for (WebElement element : elementList) {
			System.out.println(getVisibleText(element));
			visibleTextList.add(getVisibleText(element));
		}

		return visibleTextList;

	}

	public List<WebElement> getAllElement(By locator) {
		logger.info("Finding All Elements with the locator" + locator);

		List<WebElement> elementList = driver.get().findElements(locator);
		return elementList;

	}

	public String getVisibleText(By locator) {
		logger.info("Finding Element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now returning the visibile " + element.getText());

		return element.getText();
	}

	public String getVisibleText(WebElement element) {
		logger.info("Returning the visibile Text" + element.getText());

		return element.getText();

	}

	public void selectFromDropDown(By dropDownLocator, String optionToSelect) {
		WebElement dropdown = driver.get().findElement(dropDownLocator);

		Select select = new Select(dropdown);

		String value = select.getOptions().stream().filter(option -> option.getText().trim().equals(optionToSelect))
				.findFirst().get().getAttribute("value");

		JavascriptExecutor js = (JavascriptExecutor) driver.get();

		js.executeScript("arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('change'));", dropdown,
				value);
	}

	public void clear(By textBoxLocator) {
		logger.info("Clear the Text" + textBoxLocator);
		WebElement element = driver.get().findElement(textBoxLocator);
		element.clear();
	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		String path = "./screenshots/" + name + " - " + timeStamp + ".png";
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
