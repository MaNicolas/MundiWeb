package com.mundijuegos.pages;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
	
	// Variables
	protected WebDriver driver;
	protected Logger log;

	// Constructor
	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// Methods
	/** Open page with given URL **/
	protected void openURL(String url) {
		driver.get(url);
	}

	/** Find element using given locator **/
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	/** Click on element with given locator when it's visible **/
	protected void click(By locator) {
		waitForVisibilityOf(locator, 30);
		find(locator).click();
	}
	
	/** Type given text into element with given locator **/
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).sendKeys(text);
	}
	
	/** Get URL of the current page from browser **/
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	/** Get title of current page **/
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}
	
	/** Get source of current page **/
	public String getCurrentPageSource() {
		return driver.getPageSource();
	}

	/** Switch the driver to another page */
	public void switchToWindowWithTitle(String expectedTitle) {
		String firstWindow = driver.getWindowHandle();
		log.info("First window's title is " + firstWindow);
		
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> windowsIterator = allWindows.iterator();
		
		while (windowsIterator.hasNext())
		{
			String nextWindowHandle = windowsIterator.next().toString();
			
			if (!nextWindowHandle.equals(firstWindow))
			{
				driver.switchTo().window(nextWindowHandle);
				if (getCurrentPageTitle().equals(expectedTitle))
					break;
			}
		}
	}
	
	/** Switch the driver to the next page */
	public void switchToNextWindow() {		
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> windowsIterator = allWindows.iterator();
		
		String nextWindowHandle = windowsIterator.next().toString();
		driver.switchTo().window(nextWindowHandle);
	}
	
	/** Switch the driver to an iframe */
	public void switchToIframe(By frameLocator) {
		driver.switchTo().frame(find(frameLocator));
	}
	
	/** Check all checkbox **/
	public void clickAllCheckboxes(By locator) {
		log.info("Executing click on " + locator.toString());
		WebElement checkbox = find(locator);
			if (!checkbox.isSelected())
				checkbox.click();
	}
	
	/** Sleep **/
	protected void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////
	
	
	/** Wait for specific ExpectedCondition for the given amount of time in seconds **/
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/** Wait for given number of seconds for element with given locator to be visible on the page **/
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	
	/** Wait for Url to contain a certain string **/
	protected void waitForUrlToContain(String url, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.urlContains(url));
	}
}
