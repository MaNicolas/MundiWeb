package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MundijuegosPageObject extends BasePageObject{

	//Variables	
	private String pageUrl = "https://www.mundijuegos.com/";
	
	//Locators
	private By startSessionLinkLocator = By.xpath("//a[@id='header-login']");
	private By usernameLocator = By.id("userballoon-login");
	private By passwordLocator = By.id("passwordballoon-login");
	private By signInButton = By.id("submitballoon-login");
	private By accountName = By.xpath("//a[@id='header-user-nom']");
	private By dropdownButton = By.id("header-user-dropdown");
	private By logoutButton = By.xpath("//a[@href='/register/logout.php']");
	private By loginWithFacebookButton = By.xpath("//a[@href='/register/registrocontrolfb.php?canal_id=0&game_origin=&bu_lang=es']");
	private By loginWithGoogleButton = By.xpath("/html/body/div/div/div/div/ul/div/div/form/div/div");
	private By registerButton = By.id("header-register-button");
	private By registerUsername = By.id("userregister");
	private By registerPassword = By.id("passwordregister");
	private By registerEmail = By.id("emailregister");
	private By registerSex = By.xpath("(//input[@id='radioavv'])[position()=2]");
	private By registerSubmit = By.id("submitregister");
	private By acceptConditions = By.id("termsofuse");
	private By videoBingoLocator = By.xpath("(//a[@href='/multijugador/video-bingo/'])[position()=1]");
	
	//Constructor
	public MundijuegosPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//Methods
	/** Open mundijuegos.com with its URL **/
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openURL(pageUrl);
		log.info("Page opened");
	}
	
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
	
	/** Click on Video Bingo link **/
	public VideoBingoPageObject clickVideoBingoLink(){
		log.info("Click on Video Bingo link");
		click(videoBingoLocator);	
		return new VideoBingoPageObject(driver, log);
	}
}