package com.ui.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.MyAccountPage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({ com.ui.listeners.TestListeners.class })

public class SearchProduct extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	private MyAccountPage myAccountPage;
	private static final String SEARCH_TERM="Printed Summer Dress";

	@BeforeMethod(description = "User Login with valid creds")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginwith("dokec59345@kobace.com", "Pass123");
	}

	@Test(description = "Verify with login user is able to search the products", groups = { "e2e", "sanity" })
	public void verifyProductSearchTest() {
		boolean actualResult=myAccountPage.searchForAProduct(SEARCH_TERM)
				.isSearchTermPresentInProductList(SEARCH_TERM);
		Assert.assertEquals(actualResult, true);
	}

}
