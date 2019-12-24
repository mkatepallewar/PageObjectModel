package com.w2a.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.w2a.base.Page;

public class LoginPage extends Page {
	


	public ZohoAppPage doLogin(String userName, String password) throws InterruptedException {
		System.out.println("In Login Page");
		type("email_CSS", userName);
		click("nextbtn_CSS");
		
		Thread.sleep(1000);
		type("password_CSS", password);
		click("signbtn_CSS");
		
		return new ZohoAppPage();
		

	}

}
