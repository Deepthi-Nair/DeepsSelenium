package com.qa.AppName.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.AppName.Factory.DriverFactory;
import com.qa.AppName.utils.JavascriptExecutorUtils;
//import com.qa.AppName.Factory.DriverFactory;

public class ElementUtility {
	
	private  WebDriver driver;
	
	private JavascriptExecutorUtils jsutil;
	public ElementUtility(WebDriver driver) {
		// TODO Auto-generated constructor stub
	 this.driver=driver;

	 jsutil=new JavascriptExecutorUtils(driver);
	 
	}

	public WebElement getElement(By locator)
	{
		WebElement element= driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
		
		jsutil.flash(element);
		}
		else if(Boolean.parseBoolean(DriverFactory.Drawborder))
		{
			
			jsutil.drawBorder(element);
			
		}
		
		return element;
		
	}
	
	public WebElement getElement(By locator, int time)
	{
		
		return doPresenceOfElementlocated(locator, time);
	}

	

	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	
	public void doClear(By locator)
	{
		getElement(locator).clear();
	}
	
	public void doSendkeys(By locator,String value)
	{
		driver.findElement(locator).sendKeys(value);
	}
	
	
	public  void doSendkeys(String val,By locator, int time)
	{
		doPresenceOfElementlocated(locator, time).sendKeys(val);
	}
	
	public void  doClick(By locator)
	{
		if(doIsDisplayed( locator))
		{
		getElement(locator).click();
		}
		else
		{
			
			jsutil.scrollIntoView(driver.findElement(locator));
			getElement(locator).click();
			
		}
	}
	
	public void  doClick(By locator, int time)
	{
		doPresenceOfElementlocated(locator, time).click();
	}
	
	public String getText(By locator)
	{
		 return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public  List<String> getElementText(By locator)
	{
		List<WebElement> eleList= doGetElements(locator);
		List<String> eleTextList= new ArrayList<String>();
		for (WebElement e: eleList)
		{
			String text=e.getText();
			if(!text.isEmpty()) {
				eleTextList.add(text);
			
			}
		}
		return eleTextList;
	}
	
	
	public  int getLinksCount(By locator)
	{
		return doGetElements(locator).size();
	}
	
	public  List<WebElement> doGetElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	public String getAttributeValue(By locator,String attrVal)
	{
		String attVal=getElement(locator).getAttribute(attrVal);
		System.out.println(attVal);
		return attVal;
	}
	
	public boolean verifyElement(By locator)
	{
		int elementcount=getLinksCount(locator);
		if(elementcount==0)
		{
			System.out.println("element not found");
			return false;
		}
		else
		{
			System.out.println("element  found");
			return true;
			
		}
			
	}
	
	public  void doSelectByVal(By locator,String text)
	{
		Select sel=new Select(getElement(locator));
		sel.selectByVisibleText(text);
				}
	
	public  void doSelectByIndex(By locator,int index)
	{
		Select select= new Select(getElement(locator));
	    select.selectByIndex(index);
	}
	
	public void allSelectTags(By locator)
	{
		Select select=new Select(getElement(locator));
		List<WebElement> list=select.getOptions();
		System.out.println("List of dropdowns are: "+list.size());
	    for(WebElement e:list)
	    {
	    	System.out.println(e.getText());
	    }
	}
	
	public void doSelectDropdownValue(By locator,String text)
	{
		Select select=new Select(getElement(locator));
		List<WebElement> list=select.getOptions();
		 for(WebElement e:list)
		    {
		    	String val=e.getText();
		    	System.out.println(val);
		    	if(val.equalsIgnoreCase(text))
		    	{
		    		e.click();
		    		break;
		    	}
		    }
	}
	
	public void selectionWithoutSelect(By locator,String text)
	{
		List<WebElement> opts=getElements(locator);
		for(WebElement e:opts)
	    {
	    	String val=e.getText();
	    	System.out.println(val);
	    	if(val.equalsIgnoreCase(text))
	    	{
	    		e.click();
	    		break;
	    	}
	    }
		
	}
	
//********Links Util***********	
	public  void clickOnElement(By locator,String text)
	{
		List<WebElement> langList =getElements(locator);
	    System.out.println("lang list :"+langList.size());

	    for (WebElement e:langList)
	    {
	    	System.out.println(e.getText());
	    	if(e.getText().trim().equalsIgnoreCase(text))
	    	{
	    		e.click();
	    		break;
	    	}
	    }
	}
	
	
	public List<String> getLinksList(By locator)
	{
		List<WebElement> langList =getElements(locator);
	    
		List<String>LinkText= new ArrayList<String>();
		System.out.println("lang list :"+langList.size());
		
		for(WebElement e:langList)
		{
			LinkText.add(e.getText().trim());
		}
		return LinkText;
	}
	
	/************WebTable****************/
	
	public  void printTable(By rowloc,By colloc,String bxpath, String axpath)
	{
			int rowsize=getElements(rowloc).size();
			int colsize= getElements(colloc).size();
			for (int i=2;i<=rowsize;i++)
		{
				for(int j=1;j<=colsize;j++)
				{
					String xpath=bxpath+i+axpath+j+"]";
					//System.out.println(xpath);
					String rowVal=getText(By.xpath(xpath));
					System.out.print(rowVal+" : ");
				}
				System.out.println();
			}
		}
	
	
	/*************Element Wait utils**************/
	
	public  WebElement doPresenceOfElementlocated(By locator, int time)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
	 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	public  WebElement doPresenceOfElementlocated(By locator, int timeOut, long intervalTime)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
	 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	

	public  WebElement isElementVisible(By locator, int time)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
	   return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	

	public  WebElement isElementVisiblebyWebElement(By locator, int time)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
	   return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		
	}
	
	
	public  WebElement isElementVisiblebyWebElement(WebElement element, int  timeOut, long intervalTime)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofSeconds(timeOut));
	   return wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	
	public  WebElement isElementVisible(By locator,int timeOut, long intervalTime)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofSeconds(intervalTime));
	   return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public  List<WebElement> isElementsVisible(By locator,int timeOut, long intervalTime)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofSeconds(intervalTime));
	   return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
	public  List<WebElement> isElementsVisible(By locator, int time)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
	   return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
	public  List<String> getElementsTextWithWait(By locator, int time)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
		
	
	   List<WebElement>ele=  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		List <String> texts=new ArrayList<String>();
		for (WebElement e:ele) {
			String text=e.getText();
			texts.add(text);
			
		}
		return texts;
	}
	
	
	
	/***************Wait for Non Webelements*************************/
	
	public  boolean waitForurlToBe(String urlFraction,int timeOut)
	{
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	
	public  boolean waitForTiltleToBe(String titleFraction,int timeOut)
	{
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		return wait.until(ExpectedConditions.urlContains(titleFraction));
	}
	
	

	public  Alert waitForAlert(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public  String getAlertText(int timeOut)
	{
		return waitForAlert(timeOut).getText();
		
	}
	
	
	public  void doAlertAccept(int timeOut)
	{
		 waitForAlert(timeOut).accept();
		
	}
	
	public  void doAlertDismiss(int timeOut)
	{
		 waitForAlert(timeOut).dismiss();
		
	}
	
	public  void emterAlertAccept(int timeOut,String text)
	{
		 waitForAlert(timeOut).sendKeys(text);
		
	}
	
	
	public  void waitForFrameByNameorID(String nameorId,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameorId));
	}

	public  void waitForFrameByNameByIndex(int index,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		
	}
	
	public  void waitForFrameByNameByLocator(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		
	}
	
	public  void waitForFrameByNameByElement(WebElement frameElement,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		
	}
	
	public void clickWhenReady(WebElement ele,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();;
	}
	
	
	public void clickWhenReady(By locator,int timeOut,long interval)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(interval));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
	}
	
	public void waitForElementUsingFluentWait(int timeOut,long interval,By locator)
	{
		Wait<WebDriver> wait= new FluentWait<WebDriver>(driver)
				                  .withTimeout(Duration.ofSeconds(timeOut))
				                  .pollingEvery(Duration.ofMillis(interval))
				                  .ignoring(NoSuchElementException.class)
				                  .ignoring(StaleElementReferenceException.class)
				                  .withMessage("Element Not Found");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForElementUsingWebDriverWait(int timeOut,long interval,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait
		
        .pollingEvery(Duration.ofMillis(interval))
        .ignoring(NoSuchElementException.class)
        .ignoring(StaleElementReferenceException.class)
        .withMessage("Element Not Found");
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public  WebElement retrying(int timeOut,By locator) throws Exception
	{
		
	WebElement element =null;
	int attempt=0;
	while(attempt<timeOut) {
		try {
	
		element=getElement(locator);
		break;
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Element not found for "+locator+" in "+attempt+" attempts");
			try {
			Thread.sleep(500);
			}
			catch(InterruptedException e1)
			{
				e1.printStackTrace();
			}
		}
		attempt++;
	}
	if(element==null)
	{
		try {
		throw new Exception("Element not found exception");
		}
		catch(Exception e)
		{
			System.out.println("Element not found for "+timeOut+ "with interval of "+500+ " ms");
		}
	}
	return element;	
	}
	
	public  WebElement retrying(int timeOut,By locator,long pollingtime) throws Exception
	{
		
	WebElement element =null;
	int attempt=0;
	while(attempt<timeOut) 
	{
		try {
	
		element=getElement(locator);
		break;
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Element not found for "+locator+" in "+attempt+" attempts");
			try {
			Thread.sleep(500);
			}
			catch(InterruptedException e1)
			{
				e1.printStackTrace();
			}
		}
		attempt++;
	}
	if(element==null)
	{
		try {
		throw new Exception("Element not found exception");
		}
		catch(Exception e)
		{
			System.out.println("Element not found for "+timeOut+ "with interval of "+pollingtime+ " ms");
		}
	}
	return element;	
	}

}
