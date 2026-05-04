package com.ui.listeners;

import static com.utility.ExtentReporterUtility.createExtentTest;
import static com.utility.ExtentReporterUtility.getTest;
import static com.utility.ExtentReporterUtility.setupReport;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListeners implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	// 3 class used when you extent report
//	ExtentSparkReporter extentSparkReporter;
//	// Job of this class HTML ->Looks,reporting
//	ExtentReports extentReports;
//	// heavy lifting--data dumb into the file
//	ExtentTest extentTest;
//	// store information about the test

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
		getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");

	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		getTest().log(Status.FAIL, result.getThrowable().getMessage());
		logger.info("Capturing screenshot of failed tests");
		Object testClass=result.getInstance();
		BrowserUtility browserUtility=((TestBase)testClass).getInstance();
		String screenShotPath=browserUtility.takeScreenshot(result.getMethod().getMethodName());
		logger.info("Attaching to the screenshot to the HTML file");

		getTest().addScreenCaptureFromPath(screenShotPath);

	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");

	}

	public void onStart(ITestContext context) {
		logger.info("Test suite started");
		setupReport("report.html");

	}

	public void onFinish(ITestContext context) {
		logger.info("Test suite completed");
		ExtentReporterUtility.flushReport();
	}

}
