package com.w2a.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {
	
	public CRMHomePage goToCRM() {
		click("CRM_XPATH");
		return new CRMHomePage();
	}
	
	public void goToCliq() {
		click("Cliq_XPATH");
	}
	
	public void gotoBooks() {
		
		click("Books_XPATH");
	}
	
}
