package com.qa.AppName.Test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.AppName.Base.BaseTest;
import com.qa.AppName.Constants.Constants;
import com.qa.AppName.utils.ExcelUtil;

public class RegisterTest extends BaseTest{
	@Test(priority=1)
	
	public void reg()
	{
		//rp.clickReg();
		rp=lp.goToRegisterPage();
	}
	
	@Test(priority=2)
	
	public void getTitle()
	
	{
		String regPagehead=rp.getTitle();
		System.out.println(regPagehead);
		Assert.assertEquals(regPagehead, Constants.REG_PAGE_HEAD);
	}
	
//	@Test(priority=3)
//	
//	public void sendDetails()
//	{
//		rp.enterDetailsWithoutSub(prop.getProperty("firstName").trim(),prop.getProperty("lastName"),prop.getProperty("email"),prop.getProperty("pw"));
//	public String RandomEmail()}
	public String RandomEmail()
	{
		Random randomGen= new Random();
		String email="Deep"+randomGen.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	
	@DataProvider
	public Object[][] getRegisterData()
	{
		return ExcelUtil.getTestDataFromXL(Constants.REGISTER_SHEET_NAME);
	}
	@Test(priority=3,dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstname,String lastname,String ph,String pw,String subscribe)
	{
		
		rp.createAccount( firstname,lastname,RandomEmail(),ph, pw,  subscribe);
	}

}
