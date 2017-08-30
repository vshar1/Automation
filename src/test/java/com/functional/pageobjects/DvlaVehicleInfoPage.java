package com.functional.pageobjects;

import com.util.page.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DvlaVehicleInfoPage extends Page {
	
	private static final String RELATIVE_PAGE_URL = "/get-vehicle-information-from-dvla";

	private ElementHelper elementHelper;

	public DvlaVehicleInfoPage(WebDriver driver) {
		super(driver);
		elementHelper = new ElementHelper(driver);
	}

	public void clickStartNow() {
		WebElement startNowButton = elementHelper.findElementByLinkText("Start now");
		startNowButton.click();
	}
	
	@Override
	public String getRelativeUrl() {

		return RELATIVE_PAGE_URL;
	}
}
