package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class LogoutPageObject extends HeaderAndFooterPageObject {

	// Variables
	private String pageUrl = "https://www.mundijuegos.com/register/logout2.php";
	
	//Constructor
	public LogoutPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//Methods
	/** Returns log out page url **/
	public String getExpectedUrl() {
		return pageUrl;
	}
}
