package com.functional.pageobjects;

import com.VehicleDetails;
import com.util.page.ElementHelper;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmPage extends Page {
    private ElementHelper elementHelper;

    public ConfirmPage(WebDriver driver) {
        super(driver);
        elementHelper = new ElementHelper(driver);
    }

    public boolean isExpectedVehicle(VehicleDetails vehicleDet) {
        String registrationNumber = findRegNumber();
        String make = findMake();

        if (StringUtils.equals(registrationNumber, vehicleDet.getRegistration_number())
                && StringUtils.equals(make, vehicleDet.getVehicle_make())) {
            return true;
        }
        return false;
    }

    public void chooseYes() {
        elementHelper.findElementById("Correct_True").sendKeys(Keys.SPACE);
    }

    public void chooseNo() {
        elementHelper.findElementById("Correct_False").sendKeys(Keys.SPACE);
    }

    public String findRegNumber() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][1]/span[2]");
    }

    public String findMake() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][2]/span[2]");
    }

    public String findColour() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][3]/span[2]");
    }

    public void clickContinue() {
        WebElement continueButton = elementHelper.findElementByName("Continue");
        continueButton.click();
    }
}
