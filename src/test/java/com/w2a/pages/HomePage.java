package com.w2a.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.base.Page;

public class HomePage extends Page {
	
	public void goToSignUp() {
		
		click("singuplink_CSS");
	}
	
	public LoginPage goToLogin() {
		click("loginlink_XPATH");

		return new LoginPage();
	}
	
	public void goToSupport() {
		
		click("supportlink_CSS");
	}
	
	public void goToZohoEdu() {
		
	}
	
	public void goToLearnMore() {
		
	}
	
	public void validateFooterLinks() {
		
	}
}
