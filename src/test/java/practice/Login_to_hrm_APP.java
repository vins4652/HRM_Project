package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_to_hrm_APP {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String browserName = pObj.getProperty("browser");
		String url = pObj.getProperty("url");
		String userName = pObj.getProperty("username");
		String password =pObj.getProperty("password");
		String selecttype =pObj.getProperty("select_type");
		
		WebDriver driver=null;
		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.name("hr_email")).sendKeys(userName);
		driver.findElement(By.name("hr_password")).sendKeys(password);
		Select select = new Select(driver.findElement(By.id("hr_type")));
		select.selectByValue(selecttype);
		driver.findElement(By.name("login_hr")).submit();
		
		System.out.println(browserName);
		System.out.println(url);
		System.out.println(userName);
		System.out.println(password);
		System.out.println(selecttype);
	}
	

}
