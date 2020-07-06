package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookLoginPageObject extends BasePageObject{

	// Variables
	private By emailLocator = By.id("email");
	private By passwordLocator = By.id("pass");
	private By loginButton = By.id("loginbutton");
	private By errorBox = By.id("error_box");
	
	//Constructor
	public FacebookLoginPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//Methods
	/** Fill in username, password and then click sign in button **/
	public MundijuegosPageObject successfullLogin(String username, String password) {
		log.info("Executing login on facebook with username [" + username + "] and password [" + password + "]." );
		type(username, emailLocator);
		type(password, passwordLocator);
		click(loginButton);
		switchToNextWindow();
		return new MundijuegosPageObject(driver, log);
	}
	
	/** Fill in username, incorrect password and then click sign in button **/
	public void failLogin(String username, String password) {
		log.info("Executing login on facebook with username [" + username + "] and password [" + password + "]." );
		type(username, emailLocator);
		type(password, passwordLocator);
		click(loginButton);
	}
	
	/** Wait for visibility of error message after login **/
	public void waitForErrorMessage() {
		waitForVisibilityOf(errorBox, 30);
	}
	
	/** Verification if error message is displayed **/
	public boolean isErrorMessageVisible() {
		return find(errorBox).isDisplayed();
	}
}
