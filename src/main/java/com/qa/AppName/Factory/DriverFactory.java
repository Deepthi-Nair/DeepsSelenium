package com.qa.AppName.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static  String Drawborder;
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsnmanager;
    
	public static ThreadLocal<WebDriver> tDriver= new ThreadLocal<WebDriver>();
	public WebDriver init_driver(Properties prop) throws InterruptedException
	
	{
		
		String browser=prop.getProperty("browser");
		System.out.println("Browser Name is : "+browser);
		highlight=prop.getProperty("highlight");
		Drawborder=prop.getProperty("DrawBorder");
		optionsnmanager= new OptionsManager(prop);
		
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsnmanager.getChromeOptions());
			tDriver.set(new ChromeDriver(optionsnmanager.getChromeOptions()));
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsnmanager.getfirefoxOptions());
			tDriver.set(new FirefoxDriver(optionsnmanager.getChromeOptions()));
		}
		else if (browser.equalsIgnoreCase("safari"))
		{
			WebDriverManager.safaridriver().setup();
			//driver=new SafariDriver();
		
			
		}
		
		else {
			System.out.println("Please pass  thr right browser");
		}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		
		getDriver().get(prop.getProperty("url"));
		Thread.sleep(1000);
		return getDriver();
	}
	
	
	public static synchronized WebDriver getDriver()
	{
		return tDriver.get();
		}
	
	public Properties init_prop()
	{
		prop=new Properties();
		FileInputStream ip=null;
		
		String envName = System.getProperty("env");
		if (envName==null)
		{
			System.out.println("Running on prod env..");
			 try {
				ip=new FileInputStream("./src/test/resources/testrunners/Config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				//prop.load(ip);
			 catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
		
		else {
			System.out.println("Running on env " +envName);
			try
			{
			switch(envName.toLowerCase())
			{
			case "qa":
				ip=new FileInputStream("./src/test/resources/testrunners/Config/qa.config.properties");
				break;
			case "stage":
				ip=new FileInputStream("./src/test/resources/testrunners/Config/stage.config.properties");
				break;
				default:
					System.out.println("Please pass the right env...");
					break;
			}
		}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
	/*Take screens shot*/
	public String getScreenshot()
	{
		File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination =new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
