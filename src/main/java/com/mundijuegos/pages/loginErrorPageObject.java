package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginErrorPageObject extends HeaderAndFooterPageObject {

	// Locators
	private By errorMessage = By.xpath("//h1[text()='Usuario o contraseña incorrectos...']");
	
	// Constructor
	public loginErrorPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Methods
	/** Wait for error message after login **/
	public void waitForErrorMessage() {
		waitForVisibilityOf(errorMessage, 30);
	}
	
	/** Verification if account name is displayed **/
	public boolean isErrorMessageVisible() {
		return find(errorMessage).isDisplayed();
	}
}