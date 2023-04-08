package practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpaceJetbook {
	
	@Test
	public void spice() {
		// setup the browser setting to avoid the notification popup
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// options.addArguments("--header");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// trigger the SpaceJet application
		driver.get("https://www.spicejet.com/");
		// Close he JS popup on display
		// driver.switchTo().alert().dismiss();
		// Select the round trip
		driver.findElement(By.xpath("//div[text()='round trip']/../..//*[name()='svg']//*[name()='circle']")).click();
		driver.findElement(By.cssSelector("div[data-testid='to-testID-origin']")).click();
		//sending from location
		driver.findElement(By.xpath("//div[text()='From']/..//input")).sendKeys("banga");
		//sending to location
		driver.switchTo().activeElement().sendKeys("Manga");

		// Select the travel date
		Calendar caldr = Calendar.getInstance();
		SimpleDateFormat sd = new SimpleDateFormat("MMMM-yyyy");
		String travelMonthYear = sd.format(caldr.getTime());
		SimpleDateFormat sd1 = new SimpleDateFormat("dd");
		String date = sd1.format(caldr.getTime());

		//selecting the travelling date as computer date
		driver.findElement(By
				.xpath("//div[@data-testid='undefined-month-" + travelMonthYear + "']/..//div[text()='" + date + "']"))
				.click();
		driver.findElement(By
				.xpath("//div[@data-testid='undefined-month-" + travelMonthYear + "']/..//div[text()='" + date + "']"))
				.click();
		
		//selecting the retun date
		String returnMonthYear = "June-2023";
		String returnDate = "20";
		driver.findElement(By.xpath("//div[text()='Return Date']")).click();
		
		for (;;) {
			try {
				driver.findElement(By.xpath("//div[@data-testid='undefined-month-" + returnMonthYear
						+ "']/..//div[text()='" + returnDate + "']")).click();
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath(
						"//div[@data-testid=\"undefined-calendar-picker\"]//*[name()='svg']//*[name()='circle'][1]"))
						.click();
			}
		}
		
		//selecting the passenger
		driver.findElement(By.xpath("//div[text()='Passengers']")).click();
		int adult = 7;
		while (adult>0) {
			driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']")).click();
			adult--;
		}
		//selecting currency
		driver.findElement(By.xpath("//div[text()='Currency']")).click();
		driver.findElement(By.xpath("//div[text()='USD']")).click();
		//clicking on search button
		driver.findElement(By.xpath("//div[@data-testid='home-page-flight-cta']")).click();
	}

}
