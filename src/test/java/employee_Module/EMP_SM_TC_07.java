package employee_Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

//Create Admin
public class EMP_SM_TC_07 {
	
	static WebDriver driver =null;
	
	static WebDriverWait wait;
	public static void main(String[] args) throws IOException {
		
		//get the propery file data
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String browserName = pObj.getProperty("browser");
		String url = pObj.getProperty("url");
		String userName = pObj.getProperty("headusername");
		String password =pObj.getProperty("headpassword");
		String selecttype =pObj.getProperty("headselect_type");
		
		//get Excel sheet data
		FileInputStream fs = new FileInputStream(".\\src\\test\\resources\\TestSepecificdata.xlsx");
		Workbook workbook = WorkbookFactory.create(fs);
		Sheet sheet = workbook.getSheet("empdata");
		HashMap <String,String> map = new HashMap <String,String>();
		for(int i = 0;i<=sheet.getLastRowNum();i++) {
			String key = sheet.getRow(i).getCell(0).getStringCellValue();
			String value = sheet.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}
		
		//Launching the browser
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if (browserName.equals("firefox")) {
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
		String expectedTitle = "Admin Log in";
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Login Page is Displayed and varified");
		}else {
			System.err.println("Login page is not displayed and varified");
		}
		
		//login to the application
		driver.findElement(By.name("hr_email")).sendKeys(userName);
		driver.findElement(By.name("hr_password")).sendKeys(password);
		Select select = new Select(driver.findElement(By.id("hr_type")));
		select.selectByValue(selecttype);
		driver.findElement(By.name("login_hr")).click();
		
		//validating the popup
		String expectedPopuptext = "Login Successfully!!";//Insert Successfully!!!
		String actualPopuptext = driver.switchTo().alert().getText();
		if (actualPopuptext.equals(expectedPopuptext)) {
			System.out.println("Login Successfully!! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Login Successfully!! Popup is not displayed and varified upon popup text");
		}
		
		driver.switchTo().alert().accept();
		
		//validating the Homepage/Dashboard
		String expectedTabName = "Dashboard";
		String actualTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
		
		if (actualTabName.equals(expectedTabName)) {
			System.out.println("Dashboard is displayed and varified on board name");
		}else {
			System.err.println("Dashboard is not displayed and varified on board name");
		}
		
		//creating corporate and validating the page
		driver.findElement(By.xpath("//p[normalize-space()='CORPORATE']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Add Corporate']")).click();
		WebElement addCorporateButton = driver.findElement(By.xpath("//button[normalize-space()='Add Corporate']"));
		addCorporateButton.click();
		
		String expectedCorporateTabName = "Dashboard";
		String actualCorporateTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
		
		if (expectedCorporateTabName.equals(actualCorporateTabName)) {
			System.out.println("Corporate page is displayed and varified on board name");
		}else {
			System.err.println("Corporate page is not displayed and varified on board name");
		}
		
		
		String expectedAddCorporatePopupTitle="Add Corporate";
		String actualAddCorporatePopupTitle =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Add Corporate']"))).getText();
		
		if(actualAddCorporatePopupTitle.equals(expectedAddCorporatePopupTitle)) {
			System.out.println("Add Corporate popup is displayed and varified upon title");
		}else {
			System.err.println("Add Corporate popup is not displayed and varified upon title");
		}
		
		driver.findElement(By.name("corporate_name")).sendKeys("testing");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//validating the popup
		String expectedInsertPopuptext = "Insert Successfully!!!";//Insert Successfully!!!
		String actualInsertPopuptext = driver.switchTo().alert().getText();
		if (actualInsertPopuptext.equals(expectedInsertPopuptext)) {
			System.out.println("Insert Successfully!!! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Insert Successfully!!! Popup is not displayed and varified upon popup text");
		}
		driver.switchTo().alert().accept();
		boolean flag = false;
		List<WebElement> corporateNames = driver.findElements(By.cssSelector("tbody tr td:nth-child(2)"));
		for (WebElement corpName : corporateNames) {
			if(corpName.getText().equals("testing")) {
				flag=true;
				System.out.println("The Corporation is sucessfully created");
				break;
			}
		}
		if(!flag) {
			System.out.println("The Corporation is not created");
		}
		
		//creating Branches and validating the page
		driver.findElement(By.xpath("//p[normalize-space()='BRANCHES']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Add Braches']")).click();
		WebElement addBranchesButton = driver.findElement(By.xpath("//button[normalize-space()='Add Branches']"));
		addBranchesButton.click();
		
		String expectedBranchTabName = "Dashboard";
		String actualBranchTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
		
		if (actualBranchTabName.equals(expectedBranchTabName)) {
			System.out.println("Branches page is displayed and varified on board name");
		}else {
			System.err.println("Branches page is not displayed and varified on board name");
		}
		
		String expectedAddBranchesPopupTitle="Add Branches";
		String actualAddBranchesPopupTitle= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Add Branches']"))).getText();
		
		if(actualAddBranchesPopupTitle.equals(expectedAddBranchesPopupTitle)) {
			System.out.println("Add Braches popup is displayed and varified upon title");
		}else {
			System.err.println("Add Braches popup is not displayed and varified upon title");
		}
		
		driver.findElement(By.name("branches_name")).sendKeys("basavangudi");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//validating the popup
		String expectedBranchInsertPopuptext = "Insert Successfully!!!";//Insert Successfully!!!
		String actualBranchInsertPopuptext = driver.switchTo().alert().getText();
		if (actualBranchInsertPopuptext.equals(expectedBranchInsertPopuptext)) {
			System.out.println("Insert Successfully!!! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Insert Successfully!!! Popup is not displayed and varified upon popup text");
		}
		driver.switchTo().alert().accept();
		flag = false;
		List<WebElement> branchesNames = driver.findElements(By.cssSelector("tbody tr td:nth-child(2)"));
		for (WebElement branch : branchesNames) {
			if(branch.getText().equals("basavangudi")) {
				flag=true;
				System.out.println("The Branch is sucessfully created");
				break;
			}
		}
		if(!flag) {
			System.out.println("The Branch is not created");
		}
		
		
		//creating employee and validating
		driver.findElement(By.xpath("//p[normalize-space()='EMPLOYEE']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Add Employee']")).click();
		WebElement addEmployeeButton = driver.findElement(By.xpath("//button[normalize-space()='Add Employee']"));
		addEmployeeButton.click();
		
		String expectedEmployeeTabName = "Dashboard";
		String actualEmployeeTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
		
		if (actualEmployeeTabName.equals(expectedEmployeeTabName)) {
			System.out.println("Employee page is displayed and varified on board name");
		}else {
			System.err.println("Employee page is not displayed and varified on board name");
		}
		
		
		String expectedAddAdminPopupTitle="Add Employee";
		String actualAddAdminPopupTitle= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Add Employee']"))).getText();
		
		if(actualAddAdminPopupTitle.equals(expectedAddAdminPopupTitle)) {
			System.out.println("Add Employee popup is displayed and varified upon title");
		}else {
			System.err.println("Add Employee popup is not displayed and varified upon title");
		}
		
//		//filling the form for employee
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_companyid']")))).sendKeys("1234");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_firstname']")).sendKeys("Tony Stark");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_lastname']")).sendKeys("Ironman");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_middlename']")).sendKeys("Avenger");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='branches_datefrom']")).sendKeys("17-06-2021");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='branches_recentdate']")).sendKeys("23-08-2023");
//		WebElement dept_dropdown = driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='employee_department']"));
//		Select department_DD = new Select(dept_dropdown);
//		department_DD.selectByVisibleText("testing");
//		WebElement branch_dropdown1 = driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='employee_branches']"));
//		Select branch_DD = new Select(branch_dropdown1);
//		branch_DD.selectByVisibleText("basavangudi");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_position']")).sendKeys("Captain");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_contact']")).sendKeys("78912354661");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_sss']")).sendKeys("1236");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_tin']")).sendKeys("12345");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_hdmf_pagibig']")).sendKeys("2135");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_gsis']")).sendKeys("25896");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_file201']")).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\SQL_DDl_DML_TCL_DCL.pdf");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_image']")).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\Login_Page.JPG");
		
		//using the map data to fill the form
		for(Entry<String, String> set:map.entrySet()) {
			if(set.getKey().equals("employee_department")) {
				WebElement dept_dropdown = driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='"+set.getKey()+"']"));
				Select department_DD = new Select(dept_dropdown);
				department_DD.selectByVisibleText(set.getValue());
			}else if (set.getKey().equals("employee_branches")) {
				WebElement branch_dropdown1 = driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='"+set.getKey()+"']"));
				Select branch_DD = new Select(branch_dropdown1);
				branch_DD.selectByVisibleText(set.getValue());
			}else {
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='"+set.getKey()+"']")))).sendKeys(set.getValue());
			}
		}
		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_file201']")).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\SQL_DDl_DML_TCL_DCL.pdf");
		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_image']")).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\Login_Page.JPG");
		
		driver.findElement(By.xpath("//button[@name='emplo']")).click();
		
		String expectedEmployeeInsertPopuptext = "Insert Successfully!!!";//Insert Successfully!!!
		String actualEmployeeInsertPopuptext = driver.switchTo().alert().getText();
		if (actualEmployeeInsertPopuptext.equals(expectedEmployeeInsertPopuptext)) {
			System.out.println("Insert Successfully!!! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Insert Successfully!!! Popup is not displayed and varified upon popup text");
		}
		
		driver.switchTo().alert().accept();
		flag = false;
		List<WebElement> EmployeeNames = driver.findElements(By.cssSelector("tbody tr td:nth-child(3)"));
		for (WebElement empName : EmployeeNames) {
			if(empName.getText().equals("Tony Stark")) {
				flag=true;
				System.out.println("The Employee is sucessfully created");
				break;
			}
		}
		if(!flag) {
			System.out.println("The Employee is not created");
		}
		
		//Logout of application
		driver.findElement(By.xpath("//b[text()='Welcome!,']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Log Out']")).click();
		String expectedLogoutPopuptext = "Successfully Logout!";//Successfully Logout!
		String actualLogoutPopuptext = driver.switchTo().alert().getText();
		if (actualLogoutPopuptext.equals(expectedLogoutPopuptext)) {
			System.out.println("Successfully Logout! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Successfully Logout! Popup is not displayed and varified upon popup text");
		}
		driver.switchTo().alert().accept();
		
		String actualAftreLogoutTitle = driver.getTitle();
		if (actualAftreLogoutTitle.equals(expectedTitle)) {
			System.out.println("Login Page is Displayed and varified");
		}else {
			System.err.println("Login page is not displayed and varified");
		}
		
	}
}
