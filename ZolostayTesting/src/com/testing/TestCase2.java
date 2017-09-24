package com.testing;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestCase2 {
	WebDriver browser;

	@Test
	public void testCase2() throws InterruptedException {
		// method class contains methods for login
		AllMethods method = new AllMethods();
		Select commonSelect;

		// send the URL to browser
		browser.get(getData("URL"));
		// login method
		method.login(browser);
		Thread.sleep(5000);
		// search for a place using search field
		method.searchKey(browser);
		Thread.sleep(3000);
		// select a particular place
		// enter in budget filter
		commonSelect = new Select(browser.findElement(By.xpath(getPath("budgetdropdown"))));
		commonSelect.selectByVisibleText(getData("budget"));
		// enter in sharing filler
		commonSelect = new Select(browser.findElement(By.xpath(getPath("sharingPreference"))));
		commonSelect.selectByVisibleText(getData("sharing"));
		// enter in PG type filter
		commonSelect = new Select(browser.findElement(By.id(getPath("PGtype"))));
		commonSelect.selectByVisibleText(getData("PGtype"));
		Thread.sleep(2000);
		// selecting a particular location
		browser.findElement(By.xpath(getPath("selectPG") + getData("PG")+"']")).click();
		Thread.sleep(2000);
		// request a bed
		browser.findElement(By.xpath(getPath("requestBed"))).click();
		Thread.sleep(2000);
		// enter date and selecting sharing type
		browser.findElement(By.xpath(getPath("inputDate"))).clear();
		browser.findElement(By.xpath(getPath("inputDate"))).sendKeys(getData("paymentDate"));
		browser.findElement(By.xpath(getPath(getData("sharingPreference")))).click();
		browser.findElement(By.xpath(getPath("Proccedtopay"))).click();
		Thread.sleep(5000);
		//getting Name and Phone number from payment 
		Assert.assertEquals(browser.findElement(By.xpath(getPath("TestName"))).getText().trim(), "Test User");
		Assert.assertEquals(browser.findElement(By.xpath(getPath("MobileNumber"))).getText().trim(), "7777777015");

		//Applying promo code
		browser.findElement(By.xpath(getPath("checkBox"))).click();
		browser.findElement(By.xpath(getPath("promoCode"))).sendKeys(getData("promoCode"));
		browser.findElement(By.xpath(getPath("applyCode"))).click();
		Thread.sleep(5000);
		//making payment
		browser.findElement(By.xpath(getPath("makePayment"))).click();

	}

	@BeforeClass
	public void beforeClass() {
		//setting up system properties and browser type
		System.setProperty("webdriver.chrome.driver", "/home/home/Workspace/Drivers/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/home/home/Workspace/Drivers/geckodriver");
		browser = new FirefoxDriver();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(5000);
		browser.close();
	}

	//get data from allData file
	public String getData(String key) {
		return GetDataFromFile.getDataFromDataFile(key);
	}

	//get data from allPath file
	public String getPath(String key) {
		return GetDataFromFile.getDataFrompathFile(key);
	}

}
