package com.POMRepositaries;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name = "hr_email")
	private WebElement hr_EmailTextField;
	
	@FindBy(name = "hr_password")
	private WebElement hr_PasswordTextField;
	
	
	//private By selectTypeDropDown = By.id("hr_type");
	//go with value 
	//"HR Head"
	//"HR Officer"
	//"HR Officer"
	@FindBy(id = "hr_type")
	private WebElement selectTypeDropDown;
	
	@FindBy(id = "remember")
	private WebElement rememberMeCheckBox;
	
	@FindBy(className = "icheck-primary")
	private WebElement rememberMeText;
	
	public String checkboxName() {
		return rememberMeText.getText();
	}
	
	@FindBy(name = "login_hr")
	private WebElement signInButton;
	
	public Boolean validateLoginPageTitle(String expectedTitle) {
		return explicitWaitForTitleContains(driver, expectedTitle);
	}
	
	public void signInToApplication(String username, String password, String position) {
		sendkeysOnVisibleTextField(driver, hr_EmailTextField, username);
		sendkeysOnVisibleTextField(driver, hr_PasswordTextField, password);
		selectbyValue(selectTypeDropDown, position);
		if (rememberMeCheckBox.isSelected()) {

		} else {
			rememberMeCheckBox.click();
		}
		clickOnVisibleElement(driver, signInButton);
	}
	
	
	
	
	public boolean validateLoginPopup(String loginAlertText) {
		Alert loginAlert = explicitWaitForAlert(driver);
		if(loginAlert.getText().equals(loginAlertText)) {
			return true;
		}
		return false;
	}
	
	public DashBoardPage acceptLoginPopup() {
		driver.switchTo().alert().accept();
		return new DashBoardPage(driver);
	}
	
	// afetr submitting the details you will get one js popup

}
