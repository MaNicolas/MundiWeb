package com.mundijuegos.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;

public class MundijuegosPageObject extends HeaderAndFooterPageObject {

	// Variables
	private String pageUrl = "https://www.mundijuegos.com/";

	// Locators
	private By videoBingoLocator = By.id("game-68-link");

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
		try {
			click(videoBingoLocator);
		} catch (ElementClickInterceptedException exception) {
			System.out.println(exception.getMessage());
			sleep(3000);
		}
		return new VideoBingoPageObject(driver, log);
	}
}