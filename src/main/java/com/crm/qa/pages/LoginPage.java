package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	
	// Page Factory or Page Objects
	
	@FindBy(xpath = "//*[@class ='rd-navbar-nav']/a")
	WebElement loginBtn;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement username;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//div[@class='ui fluid large blue submit button']")
	WebElement signinBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Sign Up']")
	WebElement signUpBtn;

	@FindBy(xpath = ("//span[@class='brand-slogan'])[1]"))
	WebElement crmLogo;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	public boolean validateCRMLogo() {
		return crmLogo.isDisplayed();
	}
	public HomePage login(String un, String pwd) {
		loginBtn.click();
		username.sendKeys(un);
		password.sendKeys(pwd);
		signinBtn.click();
		
		return new HomePage();
	}
	public HomePage validateLoginButton() {
		loginBtn.click();
		return new HomePage();
	}
}
