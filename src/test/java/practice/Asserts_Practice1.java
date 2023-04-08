package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.GenericUtility.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.GenericUtility.TestListener.class)
public class Asserts_Practice1 extends BaseClass {

	@Test
	public void igh() {
//		WebDriverManager.edgedriver().setup();
//		WebDriver driver = new EdgeDriver();
		
//		WebDriver driver = WebDriverManager.operadriver().create();
//		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver= new ChromeDriver();
		
		
//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.facebook.com");
		Assert.fail();
	}
}

//let name = 'sindhu'
//let ename 
//while(ename!=name) {
//	ename = prompt("Type Idiot Name")
//}
//alert("Yes I am a idiot")
