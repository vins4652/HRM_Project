package admin_Module;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.GenericUtility.BaseClass;
import com.POMRepositaries.AdminPage;
import com.POMRepositaries.DashBoardPage;
import com.POMRepositaries.LoginPage;

//Validate by Editting both Admin Email Address and Password Textfield of HR Head Credentials  
public class Validate_the_Search_field_in_Admin_Module_for_HR_Head extends BaseClass {
	@Test
	public void validate_the_Search_field_in_Admin_Module_for_HR_Head() throws AWTException, EncryptedDocumentException, IOException {

		LoginPage loginPage = new LoginPage(driver);

		// Validating the login page
		String expectedLoginPageTitle = xLib.getExpectedDataFromExcel("expdata", "expectedLoginPageTitle");
		if (loginPage.validateLoginPageTitle(expectedLoginPageTitle)) {
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
		String expectedLoginPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLoginPopuptext");
		if (loginPage.validateLoginPopup(expectedLoginPopuptext)) {
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

		AdminPage adminPage = dashBoardPage.gotoAdminPage();

		// validate admin page
		String expectedAdminBoardName = xLib.getExpectedDataFromExcel("expdata", "expectedAdminTabName");
		if (adminPage.validateBoardTitle(expectedAdminBoardName)) {
			Reporter.log("Admin page is displayed and varified on board name",true);
		} else {
			Reporter.log("Admin page is not displayed and varified on board name",true);
		}

		// creating the admin credentials
		adminPage.getAddAdminFormPopup();

		// validate the addadmin form
		String expectedAddAdminPopupTitle = xLib.getExpectedDataFromExcel("expdata", "expectedAddAdminPopupTitle");
		if (adminPage.validateTheAddAdminPopupTitle(expectedAddAdminPopupTitle)) {
			Reporter.log("Add Admin popup is displayed and varified upon title",true);
		} else {
			Reporter.log("Add Admin popup is not displayed and varified upon title",true);
		}

		// fill the form for admin
		/*
		 * Company Id: 123 
		 * First Name: QSP 
		 * Last Name: Basavangudi 
		 * Middle Name: Head Branch 
		 * Contact No.: 12345678901 
		 * Position: HR Head 
		 * Email Address: qsphead@gmail.com 
		 * password: qsphead@123
		 */
		// get Excel sheet data
		HashMap<String, String> map = xLib.getMultipleDataFromExcel("admindata");
		adminPage.fillAdminForm(map);
		adminPage.saveAdminForm();

		// validate insert popup
		String expectedAdminInsertPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedAdminInsertPopuptext");// Insert
																														// Successfully!!!
		if (adminPage.validateInsertAdminPopupAndAccept(expectedAdminInsertPopuptext)) {
			Reporter.log("Insert Successfully!!! Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Insert Successfully!!! Popup is not displayed and varified upon popup text",true);
		}

		
		String adminName = xLib.getExpectedDataFromExcel("expdata", "adminName");
		
		//search for adminName in admin page
		adminPage.searchAdmin(adminName);
		
		if (adminPage.validateAddedAdminWithFirstName(adminName)) {
			Reporter.log("The Admin is sucessfully Searched using Search text field and varified",true);
		} else {
			Reporter.log("The Admin is not Searched using Search text field and varified",true);
		}
		
		//get the edit popup and validate it
		jLib.zoomOutTheWebpage();
		adminPage.getEditAdminPopup(adminName);
		
		String expectedEditAdminPopupTitle=xLib.getExpectedDataFromExcel("expdata", "expectedEditAdminPopupTitle");
		if(adminPage.validateTheEditAdminPopupTitle(expectedEditAdminPopupTitle)) {
			Reporter.log("Edit Admin popup is displayed and varified upon title",true);
		}else {
			Reporter.log("Edit Admin popup is not displayed and varified upon title",true);
		}
		
		//edit the details
		/*
		 * createdEditedUsername
		 * createdEditedUserPassword
		 */
		String createdEditedUsername = xLib.getExpectedDataFromExcel("expdata", "createdEditedUsername");
		String createdEditedUserPassword = xLib.getExpectedDataFromExcel("expdata", "createdEditedUserPassword");
		adminPage.editEmailAndPassword(adminName, createdEditedUsername, createdEditedUserPassword);
		
		//validate popup for sucessfully updated
		String expectedAdminEditPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedAdminEditPopuptext");;//Update Successfully!!!
		if (adminPage.validateUpdateAdminPopupAndAccept(expectedAdminEditPopuptext)) {
			Reporter.log("Update Successfully!!! Popup is displayed and varified upon popup text",true);
		} else {
			Reporter.log("Update Successfully!!! Popup is not displayed and varified upon popup text",true);
		}
		
		//Logout of application
		adminPage.logoutOfApplication();
		String expectedLogoutPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLogoutPopuptext");//Successfully Logout!
		if(adminPage.validateLogoutPopupAndAccept(expectedLogoutPopuptext)){
			Reporter.log("Successfully Logout! Popup is displayed and varified upon popup text",true);
		}else {
			Reporter.log("Successfully Logout! Popup is not displayed and varified upon popup text",true);
		}
		
		
		//validating after logout login page displayed
		if(loginPage.validateLoginPageTitle(expectedLoginPageTitle)) {
			Reporter.log("Login Page is Displayed and varified",true);
		}else {
			Reporter.log("Login page is not displayed and varified",true);
		}
		
		//validate the created edited credentials
		/*
		 * Position: HR Head
		 * Email Address: qsphead1@gmail.com
		 * password: qsphead1@123
		 */
		//login to the application
//		String position = "HR Head";
		loginPage.signInToApplication(createdEditedUsername, createdEditedUserPassword, selecttype);
		if(loginPage.validateLoginPopup(expectedLoginPopuptext)){
			Reporter.log("Login Successfully!! Popup is displayed and varified upon popup text",true);
		}else {
			Reporter.log("Login Successfully!! Popup is not displayed and varified upon popup text",true);
		}
		
		dashBoardPage=loginPage.acceptLoginPopup();
		if(dashBoardPage.validateDashboardPage(expectedHomeTabName)) {
			Reporter.log("Dashboard is displayed of the created and edited admin and varified on board name",true);
		} else {
			Reporter.log("Dashboard is not displayed of the created and edited admin and varified on board name",true);
		}
	}
}
