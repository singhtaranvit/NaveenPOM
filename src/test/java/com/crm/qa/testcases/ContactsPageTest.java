package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.utilis.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	TasksPage tasksPage;
	String sheetname = "crmdata";

	public ContactsPageTest() {
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
		contactsPage = homePage.clickoOnContactsLink();
	}

	@Test(priority = 1)
	public void validateContactsPageNavigation() {
		Assert.assertTrue(contactsPage.verifyContactsLabel());
	}

	@Test(priority = 2)
	public void selectFromContactsList() {
		contactsPage.selectContactsByName("crm free");
	}

	@Test(priority = 3)
	public void selectMultipeFromContactsList() {
		contactsPage.selectContactsByName("harman");
		contactsPage.selectContactsByName("crm free");
	}
	@Test(priority = 4)
		public void validateCreateSingleContact() {
		homePage.clickOnCreateContactLink();
		contactsPage.createNewContact("tom", "sandhu","ex@ex.com","Canada");
	}


	@DataProvider
	public Object[][] getCRMContactsData() throws Exception {
		Object[][] crmData = TestUtil.readExcelDataForCRM(sheetname);
		return crmData;

	}
	@Test(priority = 5, dataProvider = "getCRMContactsData")
	public void validateCreateNewContact(String firstName, String lastName, String eMail, String country) {
		homePage.clickOnCreateContactLink();
		contactsPage.createNewContact(firstName, lastName, eMail, country);
	}

	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
}
