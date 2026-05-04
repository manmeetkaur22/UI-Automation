package com.utility;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	public static ExtentSparkReporter extentSparkReporter;
	// Job of this class HTML ->Looks,reporting
	public static ExtentReports extentReports;
	// heavy lifting--data dumb into the file
	public static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	// store information about the test

	public static void setupReport(String reportName)
	{
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + reportName);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

	}
	
	public static void createExtentTest(String testName)
	{
		ExtentTest test=extentReports.createTest(testName);
		extentTest.set(test);
	}
	
	public static ExtentTest getTest()
	{
		return extentTest.get();
		
	}
	public static void flushReport()
	{
		extentReports.flush();
	}

}
