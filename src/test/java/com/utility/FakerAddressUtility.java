package com.utility;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.ui.pojo.Address;

public class FakerAddressUtility {
	
	public static void main(String args[])
	{
		getFakeAddress();
	}

	public static Address getFakeAddress()
	{
		Faker faker=new Faker(Locale.US);
		Address address=new Address(
				faker.company().name(),
				faker.address().buildingNumber(),
				faker.address().streetAddress(),
				faker.address().city(),
				faker.numerify("#####"),
				faker.phoneNumber().cellPhone(),
				faker.phoneNumber().cellPhone(),
				"other", "office address", faker.address().state());
		
		return address;
	
	}
}
