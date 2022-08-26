package com.qa.AppName.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.qa.AppName.Base.BasePage;
import com.qa.AppName.Constants.Constants;
import com.qa.AppName.utils.ElementUtility;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	private ElementUtility ele;
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		ele=new ElementUtility(driver);
	}
	
	
	// By Locators
	
	
	//private By emailId= By.id("input_email");
    private By emailId=By.id("input-email");
	private By password= By.id("input-password");
	private By login= By.xpath("//input[@type='submit']");
	private By registerLink=By.linkText("Register");
	private By forgotPwdLink= By.linkText("Forgotten Password");
	private By loginErrMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	// Page Actions
	
	@Step("Getting login page title..")
	
	public String getLoginPagTitle()
	{
		return driver.getTitle();
	}
	
	public String getLoginPageurl()
	{
		return driver.getCurrentUrl();
	}
	@Step("Getting pw forgot link exist or not..")
	public boolean isForgotPwdLinkExist()
	{
		return ele.doIsDisplayed(forgotPwdLink);
	}
	
	
	public boolean isRegLinkExist()
	{
		return ele.doIsDisplayed(registerLink);
	}
	@Step("logging in with user name:{0} and password :{1}")
	public AccountsPage doLogin(String un,String pw) throws InterruptedException
	{
		System.out.println("Login with user name: "+un+" and password "+pw);
		ele.doSendkeys(emailId, un);
		ele.doSendkeys(password, pw);
		ele.doClick(login);
		//Thread.sleep(500);
		System.out.println("Logging in ...");
		ele.doClick(login);
		
		return new AccountsPage(driver);
	}
	@Step("logging in with wrong user name:{0} and wrong password :{1}")
	public boolean doLoginWithWrongCred(String un,String pw)
	{
	System.out.println("Logging in with wrong credentials");
	ele.doSendkeys(emailId, un);
	ele.doSendkeys(password, pw);
	ele.doClick(login);
	String errMsg= ele.getText(loginErrMsg);
	System.out.println(errMsg);
	if(errMsg.contains(Constants.LOGIN_ERR_MSG) || errMsg.contains(Constants.ATTEMPT_EXCEEDEDED_MSG))
	{
		System.out.println("Login is not successful");
		return true;
	}
	return false;
	}
	
	public Register goToRegisterPage()
	{
		ele.doClick(registerLink);
		return new Register(driver);
		
	}
	
	

}
