package com.functional.pageobjects;

import com.util.page.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleSearchPage extends Page {
	private ElementHelper elementHelper;
	public VehicleSearchPage(WebDriver driver) {
		super(driver);
		elementHelper = new ElementHelper(driver);
	}

	public void enterVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		WebElement registrationNumberTextField = elementHelper.findElementById("Vrm");
 		//wait until the page is loaded and text field is displayed
		elementHelper.waitUntilVisibilityOf(registrationNumberTextField);
		elementHelper.setText(registrationNumberTextField, vehicleRegistrationNumber);
	}

	public void clickContinue() {
		WebElement continueButton = elementHelper.findElementByName("Continue");
		continueButton.click();
	}
}
