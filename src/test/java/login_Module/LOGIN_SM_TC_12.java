package login_Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


//Login as a HR Oficer
public class LOGIN_SM_TC_12 {
	
	static WebDriver driver =null;
	
	static WebDriverWait wait;
	public static void main(String[] args) throws IOException {
		
		// get the propery file data
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String browserName = pObj.getProperty("browser");
		String url = pObj.getProperty("url");
		/*
		 * officerusername hrofficer@gmail.com
		 * officerpassword hrofficer@123
		 * officerselect_type HR Officer
		 */
		String userName = pObj.getProperty("officerusername");
		String password = pObj.getProperty("officerpassword");
		String selecttype = pObj.getProperty("officerselect_type");
		
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
