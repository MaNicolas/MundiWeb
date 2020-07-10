package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VideoBingoPageObject extends HeaderAndFooterPageObject {

	// Locators
	private By videoBingoButton = By.id("game-68-btn");
	
	// Constructor
	public VideoBingoPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Methods
	/** Start Video Bingo **/
	public VideoBingoGamePageObject startVideoBingo() {
		log.info("Click on video bingo start button");
		click(videoBingoButton);
		switchToWindowWithTitle("Video Bingo: Mundijuegos");
		return new VideoBingoGamePageObject(driver, log);
	}
}