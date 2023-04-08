package admin_Module;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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


//User should be able to search the admin in admin page
public class HRM_ST_TC_42 {

static WebDriver driver =null;
	
	static WebDriverWait wait;
	public static void main(String[] args) throws AWTException, IOException {
		
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
		Sheet sheet = workbook.getSheet("admindata");
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
		
		//validating the pop-up
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
		
		//creating the admin credentials
		driver.findElement(By.xpath("//p[normalize-space()='ADMIN']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Add Admin']")).click();
		WebElement addAdminButton = driver.findElement(By.xpath("//button[normalize-space()='Add Admin']"));
		addAdminButton.click();
		
		String expectedAdminTabName = "Dashboard";
		String actualAdminTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
		
		if (actualAdminTabName.equals(expectedAdminTabName)) {
			System.out.println("Admin page is displayed and varified on board name");
		}else {
			System.err.println("Admin page is not displayed and varified on board name");
		}
		
		
		String expectedAddAdminPopupTitle="Add Admin";
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
		String acName = "QSP";
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_companyid']")))).sendKeys("123");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_firstname']")).sendKeys(acName);
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_lastname']")).sendKeys("Basavangudi");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_middlename']")).sendKeys("Head Branch");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_contactno']")).sendKeys("78912354661");
//		WebElement selectType_dropdown = driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//select[@name='hr_type']"));
//		Select selectType_DD = new Select(selectType_dropdown);
//		selectType_DD.selectByVisibleText("→ HR Head");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_email']")).sendKeys("qsphead@gmail.com");
//		driver.findElement(By.xpath("//h4[text()='Add Admin']/../..//input[@name='hr_password']")).sendKeys("qsphead@gmail.com");
		
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
		String expectedAdminInsertPopuptext = "Insert Successfully!!!";//Insert Successfully!!!
		String actualAdminInsertPopuptext = driver.switchTo().alert().getText();
		if (actualAdminInsertPopuptext.equals(expectedAdminInsertPopuptext)) {
			System.out.println("Insert Successfully!!! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Insert Successfully!!! Popup is not displayed and varified upon popup text");
		}
		
		driver.switchTo().alert().accept();
		boolean flag = false;
		List<WebElement> EmployeeNames = driver.findElements(By.cssSelector("tbody tr td:nth-child(2)"));
		for (WebElement empName : EmployeeNames) {
			if(empName.getText().equals("QSP")) {
				flag=true;
				System.out.println("The Admin is sucessfully created");
				break;
			}
		}
		if(!flag) {
			System.out.println("The Admin is not created");
		}
		//Add To search the Created Admin in Search engine
		
		driver.findElement(By.cssSelector("input[type='search']")).sendKeys(acName);
		
		
		//edit the details
		WebElement editButton = driver.findElement(By.xpath("//td[text()='"+acName+"']/..//i[1]"));
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		editButton.click();

		
		String expectedEditAdminPopupTitle="Edit Admin";
		String actualEditAdminPopupTitle =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Edit Admin']"))).getText();
		
		if(actualEditAdminPopupTitle.equals(expectedEditAdminPopupTitle)) {
			System.out.println("Edit Admin popup is displayed and varified upon title");
		}else {
			System.err.println("Edit Admin popup is not displayed and varified upon title");
		}
		
		WebElement emailTF = driver.findElement(By.xpath("//h4[text()='Edit Admin']/../..//input[@name='hr_email']"));
		emailTF.clear();
		emailTF.sendKeys("qsphead1@gmail.com");
		WebElement passTF = driver.findElement(By.xpath("//h4[text()='Edit Admin']/../..//input[@name='hr_password']"));
		passTF.clear();
		passTF.sendKeys("qsphead1@123");
		driver.findElement(By.xpath("//input[@value='"+acName+"']/../../../../..//button[text()='Save']")).click();
		
		//popup for sucessfully
		String expectedAdminEditPopuptext = "Update Successfully!!!";//Update Successfully!!!
		String actualAdminEditPopuptext = driver.switchTo().alert().getText();
		if (actualAdminEditPopuptext.equals(expectedAdminEditPopuptext)) {
			System.out.println("Update Sucessfully Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Update Sucessfully Popup is not displayed and varified upon popup text");
		}
		
		driver.switchTo().alert().accept();
		
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
		
		//validate the created credentials
		/*
		 * Position: HR Head
		 * Email Address: qsphead@gmail.com
		 * password: qsphead@123
		 */
		//login to the application
		driver.findElement(By.name("hr_email")).sendKeys("qsphead1@gmail.com");
		driver.findElement(By.name("hr_password")).sendKeys("qsphead1@123");
		Select selectType = new Select(driver.findElement(By.id("hr_type")));
		selectType.selectByValue("HR Head");
		driver.findElement(By.name("login_hr")).click();
		
		//validating the popup
		String expectedLoginPopuptext = "Login Successfully!!";//Insert Successfully!!!
		String actualLoginPopuptext = driver.switchTo().alert().getText();
		if (actualLoginPopuptext.equals(expectedLoginPopuptext)) {
			System.out.println("Login Successfully!! Popup is displayed and varified upon popup text");
		}else {
			System.err.println("Login Successfully!! Popup is not displayed and varified upon popup text");
		}
		
		driver.switchTo().alert().accept();

		//terminate the browser
		driver.quit();
		
	}
}
