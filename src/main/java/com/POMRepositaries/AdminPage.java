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

public class AdminPage extends WebDriverUtility {
	
	WebDriver driver;
	public AdminPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//button[normalize-space()='Add Admin']")
	private WebElement addAdminPopupButton;
	
	@FindBy(xpath = "//div[@class='row mb-2']//h1")
	private WebElement dashBoardTitle;
	
	@FindBy(xpath = "//h4[text()='Add Admin']")
	private WebElement addAdminFormPopupTitle;
	
	@FindBy(xpath = "//h4[text()='Edit Admin']")
	private WebElement editAdminFormPopupTitle;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveAdminFormButton;
	
	@FindBy(css = "tbody tr td:nth-child(2)")
	private List<WebElement> adminNames;
	
	@FindBy(xpath = "//b[text()='Welcome!,']")
	private WebElement profileLink;
	
	@FindBy(xpath = "//a[normalize-space()='Log Out']")
	private WebElement logout;
	
	@FindBy(xpath = "//h4[text()='Edit Admin']/../..//input[@name='hr_email']")
	private WebElement editEmailTF;
	
	@FindBy(xpath = "//h4[text()='Edit Admin']/../..//input[@name='hr_password']")
	private WebElement editPassword;
	
	@FindBy(css = "input[type='search']")
	private WebElement searchTF;
	
	
	
	public boolean validateBoardTitle(String expectedBoardTitle) {
		String actualTitle = getTagtexOfElement(driver, dashBoardTitle);
		if(actualTitle.equals(expectedBoardTitle)) {
			return true;
		}
		return false;
	}
	
	public void getAddAdminFormPopup() {
		clickOnVisibleElement(driver, addAdminPopupButton);
	}
	
	public boolean validateTheAddAdminPopupTitle(String expectedAdminFormTitle) {
		String PopupTitle = getTagtexOfElement(driver, addAdminFormPopupTitle);
		if(PopupTitle.equals(expectedAdminFormTitle)) {
			return true;
		}
		return false;
	}
	
	public void fillAdminForm(HashMap<String,String> map) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		for (Entry<String, String> set : map.entrySet()) {
			
			if(set.getKey().equals("hr_type")) {
				WebElement selectType_dropdown = driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//select[@name='"+set.getKey()+"']"));
				Select selectType_DD = new Select(selectType_dropdown);
				selectType_DD.selectByVisibleText(set.getValue());	
			}else {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='"+set.getKey()+"']")))).sendKeys(set.getValue());
			}
		}
	}
	
	public void saveAdminForm() {
		clickOnVisibleElement(driver, saveAdminFormButton);
	}
	
	public boolean validateInsertAdminPopupAndAccept(String insertAlertText) {
		Alert insertAlert = explicitWaitForAlert(driver);
		if(insertAlert.getText().equals(insertAlertText)) {
			driver.switchTo().alert().accept();
			return true;
		}
		return false;
	}
	
	public boolean validateAddedAdminWithFirstName(String expAdminName) {
		boolean flag = false;
		for (WebElement adminName : adminNames) {
			if (adminName.getText().equals(expAdminName)) {
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
	
	public void getEditAdminPopup(String adminName) {
		WebElement editButton = driver.findElement(By.xpath("//td[text()='"+adminName+"']/..//i[1]"));
		clickOnVisibleElement(driver, editButton);
	}
	
	public boolean validateTheEditAdminPopupTitle(String expectedAdminFormTitle) {
		String PopupTitle = getTagtexOfElement(driver, editAdminFormPopupTitle);
		if(PopupTitle.equals(expectedAdminFormTitle)) {
			return true;
		}
		return false;
	}
	
	public void editEmailAndPassword(String adminName,String createdEditedUsername, String createdEditedUserPassword) {
		sendkeysOnVisibleTextField(driver, editEmailTF, createdEditedUsername);
		sendkeysOnVisibleTextField(driver, editPassword, createdEditedUserPassword);
		WebElement save = driver.findElement(By.xpath("//input[@value='"+adminName+"']/../../../../..//button[text()='Save']"));
		clickOnVisibleElement(driver, save);
	}
	
	public boolean validateUpdateAdminPopupAndAccept(String updateAlertText) {
		Alert updateAlert = explicitWaitForAlert(driver);
		if(updateAlert.getText().equals(updateAlertText)) {
			driver.switchTo().alert().accept();
			return true;
		}
		return false;
	}
	
	public void searchAdmin(String adminName) {
		sendkeysOnVisibleTextField(driver, searchTF, adminName);
		
	}
}
