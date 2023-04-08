package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
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

public class deleteBranches {

	static WebDriver driver;
	static WebDriverWait wait;
	public static void main(String[] args) throws IOException, Throwable {
		//get the propery file data
		Properties pObj = new Properties();
				FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
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
				System.out.println("Popup is displayed and varified upon popup text");
			}else {
				System.err.println("Popup is not displayed and varified upon popup text");
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
//			WebElement addCorporateButton = driver.findElement(By.xpath("//button[normalize-space()='Add Corporate']"));
//			addCorporateButton.click();
			
			String expectedCorporateTabName = "Dashboard";
			String actualCorporateTabName = driver.findElement(By.xpath("//div[@class='row mb-2']//h1")).getText();
			
			if (expectedCorporateTabName.equals(actualCorporateTabName)) {
				System.out.println("Corporate page is displayed and varified on board name");
			}else {
				System.err.println("Corporate page is not displayed and varified on board name");
			}
			
			WebElement deleteButton = driver.findElement(By.cssSelector("button.btn.btn-danger.btn-sm"));
			for(;;) {
				if(false) {
					break;
				}
				wait.until(ExpectedConditions.visibilityOf(deleteButton)).click();
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//h4[text()='Delete Corporate']/../..//button[text()='Delete'])[last()]")))).click();
				wait.until(ExpectedConditions.alertIsPresent()).accept();
				Thread.sleep(500);
			}
			driver.quit();
	}
}
