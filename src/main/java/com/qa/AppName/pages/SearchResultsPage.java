package com.qa.AppName.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.AppName.utils.ElementUtility;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtility ele;
	public SearchResultsPage(WebDriver driver)
	{
		this .driver=driver;
		ele=new ElementUtility(driver);
		
	}
	
	private By prodList =By.cssSelector("div.caption a");
	
	public int getProdListCount()
	{
          ele.doPresenceOfElementlocated(prodList, 10, 2000);
		
		List <WebElement> prods=ele.getElements(prodList);
		int prodCount=prods.size();
		System.out.println("The searched product coint is : "+prodCount);
		return prodCount;
	}
	
	public  ProductInfoPage selectProd(String mainProdName)
	{
		System.out.println("Main prod name is :  "+ mainProdName);
		List<WebElement> searchList=ele.getElements(prodList);
		
			for (WebElement e: searchList)
			{
				String text=e.getText();
				System.out.println(text);
				if(text.equalsIgnoreCase( mainProdName))
					
				{
					System.out.println("clicking on selected item..." + mainProdName);
					e.click();
				break;
				}
			}
			return new ProductInfoPage(driver);
		
	}
}
