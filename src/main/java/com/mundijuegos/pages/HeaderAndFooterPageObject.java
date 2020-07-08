package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderAndFooterPageObject extends BasePageObject {
	
	// Locators
	protected By startSessionLinkLocator = By.xpath("//a[@id='header-login']");
	protected By usernameLocator = By.id("userballoon-login");
	protected By passwordLocator = By.id("passwordballoon-login");
	protected By signInButton = By.id("submitballoon-login");
	protected By accountName = By.xpath("//a[@id='header-user-nom']");
	protected By dropdownButton = By.id("header-user-dropdown");
	protected By logoutButton = By.xpath("//a[@href='/register/logout.php']");
	protected By loginWithFacebookButton = By.xpath("//a[@href='/register/registrocontrolfb.php?canal_id=0&game_origin=&bu_lang=es']");
	protected By loginWithGoogleButton = By.xpath("/html/body/div/div/div/div/ul/div/div/form/div/div");
	protected By registerButton = By.id("header-register-button");
	protected By registerUsername = By.id("userregister");
	protected By registerPassword = By.id("passwordregister");
	protected By registerEmail = By.id("emailregister");
	protected By registerSex = By.xpath("(//input[@id='radioavv'])[position()=2]");
	protected By registerSubmit = By.id("submitregister");
	protected By acceptConditions = By.id("termsofuse");

	// Constructor
	public HeaderAndFooterPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//Methods	
	/** Click on Login button **/
	public void clickLoginButton() {
		log.info("Clicking on login button");
		click(startSessionLinkLocator);
	}

	/** Fill in username, password and then click sign in button **/
	public void successfullLogin(String username, String password) {
		log.info("Executing login with username [" + username + "] and password [" + password + "]." );
		type(username, usernameLocator);
		type(password, passwordLocator);
		click(signInButton);
	}
	
	/** Fill in username, incorrect password and then click sign in button **/
	public loginErrorPageObject failLogin(String username, String password) {
		log.info("Executing login with username [" + username + "] and password [" + password + "]." );
		type(username, usernameLocator);
		type(password, passwordLocator);
		click(signInButton);
		return new loginErrorPageObject(driver, log);
	}
	
	/** Wait for visibility of account name after login **/
	public void waitForAccountName() {
		waitForVisibilityOf(accountName, 30);
	}
	
	/** Verification if account name is displayed **/
	public boolean isAccountNameVisible() {
		return find(accountName).isDisplayed();
	}
	
	/** Returns account name displayed **/
	public String getAccountName() {
		return find(accountName).getText();
	}
	
	/** Log out **/
	public LogoutPageObject logout() {
		click(dropdownButton);
		waitForVisibilityOf(logoutButton, 10);
		click(logoutButton);
		return new LogoutPageObject(driver, log);
	}
	
	/** Click on Login with Facebook button **/
	public FacebookLoginPageObject loginWithFacebook() {
		click(loginWithFacebookButton);
		switchToWindowWithTitle("Facebook");
		return new FacebookLoginPageObject(driver, log);
	}
	
	/** Click on Login with Google button **/
	public GoogleLoginPageObject loginWithGoogle() {
		click(loginWithGoogleButton);
		switchToWindowWithTitle("Connexion: comptes Google");
		return new GoogleLoginPageObject(driver, log);
	}
	
	/** Click on Register button **/
	public void clickOnRegisterButton() {
		log.info("Clicking on register button");
		click(registerButton);		
	}
	
	/** Register with new account **/
	public void registerNewAccount(String username, String password, String email) {
		log.info("Creating new account");
		type(username, registerUsername);
		type(password, registerPassword);
		type(email, registerEmail);
		click(acceptConditions);
		click(registerSex);
		click(registerSubmit);
	}
}
