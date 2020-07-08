package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MundijuegosPageObject extends HeaderAndFooterPageObject {

	// Variables
	private String pageUrl = "https://www.mundijuegos.com/";

	// Locators
	private By videoBingoLocator = By.xpath("(//a[@href='/multijugador/video-bingo/'])[position()=2]");

	// Constructor
	public MundijuegosPageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Methods
	/** Open mundijuegos.com with its URL **/
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openURL(pageUrl);
		log.info("Page opened");
	}

	/** Click on Video Bingo link **/
	public VideoBingoPageObject clickVideoBingoLink() {
		log.info("Click on Video Bingo link");
		click(videoBingoLocator);
		return new VideoBingoPageObject(driver, log);
	}
}