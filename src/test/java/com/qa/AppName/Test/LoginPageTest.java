package com.qa.AppName.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.AppName.Base.BaseTest;
import com.qa.AppName.Constants.Constants;
import com.qa.AppName.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic #: Design open cart -login")
@Story("US #:open cart with multiple users")
public class LoginPageTest extends BaseTest{
	@Description("Login url test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		
		 //loginPageObj.getLoginPagTitle();
		String title=lp.getLoginPagTitle();
		System.out.println("Tiltle is :"+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		
	
		
	}
	@Description("Login url test verification")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void loginPageurlTest()
	{
		String actUrl=lp.getLoginPageurl();
		System.out.println("url is : "+actUrl);
		Assert.assertEquals(actUrl, Constants.LOGIN_PAGE_URL_FRACTION);
	}
	@Description("Forgot password link test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void forgotpwdLinkTest()
	{
		Assert.assertTrue(lp.isForgotPwdLinkExist());
		
	}
	
	@Test(priority=3)
	
	public void loginTest() throws InterruptedException
	{
		lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	

}
