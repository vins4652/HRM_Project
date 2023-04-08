package login_Module;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.GenericUtility.BaseClass;
import com.POMRepositaries.DashBoardPage;
import com.POMRepositaries.LoginPage;


//Login as a HR Oficer
public class Login_as_Officer extends BaseClass {
	
	@Test(groups = {"smoke","regression"})
	public void login_as_Officer() throws IOException {

		LoginPage loginPage = new LoginPage(driver);

		// Validating the login page
		Boolean loginPageTitleValidation = loginPage
				.validateLoginPageTitle(xLib.getExpectedDataFromExcel("expdata", "expectedLoginPageTitle"));
		if (loginPageTitleValidation) {
			Reporter.log("Login Page is Displayed and varified upon Title",true);
		} else {
			Reporter.log("Login page is not displayed and varified upon Title",true);
		}

		// Login to application as HR Officer
		// get the propery file data
		String userName = fLib.getProperyFileData("officerusername");
		String password = fLib.getProperyFileData("officerpassword");
		String selecttype = fLib.getProperyFileData("officerselect_type");
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
		
		// logout of the application
		dashBoardPage.logoutOfApplication();
		String expectedLogoutPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLogoutPopuptext");
		if (dashBoardPage.validateLogoutPopupAndAccept(expectedLogoutPopuptext)) {
			Reporter.log("Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Popup is not displayed and varified upon popup text",true);
		}
	}
}
