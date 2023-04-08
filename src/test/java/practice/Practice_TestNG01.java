package practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Practice_TestNG01 {

	
	@BeforeSuite
	public void beforeSuiteConfig() {
		System.err.println("I ll execute before Suite:--> I ll connect DB");
	}
	
	@BeforeTest
	public void beforeTestConfig() {
		System.out.println("I ll execute before Test:--> I ll do Parallel execution");
	}
	
	@BeforeClass
	public void beforeClassConfig() {
		System.out.println("I ll execute before class--> I ll Launch the browser");
	}
	
	@BeforeMethod
	public void beforeMethodConfig() {
		System.out.println("I ll execute before evrey method:--> I ll login to application");
	}
	
	@Test(priority = 0)
	public void test01() {
		System.out.println("Test------->01");
	}
	@Test(priority = -1)
	public void test02() {
		System.out.println("Test------->02");
	}
	@Test(priority = 2)
	public void test03() {
		System.out.println("Test------->03");
	}
	@Test(priority = 1, dependsOnMethods = "test02")
	public void test04() {
		System.out.println("Test------->04");
	}
	@Test(priority = 3)
	public void test05() {
		System.out.println("Test------->05");
	}
	@Test(invocationCount = 3)
	public void test06() {
		System.out.println("Test------->06");
	}
	@Test(priority = 4)
	public void test07() {
		System.out.println("Test------->07");
	}
	
	@AfterMethod
	public void afterMethodConfig() {
		System.out.println("I ll execute after evrey method:--> I ll logout of application");
	}
	
	@AfterClass
	public void afterClassConfig() {
		System.out.println("I ll execute after class--> I ll terminate the browser");
	}
	
	@AfterTest
	public void afterTestConfig() {
		System.out.println("I ll execute after Test:--> I ll do nothing");
	}
	
	@AfterSuite
	public void afterSuiteConfig() {
		System.err.println("I ll execute after Suite:--> I ll disconnect DB");
	}

}
