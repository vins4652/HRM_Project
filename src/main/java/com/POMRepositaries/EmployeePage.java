package com.POMRepositaries;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GenericUtility.WebDriverUtility;

public class EmployeePage extends WebDriverUtility {

	WebDriver driver;

	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ("//button[contains(text(),'Add Employee')]"))
	private WebElement addEmployeeButton;

	@FindBy(xpath = "//div[@class='row mb-2']//h1")
	private WebElement dashBoardTitle;
	
	@FindBy(xpath = "//h4[text()='Add Employee']")
	private WebElement addEmployeeFormPopupTitle;

	@FindBy(xpath = ("//h4[text()='Add Employee']/../..//input[@name='employee_companyid']"))
	private WebElement companyID;

	public void enterCompanyId(String id) {
		companyID.sendKeys(id);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_firstname']")
	private WebElement firstNameTF;

	public void enterfirstNameTF(String firstname) {
		firstNameTF.sendKeys(firstname);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_lastname']")
	private WebElement lastName;

	public void enterlastName(String lastname) {
		lastName.sendKeys(lastname);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_middlename']")
	private WebElement middleName;

	public void entermiddleName(String midleName) {
		middleName.sendKeys(midleName);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='branches_datefrom']")
	private WebElement branchesDateFrom;

	public void enterbranchesDateFrom(String datefrom) {
		branchesDateFrom.sendKeys(datefrom);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='branches_recentdate']")
	private WebElement branchesRecentDate;

	public void enterRecentDate(String recentdate) {
		branchesRecentDate.sendKeys(recentdate);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//select[@name='employee_department']")
	private WebElement department;

	public void selectdepartment(String text) {
		selectbyVisibleText(driver, department, text);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//select[@name='employee_branches']")
	private WebElement branches;

	public void selectBranches(String text) {
		selectbyVisibleText(driver, branches, text);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_position']")
	private WebElement position;

	public void enterPosition(String pos) {
		position.sendKeys(pos);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_contact']")
	private WebElement contact;

	public void enterContactNum(String contactnum) {
		contact.sendKeys(contactnum);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_sss']")
	private WebElement sss;

	public void entersssNum(String sssnum) {
		sss.sendKeys(sssnum);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_tin']")
	private WebElement tin;

	public void enterTinNum(String tinnum) {
		tin.sendKeys(tinnum);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_hdmf_pagibig']")
	private WebElement hdmf;

	public void enterHdmfNum(String hdmfnum) {
		hdmf.sendKeys(hdmfnum);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_gsis']")
	private WebElement gsis;

	public void entergsisNum(String gsisnum) {
		gsis.sendKeys(gsisnum);
	}

	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_file201']")
	private WebElement File201;


	@FindBy(xpath = "//h4[text()='Add Employee']/../..//input[@name='employee_image']")
	private WebElement empPic;
	
	@FindBy(xpath = "//button[@name='emplo']")
	private WebElement saveEmployeeFormButton;
	
	@FindBy(css = "tbody tr td:nth-child(3)")
	private List<WebElement> employeeNames;

	@FindBy(xpath = "//b[text()='Welcome!,']")
	private WebElement profileLink;
	
	@FindBy(xpath = "//a[normalize-space()='Log Out']")
	private WebElement logout;
	
	@FindBy(xpath = "//h4[text()='Delete Employee']/../..//button[text()='Delete']")
	private WebElement delete;
	
//	 ========================================================
	
	public boolean validateBoardTitle(String expectedBoardTitle) {
		String actualTitle = getTagtexOfElement(driver, dashBoardTitle);
		if(actualTitle.equals(expectedBoardTitle)) {
			return true;
		}
		return false;
	}
	
	public void getAddEmployeeForm() {
		clickOnVisibleElement(driver, addEmployeeButton);
	}
	
	public boolean validateTheAddAdminPopupTitle(String expectedAddAdminFormTitle) {
		String PopupTitle = getTagtexOfElement(driver,addEmployeeFormPopupTitle);
		if(PopupTitle.equals(expectedAddAdminFormTitle)) {
			return true;
		}
		return false;
	}
	
	public void fillEmployeeForm(HashMap<String,String> map, String uploadImagePath, String uploadFilePath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		for (Entry<String, String> set : map.entrySet()) {
			if (set.getKey().equals("employee_department")) {
				WebElement dept_dropdown = driver.findElement(
						By.xpath("//h4[text()='Add Employee']/../..//select[@name='" + set.getKey() + "']"));
				Select department_DD = new Select(dept_dropdown);
				department_DD.selectByVisibleText(set.getValue());
			} else if (set.getKey().equals("employee_branches")) {
				WebElement branch_dropdown1 = driver.findElement(
						By.xpath("//h4[text()='Add Employee']/../..//select[@name='" + set.getKey() + "']"));
				Select branch_DD = new Select(branch_dropdown1);
				branch_DD.selectByVisibleText(set.getValue());
			} else {
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(
						By.xpath("//h4[text()='Add Employee']/../..//input[@name='" + set.getKey() + "']"))))
						.sendKeys(set.getValue());
			}
		}
		empPic.sendKeys(uploadImagePath);
		File201.sendKeys(uploadFilePath);
		
	}
	
	public void saveEmployeeForm() {
		clickOnVisibleElement(driver, saveEmployeeFormButton);
	}
	
	public boolean validateInsertAdminPopupAndAccept(String insertAlertText) {
		Alert insertAlert = explicitWaitForAlert(driver);
		if(insertAlert.getText().equals(insertAlertText)) {
			driver.switchTo().alert().accept();
			return true;
		}
		return false;
	}
	
	public boolean validateAddedEmployee(String createdAdmin) {
		boolean flag = false;
		for (WebElement empName : employeeNames) {
			if (empName.getText().equals(createdAdmin)) {// createdEmpName
				flag = true;
				break;
			}
		}
		return flag;
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
	
	public void deleteEmployeeAsHrAssistant(String empName) {
		clickOnVisibleElement(driver, driver.findElement(By.xpath("//td[text()='"+empName+"']/../td[9]/i[3]")));
		clickOnVisibleElement(driver, delete);
	}
	
	public boolean validateDeletePopupAndAccept(String deleteAlertText) {
		Alert deleteAlert = explicitWaitForAlert(driver);
		if(deleteAlert.getText().equals(deleteAlertText)) {
			driver.switchTo().alert().accept();
			return true;
		}
		return false;
	}

}
