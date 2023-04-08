package admin_Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HRM_ST_TC_05 {

	public static void main(String[] args) throws IOException {
		
		//get the propery file data
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String url = pObj.getProperty("url");
		/*
		 * headusername hrhead@gmail.com
		 * headpassword hrhead@123
		 * headselect_type HR Head
		 */
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
		
		//Launching browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		driver.get(url);
		String actualTitle = driver.getTitle();
		String ExpTitle="Admin Log in";
		if (actualTitle.equals(ExpTitle)) {
			System.out.println("Login page is displayed and verified upon the title");
		} else {
			System.out.println("Login page is not displayed and verified upon the title");

        }
		
		//Login to aplication as a Head
		driver.findElement(By.name("hr_email")).sendKeys(userName);
		driver.findElement(By.name("hr_password")).sendKeys(password);
		WebElement selectTypeDD = driver.findElement(By.id("hr_type"));
		Select select= new Select(selectTypeDD);
		select.selectByValue(selecttype);
		driver.findElement(By.id("remember")).click();
		driver.findElement(By.name("login_hr")).click();
		//Validating pop-up
		String loginSuccessfullyAlert = driver.switchTo().alert().getText();
		System.out.println(loginSuccessfullyAlert);
		String exploginSuccesfullyAlert="Login Successfully!!";
		if (loginSuccessfullyAlert.equals(exploginSuccesfullyAlert)) {
			System.out.println("Login Successfully!! popup is displayed and verified upon its title");
		} else {
			System.out.println("Login Successfully!! popup is not displayed and verified upon its title");
		}
		driver.switchTo().alert().accept();
		//Validating Home page
		String actualDashboardTitle = driver.getTitle();
		String expDashboardTitle="Admin | Dashboard";
		if (actualDashboardTitle.equals(expDashboardTitle)) {
			System.out.println("Dashboard page is displayed and verified");
		} else {
			System.out.println("Dashboard page is not displayed and verified");
		}
		
		//Adding Corporate
		driver.findElement(By.xpath("//p[contains(text(),'CORPORATE')]")).click();
		driver.findElement(By.xpath("//ul[@class='nav nav-treeview']//p[text()='Add Corporate']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Add Corporate']")).click();
		WebElement addCorporatePopup = driver.findElement(By.xpath("//div[@class='modal-header']/h4[text()='Add Corporate']"));
		String actualaddCorporatePopup = addCorporatePopup.getText();
		System.out.println(actualaddCorporatePopup);
		
		driver.findElement(By.xpath("//input[@placeholder='Corporate Name']")).sendKeys("testing");
		driver.findElement(By.name("corp")).click();
		String actualinsertsuccessPopup = driver.switchTo().alert().getText();
		System.out.println(actualinsertsuccessPopup);
		String ExpinsertsuccessPopup="Insert Successfully!!!";
		if (actualinsertsuccessPopup.equals(ExpinsertsuccessPopup)) {
			System.out.println("Insert Successfully!!! popup is displayed and verified");
		} else {
			System.out.println("Insert Successfully!!! popup is not displayed and verified");

        }
		driver.switchTo().alert().accept();
		//Validating Department created
		String deptCreated = "testing";
		String ActualDeptNameCreated = driver.findElement(By.xpath("//tbody/tr/td[text()='"+deptCreated+"']")).getText();
		String Expdeptname="testing";
		if (ActualDeptNameCreated.equals(Expdeptname)) {
			System.out.println("department is created successfully");
		} else {
			System.out.println("department is not created and verified");
		}
		
		//Creating The branches
		driver.findElement(By.xpath("//li[@class='nav-item has-treeview']//p[normalize-space()='BRANCHES']")).click();
		driver.findElement(By.xpath("//li[@class='nav-item']//p[normalize-space()='Add Braches']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Add Branches']")).click();
		String actualBranchPopup = driver.findElement(By.xpath("//div[@class='modal-header']/h4[text()='Add Branches']")).getText();
		System.out.println(actualBranchPopup);
		String ExpBranchPopup="Add Branches";
		if (actualBranchPopup.equals(ExpBranchPopup)) {
			System.out.println("branch popup is displayed successfully and verified");
		} else {
			System.out.println("branch popup is not displayed and verified");
		}
		driver.findElement(By.xpath("//input[@placeholder='Branches Name']")).sendKeys("basavangudi");
		driver.findElement(By.name("bran")).click();
		String actualinsertBranchPopup= driver.switchTo().alert().getText();
		System.out.println(actualinsertBranchPopup);
		String expinsertBranchPopup="Insert Successfully!!!";
		if (actualinsertBranchPopup.equals(expinsertBranchPopup)) {
			System.out.println("Insert Successfully!!! popup is displayed and verified");
		} else {
			System.out.println("Insert Successfully!!! popup is not displayed and verified");
		}
		driver.switchTo().alert().accept();
		//validating the branch
		String verifybranchcreated="basavangudi";
		String actualBranchNameCreated = driver.findElement(By.xpath("//tbody/tr//td[text()='"+verifybranchcreated+"']")).getText();
		String expBranchNameCreated="basavangudi";
		if (actualBranchNameCreated.equals(expBranchNameCreated)) {
			System.out.println("Branch is created successfully");
		} else {
			System.out.println("Branch is not created");
		}
		
		//create Employee
		driver.findElement(By.xpath("//li[@class='nav-item has-treeview']//p[normalize-space()='EMPLOYEE']")).click();
		driver.findElement(By.xpath("//ul[@class='nav nav-treeview']//p[normalize-space()='Add Employee']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Add Employee')]")).click();
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_companyid']")).sendKeys("1234");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_firstname']")).sendKeys("Sindhu");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_lastname']")).sendKeys("K");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_middlename']")).sendKeys("Dhivakar");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='branches_datefrom']")).sendKeys("03/08/2023");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='branches_recentdate']")).sendKeys("03/23/2023");
//		WebElement selectDept = driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='employee_department']"));
//		Select sel= new Select(selectDept);
//		sel.selectByValue("department");
//		WebElement selectBranches = driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='employee_branches']"));
//		Select select1=new Select(selectBranches);
//		select1.selectByValue("basavangudi");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_position']")).sendKeys("Automation tester");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_contact']")).sendKeys("98765432109");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_sss']")).sendKeys("123");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_tin']")).sendKeys("567");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_hdmf_pagibig']")).sendKeys("345");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_gsis']")).sendKeys("987");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_file201']")).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\SQL_DDl_DML_TCL_DCL.pdf");
//		driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_image']")).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\Login_Page.JPG");
		
		
		//using the map data to fill the form
		for(Entry<String, String> set:map.entrySet()) {
			if(set.getKey().equals("employee_department")) {
				WebElement dept_dropdown = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='"+set.getKey()+"']"))));
				Select department_DD = new Select(dept_dropdown);
				department_DD.selectByVisibleText(set.getValue());
			}else if (set.getKey().equals("employee_branches")) {
				WebElement branch_dropdown1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//select[@name='"+set.getKey()+"']"))));
				Select branch_DD = new Select(branch_dropdown1);
				branch_DD.selectByVisibleText(set.getValue());
			}else {
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='"+set.getKey()+"']")))).sendKeys(set.getValue());
			}
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_file201']")))).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\SQL_DDl_DML_TCL_DCL.pdf");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Employee']/../..//input[@name='employee_image']")))).sendKeys("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\Login_Page.JPG");
		
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//validating insert pop-up
		String actualempCreatedPopup = driver.switchTo().alert().getText();
		String expempCreatedPopup="Insert Successfully!!!";
		if (actualempCreatedPopup.equals(expempCreatedPopup)) {
			System.out.println("Insert Successfully!!! popup is displayed successfully");
		} else {
			System.out.println("Insert Successfully!!! popup is not displayed");
		}
		driver.switchTo().alert().accept();
		
		//validated the created employee
		List<WebElement> allEmpNames = driver.findElements(By.xpath("//tbody/tr/td[3]"));
		Boolean flag=false;
		
		for (WebElement names : allEmpNames) {
			if(names.getText().equals("Sindhu"))
			{
				
				System.out.println("Employee detail is created");
				flag=true;
				break;
			}
			if (!flag) {
				System.out.println("Employee detail is not created");
			}
		}
		
		//Log-out of application
		driver.findElement(By.xpath("//li[@class='nav-item dropdown']//b[text()='Welcome!,']")).click();
		driver.findElement(By.xpath("//a[@href='log_out.php']")).click();
		String actualLogoutPopup = driver.switchTo().alert().getText();
		String expLogoutPopup="Successfully Logout!";
		if (actualLogoutPopup.equals(expLogoutPopup)) {
			System.out.println("Successfully Logout! popup is displayed successfully");
		} else {
			System.out.println("Successfully Logout! popup is not displayed ");

		}
		driver.switchTo().alert().accept();
		//Login as a Assistant
		String actualloginpagetitle = driver.getTitle();
		String exploginpagetitle="Admin Log in";
		if (actualloginpagetitle.equals(exploginpagetitle)) {
			System.out.println("Login page is displayed and verified upon the title");
		} else {
			System.out.println("Login page is not displayed and verified upon the title");

        }
		driver.findElement(By.name("hr_email")).sendKeys("hrassistant@gmail.com");
		driver.findElement(By.name("hr_password")).sendKeys("hrassistant@123");
		WebElement selectType = driver.findElement(By.id("hr_type"));
		Select select11= new Select(selectType);
		select11.selectByValue("HR Assistant");
		driver.findElement(By.id("remember")).click();
		driver.findElement(By.name("login_hr")).click();
		String loginSuccessfulyAlert = driver.switchTo().alert().getText();
		System.out.println(loginSuccessfulyAlert);
		String exploginSuccesfulyAlert="Login Successfully!!";
		if (loginSuccessfulyAlert.equals(exploginSuccesfulyAlert)) {
			System.out.println("Login Successfully!! popup is displayed and verified upon its title");
		} else {
			System.out.println("Login Successfully!! popup is not displayed and verified upon its title");

		}
		driver.switchTo().alert().accept();
		//validating dashboard
		String actualDashboardTitl = driver.getTitle();
		String expDashboardTitl="Admin | Dashboard";
		if (actualDashboardTitl.equals(expDashboardTitl)) {
			System.out.println("Dashboard page is displayed and verified");
		} else {
			System.out.println("Dashboard page is not displayed and verified");
		}
		//deleted employee
		driver.findElement(By.xpath("//li[@class='nav-item has-treeview']")).click();
		driver.findElement(By.xpath("//li[@class='nav-item']/a[@href='Add_employee.php']")).click();
		String name="Tony Stark";
		driver.findElement(By.xpath("//td[text()='"+name+"']/../td[9]/i[3]")).click();
		driver.findElement(By.xpath("//h4[text()='Delete Employee']/../..//button[text()='Delete']")).click();
		String delpopup = driver.switchTo().alert().getText();
		String expdelpopup="Delete Successfully!!!";
		if (delpopup.equals(expdelpopup)) {
			System.out.println("Delete Successfully!!! popup is displayed");
		} else {
			System.out.println("Delete Successfully!!! popup is not displayed");
		}
		driver.switchTo().alert().accept();
		
		//Log out as a assistant
		driver.findElement(By.xpath("//li[@class='nav-item dropdown']//b[text()='Welcome!,']")).click();
		driver.findElement(By.xpath("//a[@href='log_out.php']")).click();
		String actLogoutPopup = driver.switchTo().alert().getText();
		String exploutPopup="Successfully Logout!";
		if (actLogoutPopup.equals(exploutPopup)) {
			System.out.println("Successfully Logout! popup is displayed successfully");
		} else {
			System.out.println("Successfully Logout! popup is not displayed ");

		}
		driver.switchTo().alert().accept();
		String actualoginpagetitle = driver.getTitle();
		String exploginpagetitl="Admin Log in";
		if (actualoginpagetitle.equals(exploginpagetitl)) {
			System.out.println("Login page is displayed and verified upon the title");
		} else {
			System.out.println("Login page is not displayed and verified upon the title");

        }
		driver.quit();
		

	}

}