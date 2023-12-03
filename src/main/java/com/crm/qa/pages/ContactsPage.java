package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//*[@class ='selectable ']")
	WebElement contactsLink;

	@FindBy(xpath = "//input[@name = 'first_name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name = 'last_name']")
	WebElement lastName;

	@FindBy(xpath = "//*[@placeholder = 'Email address']")
	WebElement eMailId;

	@FindBy(xpath = "//input[@wfd-id='id86']")
	WebElement selectCountry;

	@FindBy(xpath = "//i[@class='save icon']")
	WebElement saveButton;

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactsLabel() {
		return contactsLink.isDisplayed();
	}

	public void selectContactsByName(String name) {
		driver.findElement(By.xpath("//td[./a[contains(text(),'" + name + "')]]"
				+ "/preceding-sibling::td/div[@class='ui fitted read-only checkbox']")).click();
	}
	public static void selectCountry(String countryToBeSelected) {
		List<WebElement> countryList = 
				driver.findElements(By.xpath("//div[@class='visible menu transition']/div"));
		for (int i = 0; i < countryList.size(); i++) {
			String stateVisible = countryList.get(i).getText();
			if (stateVisible.equalsIgnoreCase(countryToBeSelected)) {
				countryList.get(i).click();
				break;
			}
		}
	}

	public void createNewContact(String ftName, String ltName, String eMail,String country) {
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		eMailId.sendKeys(eMail);
		ContactsPage.selectCountry(country);
		saveButton.click();
	}
}
