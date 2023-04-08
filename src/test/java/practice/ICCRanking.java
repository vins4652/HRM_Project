package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ICCRanking {
	
	public static void main(String[] args) {
		//tableData();
		//verifyCountry();
		 teamWithRanking();
		
	}
	
	public static void tableData() {
		
		WebDriver driver =WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/t20i");
		
		List<WebElement> rankings = driver.findElements(By.xpath("//tbody/tr/td[1]"));
		List<WebElement> country = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		List<WebElement> matches = driver.findElements(By.xpath("//tbody/tr/td[3]"));
		List<WebElement> points = driver.findElements(By.xpath("//tbody/tr/td[4]"));
		List<WebElement> rating = driver.findElements(By.xpath("//tbody/tr/td[5]"));
		
		for (int i =0;i<rankings.size();i++) {
			
			System.out.println(rankings.get(i).getText()+"     "+country.get(i).getText()+"     "+matches.get(i).getText()+"    "+points.get(i).getText()+"    "+rating.get(i).getText());
		}
		
		driver.quit();
	}
	
	public static void verifyCountry() {
		
		WebDriver driver =WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/t20i");
		List<WebElement> country = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		String data = "Ausralia";
		boolean flag=false;
		for (WebElement name : country) {
			if(name.getText().equals(data)) {
				System.out.println("The country is present");
				flag=true;
				break;
			}
			if(!flag) {
				System.err.println("The country is not present");
			}
			
		}
	}
	
	public static void teamWithRanking() {
		
		WebDriver driver =WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/t20i");
		List<WebElement> country = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		
		ArrayList<String> teams = new ArrayList<String>();
		
		for (WebElement cName : country) {
			teams.add(cName.getText());	
		}
		for (String team : teams) {
			String ranking = driver.findElement(By.xpath("//tbody//span[text()='"+team+"']/../../td[1]")).getText();
			String name = driver.findElement(By.xpath("//tbody//span[text()='"+team+"']/../../td[2]")).getText();
			String matches = driver.findElement(By.xpath("//tbody//span[text()='"+team+"']/../../td[3]")).getText();
			String points = driver.findElement(By.xpath("//tbody//span[text()='"+team+"']/../../td[4]")).getText();
			String rating = driver.findElement(By.xpath("//tbody//span[text()='"+team+"']/../../td[5]")).getText();
			
			System.out.println(ranking+"\t"+name+"\t"+matches+"\t"+points+"\t"+rating);
		}
	}
}
