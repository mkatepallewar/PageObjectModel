package com.w2a.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.accounts.AccountsPage;

public class TopMenu {
	
	/*
	 * 
	 * TopMenu is a page???
	 * 
	 * HomePage has a Top menu
	 * Account page has a top menu
	 * 
	 * hasa - encapsulation ie. inheritance
	 */
	WebDriver driver;
	public TopMenu(WebDriver driver) {
		this.driver=driver;
		
	}
	public void gotoHome() {
		
	}
	
	public void gotoLeads() {
		
	}
	
	public AccountsPage gotoAccounts() {
		
		Page.click("Account_LINK");
		return new AccountsPage();
	}
	
	public void gotoContacts() {
		
	}
	
	public void gotoDeals() {
		
	}
	
	public void SignOut() {
		
	}
}
