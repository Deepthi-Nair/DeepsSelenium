package com.qa.AppName.Test;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.AppName.Base.BaseTest;
import com.qa.AppName.Constants.Constants;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
public void accountPageSetUp() 
	{
		System.out.println("setting up account page...");
		
		
		
		try {
			lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		} catch (NoSuchElementException | InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test(priority=3)
	public void accountTitleTest()
	{
		String title=ap.accountTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGETITLE);
	}
	
	@DataProvider
	public Object[][] productData()
	{
		return new Object[][] {
			{"Macbook"},{"iMac"},{"Apple"}
		};
		
	}
	
	
	@DataProvider
	public Object[][] productSelectData()
	{
		return new Object[][] {
			{"Macbook","MacBook Pro"},{"iMac","iMac"},{"samsung","Samsung SyncMaster 941BW"},{"Apple","Apple Cinema 30\""}
		};
		
	}
	
	
	
	
	@Test(priority=4,dataProvider="productData")
	public void searchTest(String pname)
	{
		search = ap.searchItem(pname);
		search.getProdListCount();
		Assert.assertTrue(search.getProdListCount()>0);
		
		
	}
	@Test(priority=5,dataProvider="productSelectData")
	public void selectProdTest(String pname,String mainProdName)
	{
		search = ap.searchItem(pname);
		prodinfo=search.selectProd(mainProdName);
		Assert.assertEquals(prodinfo.doGetProdHeader(), mainProdName);
	    
	}
	
	
}
