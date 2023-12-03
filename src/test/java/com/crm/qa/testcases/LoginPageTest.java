package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homepage;
	ExtentReports extent;
	ExtentTest test;
	
	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		extent = new ExtentReports();
		initialization();
		loginPage = new LoginPage();
	}
	@Test(priority = 1)
	public void loginpPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM Power Up your Entire Business Free Forever");
	}
	@Test(priority = 2)
	public void crmLogoImageTest() {
		boolean flag = loginPage.validateCRMLogo();
		Assert.assertTrue(flag);
	}
	@Test(priority = 3)
	public void crmLoginButtonTest() {
		homepage = loginPage.validateLoginButton();
	System.out.println(homepage);
	}
	@Test(priority = 4)
	public void crmLoginTest() {
		homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
