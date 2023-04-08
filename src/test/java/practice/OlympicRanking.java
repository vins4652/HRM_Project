package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlympicRanking {
	
	public static void main(String[] args) {
		olympicResult("Côte d'Ivoire");
	}

	public static void olympicResult(String countryName) {
		// setup the browser setting to avoid the notification popup
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// options.addArguments("--header");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// trigger the olympic application
		driver.get("https://olympics.com/en/olympic-games/tokyo-2020/medals");
		String countryNmae = countryName;
		try {
		String goldMedal=driver.findElement(By.xpath("//span[text()='"+countryNmae+"']/../following-sibling::div[1]/span")).getText();
		String silverMedal=driver.findElement(By.xpath("//span[text()='"+countryNmae+"']/../following-sibling::div[2]/span")).getText();
		String bronzeMedal=driver.findElement(By.xpath("//span[text()='"+countryNmae+"']/../following-sibling::div[3]/span")).getText();
		String totalMedal=driver.findElement(By.xpath("//span[text()='"+countryNmae+"']/../following-sibling::div[4]/span")).getText();
		
		System.out.println("Country Name : "+countryNmae);
		System.out.println("Gold Medal : "+goldMedal);
		System.out.println("Silver Medal : "+silverMedal);
		System.out.println("Bronze Medal : "+bronzeMedal);
		System.out.println("Total Medals : "+totalMedal);
		} catch(Exception e) {
			System.err.println("The country is not In the list");
		}
		
		driver.quit();
	}

}
