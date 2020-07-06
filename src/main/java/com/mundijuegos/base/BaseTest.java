package com.mundijuegos.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mundijuegos.pages.MundijuegosPageObject;

public class BaseTest {
	
	// Variables
	protected WebDriver driver;
	protected Logger log;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void Setup(@Optional("chrome") String browser, ITestContext ctx) {

		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);

		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
		driver = factory.createDriver();

		// Maximize browser window
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		// Close browser
		driver.quit();
		log.info("======TEST COMPLETE======");
	}
	
	/** Login before test **/
	public MundijuegosPageObject login(String username, String password) {
		
		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click StartSession Button
		MundijuegosPage.clickLoginButton();

		// Enter username, password and click Sign in
		MundijuegosPage.successfullLogin(username, password);

		// Explicit wait
		MundijuegosPage.waitForAccountName();

		return new MundijuegosPageObject(driver, log);
	}
}