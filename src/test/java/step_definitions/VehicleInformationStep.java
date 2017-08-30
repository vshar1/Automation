package step_definitions;
import com.DirectoryUtil;
import com.FileDetail;
import com.SupportedFileFormat;
import com.VehicleDetails;
import com.functional.factory.WebDriverFactory;
import com.functional.pageobjects.ConfirmPage;
import com.functional.pageobjects.DvlaVehicleInfoPage;
import com.functional.pageobjects.VehicleSearchPage;
import com.functional.pageobjects.ViewVehicleDetailPage;
import com.util.page.PageLoader;

import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class VehicleInformationStep {

    private static String CURRENT_PRJ_DIRECTORY = System.getProperty("user.dir");
    private static String CURRENT_TEST_DIRECTORY = CURRENT_PRJ_DIRECTORY + "/resources";

    private WebDriver driver;
    private PageLoader page;
    private DvlaVehicleInfoPage dvlaVehicleInfoPage;
    private VehicleSearchPage vehicleSearchPage;
    private ConfirmPage confirmPage;
    private ViewVehicleDetailPage viewVehiclePage;

    public VehicleInformationStep()
    {
         driver = WebDriverFactory.create();
         page = new PageLoader(driver);
    }

    @Given("^I open vehicle information website$")
    public void iOpenVehicleInformationWebsite() throws Throwable {
        dvlaVehicleInfoPage = page.load(DvlaVehicleInfoPage.class);
        assertTrue(dvlaVehicleInfoPage.isDisplayed());
        dvlaVehicleInfoPage.clickStartNow();
    }

    @When("^I navigate to vehicle enquiry page$")
    public void iNavigateToVehicleEnquiryPage() throws Throwable {
        vehicleSearchPage = page.init(VehicleSearchPage.class);
        assertTrue(vehicleSearchPage.isDisplayed());
    }

    @Then("^I can assert the vehicle details with csv file$")
    public void iCanAssertTheVehicleDetailsWithCsvFile() throws Throwable {

        DirectoryUtil directoryUtil = new DirectoryUtil();
        directoryUtil.populateFileDetails(CURRENT_PRJ_DIRECTORY, SupportedFileFormat.CSV);

        // Current support to read all input data from the CSV File
        String csvfileName = ((FileDetail) directoryUtil.getFilesDetail().get(0)).getFileName();

        ArrayList<com.VehicleDetails> vehLst = directoryUtil.getVehicleCSVData(CURRENT_TEST_DIRECTORY + "/" + csvfileName);

        for(VehicleDetails vehicleDet :vehLst){
            vehicleSearchPage.enterVehicleRegistrationNumber(vehicleDet.getRegistration_number());
            vehicleSearchPage.clickContinue();

            confirmPage = page.init(ConfirmPage.class);
            assertTrue(confirmPage.isDisplayed());

            assertTrue(confirmPage.isExpectedVehicle(vehicleDet));
            confirmPage.chooseYes();
            confirmPage.clickContinue();

            viewVehiclePage = page.init(ViewVehicleDetailPage.class);
            assertTrue(viewVehiclePage.isDisplayed());
            assertTrue(viewVehiclePage.matchAllVehicleDetails(vehicleDet));

            dvlaVehicleInfoPage = page.load(DvlaVehicleInfoPage.class);
            dvlaVehicleInfoPage.clickStartNow();
            vehicleSearchPage = page.init(VehicleSearchPage.class);
        }
        driver.close();
    }
}