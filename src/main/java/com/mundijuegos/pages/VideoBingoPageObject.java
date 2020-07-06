package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VideoBingoPageObject extends BasePageObject {

	// Locators
	private By videoBingoButton = By.xpath("//a[@href='/play/video-bingo/']");
	
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