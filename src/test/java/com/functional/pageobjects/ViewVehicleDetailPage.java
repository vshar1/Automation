package com.functional.pageobjects;

import com.VehicleDetails;
import com.util.page.ElementHelper;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

public class ViewVehicleDetailPage extends Page {

    private ElementHelper elementHelper;

    public ViewVehicleDetailPage(WebDriver driver) {
        super(driver);
        elementHelper = new ElementHelper(driver);
    }

    public boolean matchAllVehicleDetails(VehicleDetails vehicleDet) {
        if (StringUtils.equals(findRegistrationNumber(), vehicleDet.getRegistration_number())
                && StringUtils.equals(findDateOfFirstRegistration(), vehicleDet.getRegistration_date())
                && StringUtils.equals(findYearOfManufacture(), vehicleDet.getManufacturer_year())) {
            return true;
        }
        return false;
    }

    public String findRegistrationNumber() {
        return elementHelper.findElementByCssClass("reg-mark").getText();
    }

    public String findMake() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][1]/span[2]");
    }

    public String findDateOfFirstRegistration() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][2]/span[2]");
    }

    public String findYearOfManufacture() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][3]/span[2]");
    }

    public String findCylinderCapacity() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][4]/span[2]");
    }

    public String findCOEmission() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][5]/span[2]");
    }

    public String findFuelType() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][6]/span[2]");
    }

    public String findExportMarker() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][7]/span[2]");
    }

    public String findVehicleStatus() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][8]/span[2]");
    }

    public String findColour() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][9]/span[2]");
    }

    public String findVehicleTypeApproval() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][10]/span[2]");
    }

    public String findWheelplan() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][11]/span[2]");
    }

    public String findRevenueWeight() {
        return elementHelper.findElementByXPath("//li[@class='list-summary-item'][12]/span[2]");
    }

}
