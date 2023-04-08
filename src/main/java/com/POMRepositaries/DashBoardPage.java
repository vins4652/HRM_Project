package com.POMRepositaries;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtility.WebDriverUtility;

public class DashBoardPage extends WebDriverUtility {
	
	WebDriver driver;
	public DashBoardPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

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
	
	@FindBy(xpath = "//div[@class='row mb-2']//h1")
	private WebElement dashboadName;
	
	public boolean validateDashboardPage(Object expectedDashboardName) {
		String boardName = getTagtexOfElement(driver, dashboadName);
		if(boardName.equals(expectedDashboardName)) {
			return true;
		}
		return false;
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
	
	public CorporatePage gotoCorporatePage() {
		CorporateModule.click();
		addCorporateLink.click();
		return new CorporatePage(driver); 
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
