package admin_Module;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GenericUtility.ExcelUtility;
import com.GenericUtility.FileUtility;
import com.GenericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

//User should be able to create head
public class MDD_ADMIN_SM_TC_1 {

	static WebDriver driver = null;

	static WebDriverWait wait;

	public static void main(String[] args) throws IOException {
		FileUtility fLib = new FileUtility();
		ExcelUtility xLib = new ExcelUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		
		//get the propery file data
		String browserName = fLib.getProperyFileData("browser");
		String url =fLib.getProperyFileData("url");
		String userName = fLib.getProperyFileData("headusername");
		String password =fLib.getProperyFileData("headpassword");
		String selecttype =fLib.getProperyFileData("headselect_type");
		
		//get Excel sheet data
		
		HashMap<String, String> map = xLib.getMultipleDataFromExcel("admindata");

		//Launching the browser
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		//pre-conditions
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		wait=new WebDriverWait(driver, 5);
		//Triggering the test url
		driver.get(url);
		
		//Validating the login page
		String expectedLoginPageTitle = xLib.getExpectedDataFromExcel("expdata", "expectedLoginPageTitle");
//		String actualLoginPageTitle = driver.getTitle();
		if (wLib.explicitWaitForTitleContains(driver, expectedLoginPageTitle)) {
			System.out.println("Login Page is Displayed and varified");
		}else {
			System.err.println("Login page is not displayed and varified");
		}
		
		//login to the application
		WebElement usernameTF=driver.findElement(By.name("hr_email"));//sendKeys(userName);
		wLib.sendkeysOnVisibleTextField(driver, usernameTF, userName);	
		WebElement passwordTF = driver.findElement(By.name("hr_password"));//.sendKeys(password);
		wLib.sendkeysOnVisibleTextField(driver, passwordTF, password);
		WebElement hrTypeDD = driver.findElement(By.id("hr_type"));
		wLib.selectbyValue(hrTypeDD, selecttype);
		
//		Select select = new Select(driver.findElement(By.id("hr_type")));
//		select.selectByValue(selecttype);
		driver.findElement(By.name("login_hr")).click();
		
		//validating the popup
		String expectedLoginPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLoginPopuptext");//Insert Successfully!!!
		String actualLoginPopuptext = driver.switchTo().alert().getText();
		if (actualLoginPopuptext.equals(expectedLoginPopuptext)) {
			System.out.println("Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Popup is not displayed and varified upon popup text");
		}
		
		driver.switchTo().alert().accept();
		
		//validating the Homepage/Dashboard
		String expectedHomeTabName = xLib.getExpectedDataFromExcel("expdata", "expectedHomeTabName");
		String actualTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
		
		if (actualTabName.equals(expectedHomeTabName)) {
			System.out.println("Dashboard is displayed and varified on board name");
		}else {
			System.err.println("Dashboard is not displayed and varified on board name");
		}
		
		//creating the admin credentials
		driver.findElement(By.xpath("//p[normalize-space()='ADMIN']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Add Admin']")).click();
		WebElement addAdminButton = driver.findElement(By.xpath("//button[normalize-space()='Add Admin']"));
		addAdminButton.click();
		
		String expectedAdminTabName = xLib.getExpectedDataFromExcel("expdata", "expectedAdminTabName");
		String actualAdminTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
		
		if (actualAdminTabName.equals(expectedAdminTabName)) {
			System.out.println("Admin page is displayed and varified on board name");
		}else {
			System.err.println("Admin page is not displayed and varified on board name");
		}
		
		
		String expectedAddAdminPopupTitle=xLib.getExpectedDataFromExcel("expdata", "expectedAddAdminPopupTitle");
		String actualAddAdminPopupTitle =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Add Admin']"))).getText();
		
		if(actualAddAdminPopupTitle.equals(expectedAddAdminPopupTitle)) {
			System.out.println("Add Admin popup is displayed and varified upon title");
		}else {
			System.err.println("Add Admin popup is not displayed and varified upon title");
		}
		
		//fill the form for admin
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
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_companyid']")))).sendKeys("123");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_firstname']")).sendKeys("QSP");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_lastname']")).sendKeys("Basavangudi");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_middlename']")).sendKeys("Head Branch");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_contactno']")).sendKeys("78912354661");
//		WebElement selectType_dropdown = driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//select[@name='hr_type']"));
//		Select selectType_DD = new Select(selectType_dropdown);
//		selectType_DD.selectByVisibleText("→ HR Head");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_email']")).sendKeys("qsphead@gmail.com");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_password']")).sendKeys("qsphead@123");
		
		for (Entry<String, String> set : map.entrySet()) {
			
			if(set.getKey().equals("hr_type")) {
				WebElement selectType_dropdown = driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//select[@name='"+set.getKey()+"']"));
				Select selectType_DD = new Select(selectType_dropdown);
				selectType_DD.selectByVisibleText(set.getValue());	
			}else {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='"+set.getKey()+"']")))).sendKeys(set.getValue());
			}
		}
		
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//popup for sucessfully
		String expectedAdminInsertPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedAdminInsertPopuptext");//Insert Successfully!!!
		String actualAdminInsertPopuptext = driver.switchTo().alert().getText();
		if (actualAdminInsertPopuptext.equals(expectedAdminInsertPopuptext)) {
			System.out.println("Insert Successfully!!! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Insert Successfully!!! Popup is not displayed and varified upon popup text");
		}
		
		//validating the admin in adminpage
		driver.switchTo().alert().accept();
		boolean flag = false;
		String expAdminName = xLib.getExpectedDataFromExcel("expdata", "adminName");
		List<WebElement> EmployeeNames = driver.findElements(By.cssSelector("tbody tr td:nth-child(2)"));
		for (WebElement empName : EmployeeNames) {
			if(empName.getText().equals(expAdminName)) {
				flag=true;
				System.out.println("The Admin is sucessfully created");
				break;
			}
		}
		if(!flag) {
			System.out.println("The Admin is not created");
		}
		
		//Logout of application
		driver.findElement(By.xpath("//b[text()='Welcome!,']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Log Out']")).click();
		String expectedLogoutPopuptext = xLib.getExpectedDataFromExcel("expdata", "expectedLogoutPopuptext");//Successfully Logout!
		String actualLogoutPopuptext = driver.switchTo().alert().getText();
		if (actualLogoutPopuptext.equals(expectedLogoutPopuptext)) {
			System.out.println("Successfully Logout! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Successfully Logout! Popup is not displayed and varified upon popup text");
		}
		driver.switchTo().alert().accept();

		// terminate the browser
		driver.quit();

	}
}
