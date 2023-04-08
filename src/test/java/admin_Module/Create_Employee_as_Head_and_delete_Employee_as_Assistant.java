package admin_Module;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.GenericUtility.BaseClass;
import com.GenericUtility.IPathConstant;
import com.POMRepositaries.BranchesPage;
import com.POMRepositaries.CorporatePage;
import com.POMRepositaries.DashBoardPage;
import com.POMRepositaries.EmployeePage;
import com.POMRepositaries.LoginPage;

public class Create_Employee_as_Head_and_delete_Employee_as_Assistant extends BaseClass {
	
	@Test
	public void create_Employee_as_Head_and_delete_Employee_as_Assistant() throws IOException {

		
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

		// creating corporate and validating the page
		CorporatePage corporatePage = dashBoardPage.gotoCorporatePage();
		String expectedCorporateTabName = xLib.getExpectedDataFromExcel("expdata", "expectedCorporateTabName");
//		if (corporatePage.validateBoardTitle(expectedCorporateTabName)) {
//			Reporter.log("Corporate page is displayed and varified on board name",true);
//		} else {
//			Reporter.log("Corporate page is not displayed and varified on board name",true);
//		}
		
		assertTrue(corporatePage.validateBoardTitle(expectedCorporateTabName), "Corporate page is not displayed and varified on board name");
		Reporter.log("Corporate page is displayed and varified on board name",true);

		// get Add Corporate popup
		corporatePage.getAddCorporateFormPopup();

		// validate the corporate popup text
		String expectedAddCorporatePopupTitle = xLib.getExpectedDataFromExcel("expdata",
				"expectedAddCorporatePopupTitle");
//		if (corporatePage.validateTheAddCorporatePopupTitle(expectedAddCorporatePopupTitle)) {
//			Reporter.log("Add Corporate popup is displayed and varified upon title",true);
//		} else {
//			Reporter.log("Add Corporate popup is not displayed and varified upon title",true);
//		}
		
		assertTrue(corporatePage.validateTheAddCorporatePopupTitle(expectedAddCorporatePopupTitle), "Add Corporate popup is not displayed and varified upon title");
		Reporter.log("Add Corporate popup is displayed and varified upon title",true);

		// fill the Add corporate pop-up
		String corporateName = xLib.getExpectedDataFromExcel("expdata", "corporateName");
		corporatePage.fillCorporateForm(corporateName);
		corporatePage.saveCorporateForm();

		// validate corporate insert pop-up
		String expectedInsertPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedInsertPopuptext");
//		if (corporatePage.validateInsertCoporatePopupAndAccept(expectedInsertPopuptext)) {
//			Reporter.log("Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(corporatePage.validateInsertCoporatePopupAndAccept(expectedInsertPopuptext), "Popup is not displayed and varified upon popup text");
		Reporter.log("Popup is displayed and varified upon popup text",true);
		
		// validate the added corporate
//		if (corporatePage.validateAddedCorporate(corporateName)) {
//			Reporter.log("The Corporation is sucessfully created",true);
//		} else {
//			Reporter.log("The Corporation is not created",true);
//		}
		
		assertTrue(corporatePage.validateAddedCorporate(corporateName), "The Corporation is not created");
		Reporter.log("The Corporation is sucessfully created",true);
		
		// creating Branches and validating the page
		BranchesPage branchesPage = corporatePage.gotoBranchPage();
		String expectedBranchTabName = xLib.getExpectedDataFromExcel("expdata", "expectedBranchTabName");
//		if (branchesPage.validateBoardTitle(expectedBranchTabName)) {
//			Reporter.log("Branches page is displayed and varified on board name",true);
//		} else {
//			Reporter.log("Branches page is not displayed and varified on board name",true);
//		}
		
		assertTrue(branchesPage.validateBoardTitle(expectedBranchTabName), "Branches page is not displayed and varified on board name");
		Reporter.log("Branches page is displayed and varified on board name",true);

		// get Add Branches pop-up and validate pop-up
		branchesPage.getAddBranchesFormPopup();
		String expectedAddBranchesPopupTitle = xLib.getExpectedDataFromExcel("expdata",
				"expectedAddBranchesPopupTitle");
//		if (branchesPage.validateTheAddBranchesPopupTitle(expectedAddBranchesPopupTitle)) {
//			Reporter.log("Add Braches popup is displayed and varified upon title",true);
//		} else {
//			Reporter.log("Add Braches popup is not displayed and varified upon title",true);
//		}
		
		assertTrue(branchesPage.validateTheAddBranchesPopupTitle(expectedAddBranchesPopupTitle), "Add Braches popup is not displayed and varified upon title");
		Reporter.log("Add Braches popup is displayed and varified upon title",true);

		// Add branch
		String branchName = xLib.getExpectedDataFromExcel("expdata", "branchName");
		branchesPage.fillBranchesForm(branchName);
		branchesPage.saveBranchesForm();
		// validate insert pop-up
		String expectedBranchInsertPopuptext = xLib.getExpectedDataFromExcel("expdata",
				"expectedBranchInsertPopuptext");// Insert Successfully!!!
//		if (branchesPage.validateInsertBranchesPopupAndAccept(expectedBranchInsertPopuptext)) {
//			Reporter.log("Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(branchesPage.validateInsertBranchesPopupAndAccept(expectedBranchInsertPopuptext), "Popup is not displayed and varified upon popup text");
		Reporter.log("Popup is displayed and varified upon popup text",true);
		
		
		// validate the created branch
//		if (branchesPage.validateAddedBranches(branchName)) {
//			Reporter.log("The Branch is sucessfully created",true);
//		} else {
//			Reporter.log("The Branch is not created",true);
//		}
		
		assertTrue(branchesPage.validateAddedBranches(branchName), "The Branch is not created");		
		Reporter.log("The Branch is sucessfully created",true);
		

		// redirect to employee model
		EmployeePage employeePage = branchesPage.gotoEmployeePage();

		// validate employee page
		String expectedEmployeeTabName = xLib.getExpectedDataFromExcel("expdata", "expectedEmployeeTabName");
//		if (employeePage.validateBoardTitle(expectedEmployeeTabName)) {
//			Reporter.log("Employee page is displayed and varified on board name",true);
//		} else {
//			Reporter.log("Employee page is not displayed and varified on board name",true);
//		}
		assertTrue(employeePage.validateBoardTitle(expectedEmployeeTabName), "Employee page is not displayed and varified on board name");
		Reporter.log("Employee page is displayed and varified on board name",true);
		
		// validate and fill the form
		employeePage.getAddEmployeeForm();
		String expectedAddEmployeePopupTitle = xLib.getExpectedDataFromExcel("expdata",
				"expectedAddEmployeePopupTitle");
//		if (employeePage.validateTheAddAdminPopupTitle(expectedAddEmployeePopupTitle)) {
//			Reporter.log("Add Employee popup is displayed and varified upon title",true);
//		} else {
//			Reporter.log("Add Employee popup is not displayed and varified upon title",true);
//		}
		
		assertTrue(employeePage.validateTheAddAdminPopupTitle(expectedAddEmployeePopupTitle), "Add Employee popup is not displayed and varified upon title");
		Reporter.log("Add Employee popup is displayed and varified upon title",true);

		// get Excel sheet data
		HashMap<String, String> map = xLib.getMultipleDataFromExcel("empdata");

		employeePage.fillEmployeeForm(map, IPathConstant.UPLOADIMAGEPATH, IPathConstant.UPLOADFILEPATH);
		employeePage.saveEmployeeForm();
		String expectedEmployeeInsertPopuptext = xLib.getExpectedDataFromExcel("expdata",
				"expectedEmployeeInsertPopuptext");// Insert Successfully!!!
//		if (employeePage.validateInsertAdminPopupAndAccept(expectedEmployeeInsertPopuptext)) {
//			Reporter.log("Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(employeePage.validateInsertAdminPopupAndAccept(expectedEmployeeInsertPopuptext), "Popup is not displayed and varified upon popup text");
		Reporter.log("Popup is displayed and varified upon popup text",true);
		
		
		
		String employeeName = xLib.getExpectedDataFromExcel("expdata", "createdEmpName");
//		if (employeePage.validateAddedEmployee(employeeName)) {
//			Reporter.log("The Employee is sucessfully created");
//		} else {
//			Reporter.log("The Employee is not created");
//		}
		
		assertTrue(employeePage.validateAddedEmployee(employeeName), "The Employee is not created");
		Reporter.log("The Employee is sucessfully created");

		// logout of the application
		employeePage.logoutOfApplication();
		String expectedLogoutPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLogoutPopuptext");
//		if (employeePage.validateLogoutPopupAndAccept(expectedLogoutPopuptext)) {
//			Reporter.log("Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Popup is not displayed and varified upon popup text",true);
//		}

		assertTrue(employeePage.validateLogoutPopupAndAccept(expectedLogoutPopuptext), "Popup is not displayed and varified upon popup text");
		Reporter.log("Popup is displayed and varified upon popup text",true);
		
		
		String assistantUsername = fLib.getProperyFileData("assistantusername");
		String assistantPassword = fLib.getProperyFileData("assistantpassword");
		String assistantSelectType = fLib.getProperyFileData("assistantselect_type");
		// Login as a Assistant
		loginPage.signInToApplication(assistantUsername, assistantPassword, assistantSelectType);

		// Validating Popup
		boolean loginPagePopupValidationAsAssistant = loginPage
				.validateLoginPopup(xLib.getExpectedDataFromExcel("expdata", "expectedLoginPopuptext"));
//		if (loginPagePopupValidationAsAssistant) {
//			Reporter.log("Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(loginPagePopupValidationAsAssistant, "Popup is not displayed and varified upon popup text");
		Reporter.log("Popup is displayed and varified upon popup text",true);
		
		// Accept popup and redirect to Dashboard page
		loginPage.acceptLoginPopup();

		// validate the dashboard
//		if (dashBoardPage.validateDashboardPage(expectedHomeTabName)) {
//			Reporter.log("Dashboard is displayed and varified on board name",true);
//		} else {
//			Reporter.log("Dashboard is not displayed and varified on board name",true);
//		}
		
		
		
		assertTrue(dashBoardPage.validateDashboardPage(expectedHomeTabName), "Dashboard is not displayed and varified on board name");
		Reporter.log("Dashboard is displayed and varified on board name",true);
		
		dashBoardPage.gotoEmployeePage();
		// deleted employee
		String empName = xLib.getExpectedDataFromExcel("expdata", "empName");
		employeePage.deleteEmployeeAsHrAssistant(empName);

		String expextedDeletePopupText = xLib.getExpectedDataFromExcel("expdata", "expextedDeletePopupText");
//		if (employeePage.validateDeletePopupAndAccept(expextedDeletePopupText)) {
//			Reporter.log("Delete Successfully!!! popup is displayed",true);
//		} else {
//			Reporter.log("Delete Successfully!!! popup is not displayed",true);
//		}
		
		assertTrue(employeePage.validateDeletePopupAndAccept(expextedDeletePopupText), "Delete Successfully!!! popup is not displayed");
		Reporter.log("Delete Successfully!!! popup is displayed",true);
		
		// Log out as a assistant
		employeePage.logoutOfApplication();
//		if (employeePage.validateLogoutPopupAndAccept(expectedLogoutPopuptext)) {
//			Reporter.log("Popup is displayed and varified upon popup text",true);
//		} else {
//			Reporter.log("Popup is not displayed and varified upon popup text",true);
//		}
		
		assertTrue(employeePage.validateLogoutPopupAndAccept(expectedLogoutPopuptext), "Popup is not displayed and varified upon popup text");
		Reporter.log("Popup is displayed and varified upon popup text",true);
	}

}