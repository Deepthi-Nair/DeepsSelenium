package com.qa.AppName.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.AppName.Base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{
	@DataProvider(name="Negative Login")
	public Object[][] LoginWrongTestData() {
		
		return new Object[][] {
			{"test11@gmail.com","ygjhg"},
			{"test1@gmail.com","ygjhvg"},
			{"test11@gmail.com",""}
			
		};
	}
	
	
	@Test(dataProvider = "Negative Login")
	public void loginNegativeTest(String un,String pw)
	{
		
		Assert.assertFalse(lp.doLoginWithWrongCred(un, pw));
		
		
	}

}
