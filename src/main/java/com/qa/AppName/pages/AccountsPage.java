package com.qa.AppName.pages;


import javax.naming.directory.SearchResult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.AppName.utils.ElementUtility;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtility ele;
	public AccountsPage(WebDriver driver)
	{
		this .driver=driver;
		ele=new ElementUtility(driver);
		
	}
	
	private By title= By.cssSelector("div#logo a");
	private By searchfield=By.name("search");
	private By searchicon= By.cssSelector("div#search button i");
	

	public String accountTitle()
	{
		return driver.getTitle();
		
	}
	
	public SearchResultsPage searchItem(String prodName)
	{
		System.out.println("Searching a product....");
		ele.doClick(searchfield);
		ele.doClear(searchfield);
		ele.doSendkeys(searchfield, prodName);
		ele.doClick(searchicon);
		return new SearchResultsPage(driver);
	}
	
}
