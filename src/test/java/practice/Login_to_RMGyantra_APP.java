package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_to_RMGyantra_APP {
	
	public static void main(String[] args) throws IOException, SQLException {
		//Steps to retrive the data from properties file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\RMGYantra.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String browserName = pObj.getProperty("browser");
		String url = pObj.getProperty("url");
		String userName = pObj.getProperty("username");
		String password =pObj.getProperty("password");
		String projectName = pObj.getProperty("project_name");
		String projectManager = pObj.getProperty("project_manager");
		String projectStatus =pObj.getProperty("project_status");
		//Script to login and submit the project in RMGyantra
		
		WebDriver driver=null;
		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.id("usernmae")).sendKeys(userName);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Sign in']")).submit();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.cssSelector("button.btn.btn-success")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys(projectManager);
		Select select = new Select(driver.findElement(By.xpath("//select[(@name='status') and not (@class)]")));
		select.selectByValue(projectStatus);
		driver.findElement(By.cssSelector("input[value='Add Project']")).click();
		driver.quit();
		
		
		Driver driver1 = new Driver();
		DriverManager.registerDriver(driver1);
		Connection connection = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
		Statement statement = connection.createStatement();
		String QueryToFetchTableData = "Select * From project;";
		ResultSet tabledata = statement.executeQuery(QueryToFetchTableData);
		
		boolean flag =false;
		while(tabledata.next()) {
			String actualProject = tabledata.getString(4);
			System.out.println(actualProject);
			if (actualProject.equals(projectName)) {
				flag=true;
			}
		}
		if (flag) {
			System.out.println("project is created and verified in database");
		} else if(!flag){
			System.out.println("project is not created and verified in database");
		}
		
		connection.close();
		
		
		
	}
	
}
