package com.ui.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({ com.ui.listeners.TestListeners.class })

public class LoginTest extends TestBase{
	/*
	 * Test Method 1. Test script must be small 2. You cannot have conditional
	 * statements, loops,try catch 3. Reduce the use of local variable 4. Atleast
	 * one assertions
	 */

	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Test(description = "Verify with valid user is able to login into application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataProvider.loginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {

		Assert.assertEquals(
				homePage.goToLoginPage().doLoginwith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Manmeet Kaur");

	}

	@Test(description = "Verify with valid user is able to login into application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataProvider.loginDataProvider.class, dataProvider = "LoginTestCSVDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginCSVTest(User user) {
		Assert.assertEquals(
				homePage.goToLoginPage().doLoginwith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Manmeet Kaur");

	}

}
