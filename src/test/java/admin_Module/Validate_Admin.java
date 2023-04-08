package admin_Module;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.GenericUtility.BaseClass;
import com.POMRepositaries.AdminPage;
import com.POMRepositaries.DashBoardPage;
import com.POMRepositaries.LoginPage;

//User should be able to Login with the changed Email Address and Password of HR Head credential
public class Validate_Admin extends BaseClass {
	@Test
	public void validate_Admin() throws IOException {

		LoginPage loginPage = new LoginPage(driver);

		// Validating the login page
		Boolean loginPageTitleValidation = loginPage
				.validateLoginPageTitle(xLib.getExpectedDataFromExcel("expdata", "expectedLoginPageTitle"));
//		if (loginPageTitleValidation) {
//			Reporter.log("Login Page is Displayed and varified upon Title",true);
//		} else {
//			Reporter.log("Login page is not displayed and varified upon Title",true);
//		}
		
		assertTrue(loginPageTitleValidation, "Login page is not displayed and varified upon Title");
		Reporter.log("Login Page is Displayed and varified upon Title",true);
		
		// Login to application
		// get the propery file data
		String userName = fLib.getProperyFileData("headusername");
		String password = fLib.getProperyFileData("headpassword");
		String selecttype = fLib.getProperyFileData("headselect_type");
		loginPage.signInToApplication(userName, password, selecttype);

		// Validating Popup
		boolean loginPagePopupValidation = loginPage
				.validateLoginPopup(xLib.getExpectedDataFromExcel("expdata", "expectedLoginPopuptext"));
//		if (loginPagePopupValidation) {
//			Reporter.log("Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Popup is not displayed and varified upon popup text",true);
//		}

		
		assertTrue(loginPagePopupValidation, "Popup is not displayed and varified upon popup text");
		Reporter.log("Popup is displayed and varified upon popup text",true);
		
		// Accept popup and redirect to Dashboard page
		DashBoardPage dashBoardPage = loginPage.acceptLoginPopup();

		// validate the dashboard
		String expectedHomeTabName = xLib.getExpectedDataFromExcel("expdata", "expectedHomeTabName");
//		if (dashBoardPage.validateDashboardPage(expectedHomeTabName)) {
//			Reporter.log("Dashboard is displayed and varified on board name",true);
//		} else {
//			Reporter.log("Dashboard is not displayed and varified on board name",true);
//		}
		
		assertTrue(dashBoardPage.validateDashboardPage(expectedHomeTabName), "Dashboard is not displayed and varified on board name");
		Reporter.log("Dashboard is displayed and varified on board name",true);

		AdminPage adminPage = dashBoardPage.gotoAdminPage();

		// validate admin page
		String expectedAdminBoardName = xLib.getExpectedDataFromExcel("expdata", "expectedAdminTabName");
//		if (adminPage.validateBoardTitle(expectedAdminBoardName)) {
//			Reporter.log("Admin page is displayed and varified on board name",true);
//		} else {
//			Reporter.log("Admin page is not displayed and varified on board name",true);
//		}
		
		assertTrue(adminPage.validateBoardTitle(expectedAdminBoardName), "Admin page is not displayed and varified on board name");
		Reporter.log("Admin page is displayed and varified on board name",true);

		// creating the admin credentials
		adminPage.getAddAdminFormPopup();

		// validate the addadmin form
		String expectedAddAdminPopupTitle = xLib.getExpectedDataFromExcel("expdata", "expectedAddAdminPopupTitle");
//		if (adminPage.validateTheAddAdminPopupTitle(expectedAddAdminPopupTitle)) {
//			Reporter.log("Add Admin popup is displayed and varified upon title",true);
//		} else {
//			Reporter.log("Add Admin popup is not displayed and varified upon title",true);
//		}
		
		
		assertTrue(adminPage.validateTheAddAdminPopupTitle(expectedAddAdminPopupTitle),"Add Admin popup is not displayed and varified upon title");
		Reporter.log("Add Admin popup is displayed and varified upon title",true);
		
		// fill the form for admin
		/*
		 * Company Id: 123 First Name: QSP Last Name: Basavangudi Middle Name: Head
		 * Branch Contact No.: 12345678901 Position: HR Head Email Address:
		 * qsphead@gmail.com password: qsphead@123
		 */

		// get Excel sheet data
		HashMap<String, String> map = xLib.getMultipleDataFromExcel("admindata");
		adminPage.fillAdminForm(map);
		adminPage.saveAdminForm();

		// validate insert popup
		String expectedAdminInsertPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedAdminInsertPopuptext");// Insert
																														// Successfully!!!
//		if (adminPage.validateInsertAdminPopupAndAccept(expectedAdminInsertPopuptext)) {
//			Reporter.log("Insert Successfully!!! Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Insert Successfully!!! Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(adminPage.validateInsertAdminPopupAndAccept(expectedAdminInsertPopuptext), "Insert Successfully!!! Popup is not displayed and varified upon popup text");
		Reporter.log("Insert Successfully!!! Popup is displayed and varified upon popup text",true);
		
		String expAdminName = xLib.getExpectedDataFromExcel("expdata", "adminName");
		if (adminPage.validateAddedAdminWithFirstName(expAdminName)) {
			Reporter.log("The Admin is sucessfully created");
		} else {
			Reporter.log("The Admin is not created");
		}
		
		assertTrue(adminPage.validateAddedAdminWithFirstName(expAdminName), "The Admin is not created");
		Reporter.log("The Admin is sucessfully created",true);
		
		
//		Logout of application and validate the logout popup
		adminPage.logoutOfApplication();
		String expectedLogoutPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLogoutPopuptext");// Successfully
//		if (adminPage.validateLogoutPopupAndAccept(expectedLogoutPopuptext)) {
//			Reporter.log("Successfully Logout! Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Successfully Logout! Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(adminPage.validateLogoutPopupAndAccept(expectedLogoutPopuptext), "Successfully Logout! Popup is not displayed and varified upon popup text");
		Reporter.log("Successfully Logout! Popup is displayed and varified upon popup text",true);

		// validate the created credentials
		/*
		 * createdUsername 
		 * createdUserPassword 
		 * createdSelectType
		 * 
		 * Position: HR Head 
		 * Email Address: qsphead@gmail.com 
		 * password: qsphead@123
		 */
		// login to the application with new credentials
		String createdUsername = xLib.getExpectedDataFromExcel("expdata", "createdUsername");
		String createdUserPassword = xLib.getExpectedDataFromExcel("expdata", "createdUserPassword");
		String createdSelectType = xLib.getExpectedDataFromExcel("expdata", "createdSelectType");

		loginPage.signInToApplication(createdUsername, createdUserPassword, createdSelectType);

		boolean newcredentialsloginPagePopupValidation = loginPage
				.validateLoginPopup(xLib.getExpectedDataFromExcel("expdata", "expectedLoginPopuptext"));
//		if (newcredentialsloginPagePopupValidation) {
//			Reporter.log("Login Successfully!! Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Login Successfully!! Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(newcredentialsloginPagePopupValidation, "Login Successfully!! Popup is not displayed and varified upon popup text");
		Reporter.log("Login Successfully!! Popup is displayed and varified upon popup text",true);

		dashBoardPage = loginPage.acceptLoginPopup();

		// validate the dashboard
//		if (dashBoardPage.validateDashboardPage(expectedHomeTabName)) {
//			Reporter.log("Dashboard of new credential is displayed and varified on board name",true);
//		} else {
//			Reporter.log("Dashboard of new credential is not displayed and varified on board name",true);
//		}
		
		assertTrue(dashBoardPage.validateDashboardPage(expectedHomeTabName), "Dashboard of new credential is not displayed and varified on board name");
		Reporter.log("Dashboard of new credential is displayed and varified on board name",true);
		
		
		dashBoardPage.logoutOfApplication();
		dashBoardPage.validateLogoutPopupAndAccept(expectedLogoutPopuptext);
		

	}
}
