package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class VideoBingoGamePageObject extends BasePageObject {

	// Variables
	private String url = "https://www.mundijuegos.com/play/video-bingo/";
	
	// Constructor
	public VideoBingoGamePageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Methods
	/** Start Video Bingo **/
	public String getExpectedUrl() {
		log.info("Expected url is " + url);
		return url;
	}
	
	/** Wait for url **/
	public void waitforUrl() {
		waitForUrlToContain("play/video-bingo/", 30);
	}
}