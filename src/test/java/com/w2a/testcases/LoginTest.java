package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import com.w2a.utilities.Utilities;

public class LoginTest extends BaseTest {
	
	@Test (dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) {
		HomePage home=new HomePage();
		LoginPage lp= home.goToLogin();
		Page.log.debug("Inside Login test");
		try {
			ZohoAppPage zp= lp.doLogin(data.get("username"), data.get("password"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Page.log.debug("Login test Failed.");
			Page.log.error(e);
		}
		Page.log.debug("Login test Success.");
	}

}
