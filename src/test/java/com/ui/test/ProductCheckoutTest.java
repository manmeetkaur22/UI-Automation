package com.ui.test;

import static com.constant.Size.L;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.pages.SearchResultPage;
import com.utility.LoggerUtility;

@Listeners({ com.ui.listeners.TestListeners.class })

public class ProductCheckoutTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	private SearchResultPage searchResultPage;
	private static final String SEARCH_TERM = "Printed Summer Dress";

	@BeforeMethod(description = "User Login with valid creds")
	public void setup() {
		searchResultPage = homePage.goToLoginPage().doLoginwith("dokec59345@kobace.com", "Pass123")
				.searchForAProduct(SEARCH_TERM);
	}

	@Test(description = "Verify with login user is able to buy product", groups = { "e2e", "sanity" })
	public void checkoutTest() {
		String result=searchResultPage.clickOnTheProductAtIndex(0).changeSize(L).addProductCart().proceedToCheckout()
		.gotoConfirmAddressPage().gotoShippmentPage().gotoPaymentPage().makePaymentByWire();
		
		Assert.assertTrue(result.contains("complete"));
		
	}

}
