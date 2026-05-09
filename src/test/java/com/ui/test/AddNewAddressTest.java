package com.ui.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.Address;
import com.utility.FakerAddressUtility;

public class AddNewAddressTest extends TestBase {
	private MyAccountPage myAccountPage;
	private AddressPage addressPage;
	private Address address;
	
	@BeforeMethod(description = "User Login with valid creds")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginwith("dokec59345@kobace.com", "	");
		address=FakerAddressUtility.getFakeAddress();
	}

	@Test
	public void addNewAddress() {
		myAccountPage.gotoAddressPage().saveAddress(address);
	}
	

}
