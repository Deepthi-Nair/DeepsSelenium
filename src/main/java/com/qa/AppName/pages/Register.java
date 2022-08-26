package com.qa.AppName.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.AppName.Base.BaseTest;
import com.qa.AppName.Constants.Constants;
import com.qa.AppName.utils.ElementUtility;

public class Register extends BaseTest{
	private WebDriver driver;
	private ElementUtility ele;
	
	public Register(WebDriver driver)
	{
		this.driver=driver;
		ele= new ElementUtility (driver);
		
	}
	
	
	//element Locators
	private By registerLink=By.xpath("//div[@class='list-group']/a[text()='Register']");
	
	private By pageHeading= By.xpath("//div[@id='content']/h1");
	
	private By fname= By.xpath("//input[@id='input-firstname']");
	private By lname= By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone= By.name("telephone");
	private By password=By.id("input-password");
	private By confirm =By.name("confirm");
	private By subscriptionNo=By.xpath("//input[@type='radio' and @value='0' and @name='newsletter']");
	private By subscriptionYes=By.xpath("//input[@type='radio' and @value='1' and @name='newsletter']");
	private By agree=By.name("agree");
	private By submit=By.xpath("//input[@type='submit']");
	
	private By succMsg=By.xpath("//div[@id='content']/h1");
	private By logout=By.linkText("Logout");
	
	public void clickReg()
	{
		ele.doClick(registerLink);
	}
	
	public String getTitle()
	{
		return ele.getText(pageHeading);
	}
	
/*	public void enterDetailsWithSub(String firstname,String lastname,String eid,String pw)
	
	{
		ele.waitForElementUsingFluentWait(5,1000, fname);
		ele.doSendkeys(fname,firstname);
		ele.doSendkeys(lname, lastname);
		ele.doSendkeys(email, eid);
		ele.doSendkeys(password, pw);
		ele.doClick(subscriptionYes);
		ele.doClick(agree);
		ele.doClick(submit);
		
	}*/
	
//	public void enterDetailsWithoutSub(String firstname,String lastname,String eid,String pw)
//	{
//		ele.doSendkeys(fname,firstname);
//		ele.doSendkeys(lname, lastname);
//		ele.doSendkeys(email, eid);
//		ele.doSendkeys(password, pw);
//		ele.doClick(subscriptionNo);
//		ele.doClick(agree);
//		ele.doClick(submit);
//	}
	public boolean createAccount(String firstname,String lastname,String eid,String ph,String pw,String subscribe)
	{
		ele.doSendkeys(fname, firstname);
		ele.doSendkeys(lname, lastname);
		ele.doSendkeys(email, eid);
		ele.doSendkeys(telephone, ph);
		ele.doSendkeys(password, pw);
		ele.doSendkeys(confirm, pw);
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
			ele.doClick(subscriptionYes);
		}
		else
		{
			ele.doClick(subscriptionNo);
		}
		
		
		ele.doClick(agree);
		ele.clickWhenReady(submit, 5, 1000);
		
		ele.waitForElementUsingFluentWait(5, 500, succMsg);
		String successMsg=ele.getText(succMsg);
		System.out.println(successMsg);
		if (successMsg.contains(Constants.REG_SUCCESS_MESG))
			
		{
			System.out.println("registration successful");
			ele.clickWhenReady(logout, 5, 1000);
			//ele.doClick(logout);
			//ele.doClick(registerLink);
			ele.clickWhenReady(registerLink, 5, 1000);
			return true;
		}
return false;
	}
	
	

}
