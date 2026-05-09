package com.ui.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class SearchResultPage extends BrowserUtility {

	private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//span[@class=\"lighter\"]");
	private static final By ALL_PRODUCTS_LIST_NAME = By.xpath("//h5[@itemprop=\"name\"]/a");

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	public String getSearchResultTitle() {
		return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
	}

	public void getAllDressName() {
		getAllVisibleText(ALL_PRODUCTS_LIST_NAME);
	}

	public boolean isSearchTermPresentInProductList(String searchTerm) {
		List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(" "));
		List<String> productNamesList = getAllVisibleText(ALL_PRODUCTS_LIST_NAME);
		boolean result = productNamesList.stream()
				.anyMatch(name -> (keywords.stream().anyMatch(name.toLowerCase()::contains)));

		return result;

	}
	public ProductDetailPage clickOnTheProductAtIndex(int index)
	{
		clickOn(getAllElement(ALL_PRODUCTS_LIST_NAME).get(index));
		return new ProductDetailPage(getDriver());
	}
}
