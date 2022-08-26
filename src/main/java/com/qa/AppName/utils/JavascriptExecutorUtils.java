package com.qa.AppName.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class JavascriptExecutorUtils {
 
	private WebDriver driver;
	
	public JavascriptExecutorUtils(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public String getTitlebyJS()
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		return js.executeScript("return document.title;").toString();
	}
	
	public void generateAlert(String msg)
	{
      JavascriptExecutor js= (JavascriptExecutor)driver;
	js.executeScript("alert('"+msg+"')");
		
	}
	
	public String getPageInnerTextbyJS()
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	
	public void drawBorder(WebElement ele)
	{
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red'",ele);
	}
	
	public void flash(WebElement ele)
	{
		String bgcolor=ele.getCssValue("backgroundColor");
		for (int i=0;i<10;i++)
		{
			changeColor("rgb(0,200,0)",ele);
			changeColor(bgcolor,ele);
		}
		
	}
	
	private void changeColor(String color,WebElement ele)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='" +color+ "'", ele);
	 try 
	 {
		 Thread.sleep(20);
		 
	 }
	 catch(InterruptedException e)
	 {
		 
	 }
	}
	
	public void clickOnElement(WebElement ele)
	{
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("argument[0].click();",ele);
	}
	public void scrollPageDown(WebElement ele)
	{
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("Window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollPageDown(String height)
	{
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("Window.scrollTo(0,'"+height+"')");
	}
	
	public void scrollPageUp(WebElement ele)
	{
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("Window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void scrollIntoView(WebElement ele)
	{
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("return arguments[0].scrollIntoView();",ele);
	}

	
	
	
	
	
}
