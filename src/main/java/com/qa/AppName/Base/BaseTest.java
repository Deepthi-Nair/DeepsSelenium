package com.qa.AppName.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.AppName.Factory.DriverFactory;
import com.qa.AppName.pages.AccountsPage;
import com.qa.AppName.pages.LoginPage;
import com.qa.AppName.pages.ProductInfoPage;
import com.qa.AppName.pages.Register;
import com.qa.AppName.pages.SearchResultsPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	public Properties prop;
	public LoginPage lp;
	public Register rp;
	public AccountsPage ap;
	public SearchResultsPage search;
	public ProductInfoPage prodinfo;
	public SoftAssert softassert;
	
	
	@BeforeTest
	public void setup() throws InterruptedException { 
		
		df= new DriverFactory();
		prop=df.init_prop();
		driver=df.init_driver(prop);
	    lp= new LoginPage(driver);
		rp=new Register(driver);
		ap=new AccountsPage(driver);
		search = new SearchResultsPage(driver);
		prodinfo = new ProductInfoPage(driver);
		softassert=new SoftAssert();		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	

}
