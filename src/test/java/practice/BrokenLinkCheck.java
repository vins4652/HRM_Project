package practice;

import java.io.IOException;
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

public class BrokenLinkCheck {

	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		//check the broken links in the sites
		driver.get("https://phptravels.com/");
		
		//find all the links from the sites
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		//to divide and store the links create two lists
		List<String> validLinks=new ArrayList<String>();
		List<String> brokenLink=new ArrayList<String>();
		
		//iterating the links to fetch the href attribute value 
		//and divide the broken and valid links
		for (WebElement oneLink : allLinks) {
			//fetching the link from anchor tag
			//link is attached to href attribute
			String link=oneLink.getAttribute("href");
			
			//check if the <a href="">
			if(link!=null) {
				//check if the <a href="www.fb.com">
				//check for missing protocol(http) in link
				if(link.contains("http")) {
					//if link is valaid add it to the validLinks list
					validLinks.add(link);
				}
				else {
					//if link is not null but protocol is missing
					//then add it to the brokenLink with proper message
					brokenLink.add(link+" ==> Not Having Protocol");
				}
			}else {
				//if the link is null add it to the brokenLink list
				brokenLink.add(link+" ===> Null");
			}
		}
		
		
		//validate the valink by connecting and checking the status code
		for (String vLink : validLinks) {
			try {
				//to check the url make it as URL type object
				URL url = new URL(vLink);
				
				//create a connection using URL class method openConnection()
				//it will return URLConnection
				URLConnection urlCon = url.openConnection();
				
				//type-cast the urlCon to HttpURLConnection to
				//to get a responseCode --> status code
				//to get a responseMessage
				HttpURLConnection httpUrlCon = (HttpURLConnection)urlCon;
				int statusCode = httpUrlCon.getResponseCode();
				String responseMessage = httpUrlCon.getResponseMessage();
				
				//if the link is failing to load then
				if (statusCode>=400) {
					brokenLink.add(vLink+" ==> "+statusCode+" ==>"+responseMessage);
				}
			} catch (Exception e) {
				//if the link is not connected server it ll throw exception then add
				brokenLink.add(vLink+" ==> Not Connect to the server");
			} 
		}
		
		
		//print the broken links with proper messages
		for (String messageAndLink : brokenLink) {
			System.out.println(messageAndLink);
		}
		driver.quit();
		
	}
	
}
