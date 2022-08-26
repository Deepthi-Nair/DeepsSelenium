package com.qa.AppName.Constants;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
public static  final String LOGIN_PAGE_TITLE="Account Login";
public static final String LOGIN_PAGE_URL_FRACTION = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";
public static final int DEFAULT_TIME_OUT = 5;
public static final String REG_PAGE_HEAD="Register Account";

public static final String ACCOUNT_PAGETITLE="My Account";
public static final int IMAC_IMAGE_COUNT =4;
public static final CharSequence LOGIN_ERR_MSG = " Warning: No match for E-Mail Address and/or Password.";
public static final String ATTEMPT_EXCEEDEDED_MSG =" Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
public static final String REG_SUCCESS_MESG = "Your Account Has Been Created!";
public static final String REGISTER_SHEET_NAME = "registration";
public static final List<String> AccountPageSections()

{
	List<String> expList=new ArrayList<String>();
	expList.add("My Account");
	expList.add("My Orders");
	expList.add("My Affiliate Account");
	expList.add("NewsLetter");
	 return expList;
	
	}
}
