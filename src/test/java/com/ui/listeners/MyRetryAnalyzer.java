package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constant.Env;
import com.utility.JsonUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {
//	private static final int MAX_NUMBERS_OF_ATTEMPTS =Integer.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUMBERS_OF_ATTEMPTS"));
	private static final int MAX_NUMBERS_OF_ATTEMPTS =JsonUtility.readJson(Env.QA).getMAX_NUMBERS_OF_ATTEMPTS();
	private static int currentAttempts = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempts <= 1) {
			currentAttempts++;
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
