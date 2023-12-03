package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;

public class HomePageTest extends TestBase {
	private static Logger log = Logger.getLogger(HomePageTest.class);
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	TasksPage tasksPage;
	

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		contactsPage = new ContactsPage();
		dealsPage = new DealsPage();
		tasksPage = new TasksPage();
		
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void validatehomePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		log.info("Starting validating home page title test ...");
		Assert.assertEquals(title, "Cogmento CRM", "HomePage title mismatch");
	}

	@Test(priority = 2)
	public void validateUserNameDisplay() {
		log.info("Starting validating user name display test ...");
		test = extent.createTest("validateUserNameDisplay");
		test.log(Status.INFO, "Starting validating user name display test ...");
		boolean flag = homePage.verifyUserName();
		 if (flag) {
	            test.log(Status.PASS, "UserName displayed");
	        } else {
	            test.log(Status.FAIL, "UserName not displayed");
	        }
	    }
	

	@Test(priority = 3)
	public void validateContactsLink() {
		log.info("Starting validating contacts link test ...");
		
		contactsPage = homePage.clickoOnContactsLink();
		System.out.println(contactsPage);
	}

	@Test(priority = 4)
	public void validateDealsLink() {
		log.info("Starting validating deals link test ...");
		dealsPage = homePage.clickoOnDealsLink();
		System.out.println(dealsPage);
	}

	@Test(priority = 5)
	public void validateTasksLink() {
		log.info("Starting validating tasks link test ...");
		tasksPage = homePage.clickoOnTasksLink();
		System.out.println(tasksPage);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}