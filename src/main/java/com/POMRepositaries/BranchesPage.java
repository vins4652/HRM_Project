package com.POMRepositaries;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtility.WebDriverUtility;

public class BranchesPage extends WebDriverUtility {
	
	WebDriver driver;
	public BranchesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//button[normalize-space()='Add Branches']")
	private WebElement addBranchesPopupButton;
	
	@FindBy(xpath = "//div[@class='row mb-2']//h1")
	private WebElement dashBoardTitle;
	
	@FindBy(xpath = "//h4[text()='Add Branches']")
	private WebElement addBranchesFormPopupTitle;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveBranchesFormButton;
	
	@FindBy(name = "branches_name")
	private WebElement branchNameTF;
	
	@FindBy(css = "span.brand-text.font-weight-light")
	private WebElement brandNameAndDashBoardLink;
	
	@FindBy(className = "d-block")
	private WebElement loginUserMail;
	
	@FindBy(xpath = "//p[text()='Dashboard']/../..")
	private WebElement dashboard;
	
	@FindBy(xpath = "//p[contains(text(),'CORPORATE')]")
	private WebElement CorporateModule;
	
	@FindBy(xpath = "//p[contains(text(),'Add Corporate')]")
	private WebElement addCorporateLink;
	
	@FindBy(xpath = "//p[contains(text(),'BRANCHES')]")
	private WebElement branchModule;
	
	@FindBy(xpath = "//p[contains(text(),'Add Braches')]")
	private WebElement addBranchLink;
	
	@FindBy(css = "tbody tr td:nth-child(2)")
	private List<WebElement> branchesNames;
	
	@FindBy(xpath = "//p[contains(text(),'EMPLOYEE')]")
	private WebElement employeeModule;
	
	@FindBy(xpath = "//p[contains(text(),'Add Employee')]")
	private WebElement addemployeeLink;
	
	@FindBy(xpath = "//p[contains(text(),'ADMIN')]")
	private WebElement adminModule;
	
	@FindBy(xpath = "//p[contains(text(),'Add Admin')]")
	private WebElement addAdminLink;
	
	@FindBy(xpath = "//b[text()='Welcome!,']")
	private WebElement profileLink;
	
	@FindBy(xpath = "//a[normalize-space()='Log Out']")
	private WebElement logout;
	
	public boolean validateBoardTitle(String expectedBoardTitle) {
		String actualTitle = getTagtexOfElement(driver, dashBoardTitle);
		if(actualTitle.equals(expectedBoardTitle)) {
			return true;
		}
		return false;
	}
	
	public void getAddBranchesFormPopup() {
		clickOnVisibleElement(driver,addBranchesPopupButton);
	}
	
	public boolean validateTheAddBranchesPopupTitle(String expectedBranchesFormTitle) {
		String PopupTitle = getTagtexOfElement(driver,addBranchesFormPopupTitle);
		if(PopupTitle.equals(expectedBranchesFormTitle)) {
			return true;
		}
		return false;
	}
	
	public void fillBranchesForm(String branchName) {
		sendkeysOnVisibleTextField(driver, branchNameTF, branchName);
	}
	
	public void saveBranchesForm() {
		clickOnVisibleElement(driver, saveBranchesFormButton);
	}
	
	public boolean validateInsertBranchesPopupAndAccept(String insertAlertText) {
		Alert insertAlert = explicitWaitForAlert(driver);
		if(insertAlert.getText().equals(insertAlertText)) {
			driver.switchTo().alert().accept();
			return true;
		}
		return false;
	}
	
	public boolean validateAddedBranches(String branchName) {
		boolean flag = false;
		for (WebElement corpName : branchesNames) {
			if(corpName.getText().equals(branchName)){ 
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public String getBrandName() {
		return brandNameAndDashBoardLink.getText();
	}
	
	public String getLoginUserMailId() {
		return loginUserMail.getText();
	}
	
	public void gotoDashboard() {
		dashboard.click();
	}
	
	public BranchesPage gotoCorporatePage() {
		CorporateModule.click();
		addCorporateLink.click();
		return new BranchesPage(driver); 
	}
	
	public BranchesPage gotoBranchPage() {
		branchModule.click();
		addBranchLink.click();
		return new BranchesPage(driver);
	}
	
	public EmployeePage gotoEmployeePage() {
		employeeModule.click();
		addemployeeLink.click();
		return new EmployeePage(driver);
	}
	
	public AdminPage gotoAdminPage() {
		adminModule.click();
		addAdminLink.click();
		return new AdminPage(driver);
	}
	
	
	public void logoutOfApplication() {
		clickOnVisibleElement(driver, profileLink);
		clickOnVisibleElement(driver, logout);
	}
	
	public boolean validateLogoutPopupAndAccept(String logoutAlertText) {
		Alert logoutAlert = explicitWaitForAlert(driver);
		if(logoutAlert.getText().equals(logoutAlertText)) {
			driver.switchTo().alert().accept();
			return true;
		}
		return false;
	}
	
	

	
}
