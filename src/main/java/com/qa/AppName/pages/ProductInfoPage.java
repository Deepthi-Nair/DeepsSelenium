package com.qa.AppName.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.AppName.utils.ElementUtility;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtility ele;
	
	private By prodHeader= By.cssSelector("li:nth-child(1) a:nth-child(1) img:nth-child(1)");
	private By images= By.xpath("//ul[@class='thumbnails']//img");
	private By productMetaData= By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By priceMetaData= By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty= By.id("input-quantity");
	private By addTocart= By.id("button-cart");
	
	private Map<String,String> prodInfoMap;
	
	
	public ProductInfoPage(WebDriver driver)
	{
	this .driver=driver;	
	ele =new ElementUtility(driver);
	}

	
	public String doGetProdHeader()
	{
		System.out.println(ele.getAttributeValue(prodHeader, "title")); 
		return ele.getAttributeValue(prodHeader, "title");
		
	}
	
	public int getProductImageCount()
	{
		return ele.isElementsVisible(images, 10).size();
	}
	
	
	public Map<String, String> getProdInfo()
	{
		prodInfoMap=new LinkedHashMap<String,String>();
		prodInfoMap.put("name", doGetProdHeader());
		getProdMetaData();
		getPriceData();
		return prodInfoMap;
		
	}
	
	
	
	/*	To Capture:
	 * Brand: Apple
Product Code: Product 18
Reward Points: 800
Availability: In Stock
	  */
	
	private void getProdMetaData()
	{
		List<WebElement> metaDataList=ele.getElements(productMetaData);
		for(WebElement e: metaDataList)
		{
			String text= e.getText();
			String metaArray[]=text.split(":");
			String metakey=metaArray[0].trim();
			String metaval=metaArray[1].trim();
			prodInfoMap.put(metakey, metaval);
		}
	
	 
	}
	
	
	
	/*To capture
	 * $2,000.00
Ex Tax: $2,000.00*/
	private void getPriceData()
	{
		List<WebElement> metaPriceList=ele.getElements(priceMetaData);
		String mainPrice=metaPriceList.get(0).getText().trim();
		String exPrice=metaPriceList.get(1).getText().trim();
		prodInfoMap.put("pricekey",mainPrice);
		prodInfoMap.put("Expricekey",exPrice);
		
	}
}
