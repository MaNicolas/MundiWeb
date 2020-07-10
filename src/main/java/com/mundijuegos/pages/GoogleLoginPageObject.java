package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleLoginPageObject extends BasePageObject{

	// Variables
	private By emailLocator = By.id("identifierId");
	private By nextLocator1 = By.id("identifierNext");
	
	private By passwordLocator = By.xpath("//input[@type='password']");
	private By nextLocator2 = By.xpath("//div[@id='passwordNext']");
	
	//Constructor
	public GoogleLoginPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}

	//Methods
	
	public MundijuegosPageObject successfullLogin(String emailAddress, String password) {
		type(emailAddress, emailLocator);
		click(nextLocator1);
		waitForVisibilityOf(passwordLocator, 10);
		type(password, passwordLocator);
		click(nextLocator2);
		switchToNextWindow();
		return new MundijuegosPageObject(driver, log);
	}
}