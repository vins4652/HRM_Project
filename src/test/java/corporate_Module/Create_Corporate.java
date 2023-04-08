package corporate_Module;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.GenericUtility.BaseClass;
import com.POMRepositaries.BranchesPage;
import com.POMRepositaries.CorporatePage;
import com.POMRepositaries.DashBoardPage;
import com.POMRepositaries.LoginPage;

//Create Corporate
public class Create_Corporate extends BaseClass {
	@Test
	public void create_Corporate() throws IOException {


		LoginPage loginPage = new LoginPage(driver);

		// Validating the login page
		Boolean loginPageTitleValidation = loginPage
				.validateLoginPageTitle(xLib.getExpectedDataFromExcel("expdata", "expectedLoginPageTitle"));
		if (loginPageTitleValidation) {
			Reporter.log("Login Page is Displayed and varified upon Title",true);
		} else {
			Reporter.log("Login page is not displayed and varified upon Title",true);
		}

		// Login to application
		// get the propery file data
		String userName = fLib.getProperyFileData("headusername");
		String password = fLib.getProperyFileData("headpassword");
		String selecttype = fLib.getProperyFileData("headselect_type");

		loginPage.signInToApplication(userName, password, selecttype);

		// Validating Popup
		boolean loginPagePopupValidation = loginPage
				.validateLoginPopup(xLib.getExpectedDataFromExcel("expdata", "expectedLoginPopuptext"));
		if (loginPagePopupValidation) {
			Reporter.log("Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Popup is not displayed and varified upon popup text",true);
		}

		// Accept popup and redirect to Dashboard page
		DashBoardPage dashBoardPage = loginPage.acceptLoginPopup();

		// validate the dashboard
		String expectedHomeTabName = xLib.getExpectedDataFromExcel("expdata", "expectedHomeTabName");
		if (dashBoardPage.validateDashboardPage(expectedHomeTabName)) {
			Reporter.log("Dashboard is displayed and varified on board name",true);
		} else {
			Reporter.log("Dashboard is not displayed and varified on board name",true);
		}
		
		// creating corporate and validating the page
		CorporatePage corporatePage = dashBoardPage.gotoCorporatePage();
		String expectedCorporateTabName = xLib.getExpectedDataFromExcel("expdata", "expectedCorporateTabName");
		if (corporatePage.validateBoardTitle(expectedCorporateTabName)) {
			Reporter.log("Corporate page is displayed and varified on board name",true);
		} else {
			Reporter.log("Corporate page is not displayed and varified on board name",true);
		}

		// get Add Corporate popup
		corporatePage.getAddCorporateFormPopup();

		// validate the corporate popup text
		String expectedAddCorporatePopupTitle = xLib.getExpectedDataFromExcel("expdata",
				"expectedAddCorporatePopupTitle");
		if (corporatePage.validateTheAddCorporatePopupTitle(expectedAddCorporatePopupTitle)) {
			Reporter.log("Add Corporate popup is displayed and varified upon title",true);
		} else {
			Reporter.log("Add Corporate popup is not displayed and varified upon title",true);
		}

		// fill the Add corporate pop-up
		String corporateName = xLib.getExpectedDataFromExcel("expdata", "corporateName");
		corporatePage.fillCorporateForm(corporateName);
		corporatePage.saveCorporateForm();

		// validate corporate insert pop-up
		String expectedInsertPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedInsertPopuptext");
		if (corporatePage.validateInsertCoporatePopupAndAccept(expectedInsertPopuptext)) {
			Reporter.log("Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Popup is not displayed and varified upon popup text",true);
		}

		// validate the added corporate
		if (corporatePage.validateAddedCorporate(corporateName)) {
			Reporter.log("The Corporation is sucessfully created",true);
		} else {
			Reporter.log("The Corporation is not created",true);
		}

		// creating Branches and validating the page
		BranchesPage branchesPage = corporatePage.gotoBranchPage();
		String expectedBranchTabName = xLib.getExpectedDataFromExcel("expdata", "expectedBranchTabName");
		if (branchesPage.validateBoardTitle(expectedBranchTabName)) {
			Reporter.log("Branches page is displayed and varified on board name",true);
		} else {
			Reporter.log("Branches page is not displayed and varified on board name",true);
		}
		
		// logout of the application
		corporatePage.logoutOfApplication();
		String expectedLogoutPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLogoutPopuptext");
		if (branchesPage.validateLogoutPopupAndAccept(expectedLogoutPopuptext)) {
			Reporter.log("Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Popup is not displayed and varified upon popup text",true);
		}
	}
}
