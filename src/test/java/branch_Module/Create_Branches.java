package branch_Module;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.GenericUtility.BaseClass;
import com.POMRepositaries.BranchesPage;
import com.POMRepositaries.DashBoardPage;
import com.POMRepositaries.LoginPage;

//Create Branches
public class Create_Branches extends BaseClass {
	@Test
	public void create_Branches() throws IOException {

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
		
		// creating Branches and validating the page
		BranchesPage branchesPage = dashBoardPage.gotoBranchPage();
		String expectedBranchTabName = xLib.getExpectedDataFromExcel("expdata", "expectedBranchTabName");
		if (branchesPage.validateBoardTitle(expectedBranchTabName)) {
			Reporter.log("Branches page is displayed and varified on board name",true);
		} else {
			Reporter.log("Branches page is not displayed and varified on board name",true);
		}

		// get Add Branches pop-up and validate pop-up
		branchesPage.getAddBranchesFormPopup();
		String expectedAddBranchesPopupTitle = xLib.getExpectedDataFromExcel("expdata",
				"expectedAddBranchesPopupTitle");
		if (branchesPage.validateTheAddBranchesPopupTitle(expectedAddBranchesPopupTitle)) {
			Reporter.log("Add Braches popup is displayed and varified upon title",true);
		} else {
			Reporter.log("Add Braches popup is not displayed and varified upon title",true);
		}

		// Add branch
		String branchName = xLib.getExpectedDataFromExcel("expdata", "branchName");
		branchesPage.fillBranchesForm(branchName);
		branchesPage.saveBranchesForm();
		// validate insert pop-up
		String expectedBranchInsertPopuptext = xLib.getExpectedDataFromExcel("expdata",
				"expectedBranchInsertPopuptext");// Insert Successfully!!!
		if (branchesPage.validateInsertBranchesPopupAndAccept(expectedBranchInsertPopuptext)) {
			Reporter.log("Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Popup is not displayed and varified upon popup text",true);
		}

		// validate the created branch
		if (branchesPage.validateAddedBranches(branchName)) {
			Reporter.log("The Branch is sucessfully created",true);
		} else {
			Reporter.log("The Branch is not created",true);
		}
		
		// logout of the application
		branchesPage.logoutOfApplication();
		String expectedLogoutPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLogoutPopuptext");
		if (branchesPage.validateLogoutPopupAndAccept(expectedLogoutPopuptext)) {
			Reporter.log("Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Popup is not displayed and varified upon popup text",true);
		}
	}
}
