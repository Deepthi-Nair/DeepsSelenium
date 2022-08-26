package com.qa.AppName.Test;

import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.AppName.Base.BaseTest;

import io.netty.util.Constant;

public class ProductInfoTest extends BaseTest{
	private static final String Constants = null;


	@BeforeClass
public void productPageSetUp() 
	{
		System.out.println("setting up account page...");
		
		
		
		try {
			ap=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		} catch (NoSuchElementException | InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void productHeaderTest()
	{
		search = ap.searchItem("Mac Book");
		prodinfo=search.selectProd("MacBook Pro");
		Assert.assertEquals(prodinfo.doGetProdHeader(), "MacBook Pro");
	}
	
	
	@Test(priority=2)
	public void productImageCountTest() {
		
	
		search = ap.searchItem("iMac");
		prodinfo=search.selectProd("iMac");
		Assert.assertEquals(prodinfo.getProductImageCount(),3);
	}
	
	@Test(priority=3)
	public void prodInfoTest()
	{
		search = ap.searchItem("Mac Book");
		prodinfo=search.selectProd("MacBook Pro");
		Map<String,String>actProductInfo=prodinfo.getProdInfo();
		actProductInfo.forEach((k,v)->System.out.println(k +":"+ v));
		softassert.assertEquals(actProductInfo.get("name"), "MacBook Pro");
		softassert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softassert.assertEquals(actProductInfo.get("pricekey"), "$2,000.00");
		softassert.assertAll();
		   
		    
	}
	

}
