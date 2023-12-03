package com.crm.qa.testcases;

import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;

public class EnterCountry extends TestBase {
	HomePage homePage = new HomePage();
	ContactsPage contactsPage = new ContactsPage();
	@Test
public void validateCreateSingleContact() {
	homePage.clickOnCreateContactLink();
	contactsPage.createNewContact("tom", "sandhu","ex@ex.com","Canada");
}
}
