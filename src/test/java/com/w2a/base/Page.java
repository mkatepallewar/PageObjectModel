package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;

public class Page {
	public static WebDriver driver;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	
	public ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	
	public static String browser;
	
	public static TopMenu menu;
	
	
	/*
	 * Logs
	 * Properties OR Config
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	public Page() {
		if (driver==null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config File Loaded");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				fis =new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR File Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// This is used when we configure browser from Jenkins
			/*
			 * for getting browser parameter from jenkins, in configuration ---
			 * you should select This project is parameterised -- Choice option this option 
			 * and in build --> Goals and option -- > "clean test -Dbrowser=$browser" here $browser is the name of choice parameter
			 * 
			 */
			
			/*
			 * Using Git Plugin and testing
			 * 
			 * 
			 */
			System.out.println(System.getProperty("browser"));
			if (System.getProperty("browser")!=null && !System.getProperty("browser").isEmpty()) {
				browser=System.getProperty("browser");
			}else {
				browser=config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			
			
			
			if(browser.equals("firefox")){
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				driver=new FirefoxDriver();
				log.debug("Firefox Launched");
			}else if(browser.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				Map<String, Object> prefs=new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manage_enabled", false);
				ChromeOptions options=new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				driver=new ChromeDriver(options);
				log.debug("Chrome Launched");
			}else if(browser.equals("ie")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
				log.debug("IE Launched");
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Nevigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait =new WebDriverWait(driver, 10);
			menu=new TopMenu(driver);
		}
	}
	
	public static void quit() {
		driver.quit();
		log.debug("Execution Completed");
	}
	
	static WebElement dropdown;
	
	public static void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown= driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}else if(locator.endsWith("_XPATH")) {
			dropdown= driver.findElement(By.xpath(OR.getProperty(locator)));
		}else if(locator.endsWith("_ID")) {
			dropdown= driver.findElement(By.id(OR.getProperty(locator)));
		}
		Select select=new Select(dropdown);
		select.selectByVisibleText(value);
		log.debug("Selecting from dropdown at " +locator + "value as : "+value);
		test.log(LogStatus.INFO, "Selecting from dropdown at " +locator + "value as : "+value);
		
	}
	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if(locator.endsWith("_LINK")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}
		log.debug("Clicking on : " + locator);
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}
	
	
	public static void type(String locator, String value) {
		
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}	
		log.debug("Typing " + locator + "entered value as " +value);
		test.log(LogStatus.INFO, "Typing " + locator + "entered value as " +value);
	}
	
	
	public static void wait(String locator) {
		WebElement element ;
		if (locator.endsWith("_CSS")) {
			element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
			element.click();
		}else if(locator.endsWith("_XPATH")) {
			element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			element.click();
		}else if(locator.endsWith("_ID")) {
			element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id(locator)));
			element.click();
		}		
		log.debug("Waiting for " + locator);
		test.log(LogStatus.INFO, "Waiting for " + locator );
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public static void verifyEquals(String expected, String actual) throws IOException {
		
		try {
			Assert.assertEquals(expected, actual);
		}catch(Throwable t) {
			//ReportNG Report
			
			Utilities.captureScreeShot();
			Reporter.log("<br>"+"Verification Failure: " + t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+Utilities.screenShotName+"><img src="+Utilities.screenShotName+" height=200 width=200></img></>");
			Reporter.log("<br>");
			
			test.log(LogStatus.FAIL, "Verification failed with Exception: "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenShotName));
		}
	}

}
