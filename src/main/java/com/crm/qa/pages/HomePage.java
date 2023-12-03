package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	Actions actions;

	@FindBy(xpath = "//b[contains(text(),'tsb')]")
	WebElement usernameLabel;

	@FindBy(xpath = "//i[@class='home icon']")
	WebElement homeIcon;

	@FindBy(xpath = "//span[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//button[@class='ui linkedin button']/child::*[@class = 'edit icon']")
	WebElement newContactLink;

	@FindBy(xpath = "//span[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//span[contains(text(),'Tasks')]")
	WebElement tasksLink;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyUserName() {
		return usernameLabel.isDisplayed();
	}

	public ContactsPage clickoOnContactsLink() {
		actions = new Actions(driver);
		actions.moveToElement(homeIcon).build().perform();
		contactsLink.click();
		driver.findElement(By.xpath("//*[@id='main-content']/div/div[2]/div/table/tfoot/tr/th[2]")).click();
		return new ContactsPage();
	}

	public void clickOnCreateContactLink() {
		newContactLink.click();
	}
	public DealsPage clickoOnDealsLink() {
		actions = new Actions(driver);
		actions.moveToElement(homeIcon).build().perform();
		dealsLink.click();
		return new DealsPage();
	}

	public TasksPage clickoOnTasksLink() {
		actions = new Actions(driver);
		actions.moveToElement(homeIcon).build().perform();
		driver.findElement(By.xpath("//*[@id='main-content']/div/div[2]/div/table/tfoot/tr/th[2]")).click();
		tasksLink.click();
		return new TasksPage();
	}

}
