package branch_Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

//Create Branches
public class BRANCH_SM_TC_06 {
	
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
		
		//driver.findElement(By.xpath("//button[normalize-space()='Add Corporate']")).click();
		
		String expectedAddBranchesPopupTitle="Add Branches";
		String actualAddBranchesPopupTitle= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Add Branches']"))).getText();
		//String actualAddBranchesPopupTitle= driver.findElement(By.xpath("//h4[text()='Add Branches']")).getText();
		
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
		boolean flag = false;
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
