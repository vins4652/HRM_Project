package practice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookBrokenLinks {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.facebook.com/");
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		
		List<String> activeLinks = new ArrayList<String>();
		List<String> brokenLinks = new ArrayList<String>();
		
		for (WebElement oneLink : allLinks) {
			
			String link = oneLink.getAttribute("href");
			if (link!=null) {
				if (link.contains("http")) {
					activeLinks.add(link);
				}else {
					brokenLinks.add(link+" ==> Protocol is missing");
				}
			}else {
				brokenLinks.add(link+" ==> Link is null");
			}
		}
		
		for (String link : activeLinks) {
			try {
				URL url = new URL(link);
				URLConnection urlCon = url.openConnection();
				HttpURLConnection httpUrlCon = (HttpURLConnection)urlCon;
				int statusCode = httpUrlCon.getResponseCode();
				String responseMSG = httpUrlCon.getResponseMessage();
				if (statusCode>=400) {
					brokenLinks.add(link+ " ==> Link is failed to connect and message is "+responseMSG);
				}
			} catch (Exception e) {
				brokenLinks.add(link+" == Link is not connected to server");
			}
		}
		
		for (String link : brokenLinks) {
			System.out.println(link);
		}
		System.out.println(activeLinks.size());
		driver.quit();
		
	}
}
