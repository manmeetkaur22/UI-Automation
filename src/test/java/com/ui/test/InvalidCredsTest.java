package com.ui.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.pojo.User;
import com.utility.LoggerUtility;

public class InvalidCredsTest extends TestBase {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final String INVALID_EMAIL_ADDRESS = "manmeet.kaur2@gmail.com";
	private static final String INVALID_PASSWORD = "manmeet@33";

	@Test(description = "Verify proper message is shown when enter invalid creds", groups = { "e2e", "sanity" })
	public void loginTest() {

		Assert.assertEquals(homePage.goToLoginPage().doLoginwithInvalidCreds(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
				.getErrorMessage(), "Authentication failed.");

	}

}
