package com.mundijuegos.loginTest;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mundijuegos.base.TestUtilities;
import com.mundijuegos.pages.MundijuegosPageObject;
import com.mundijuegos.pages.loginErrorPageObject;

public class LoginTests extends TestUtilities {

	@Parameters({ "username", "password" })
	@Test(priority = 1, enabled = true, groups = { "positiveTests" })
	public void positiveLoginTest(@Optional("Nicoroots") String username, @Optional("Baator1985") String password) {
		log.info("Starting positive loginTest");

		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click StartSession Button
		MundijuegosPage.clickLoginButton();

		// Enter username, password and click Sign in
		MundijuegosPage.successfullLogin(username, password);

		// Explicit wait
		MundijuegosPage.waitForAccountName();

		// Verifications:
		// Check if account name is displayed
		Assert.assertTrue(MundijuegosPage.isAccountNameVisible());

		// Check if account name is correct
		String expectedAccountName = username;
		String actualAccountName = MundijuegosPage.getAccountName();
		Assert.assertEquals(actualAccountName, expectedAccountName, "Incorrect account has been logged");
	}

	@Parameters({ "username", "password" })
	@Test(priority = 2, enabled = true, groups = { "negativeTests" })
	public void negativeLoginTests(@Optional("Nicoroots") String username, @Optional("Baator198") String password) {
		log.info("Starting negative loginTest");

		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click StartSession Button
		MundijuegosPage.clickLoginButton();

		// Enter username, password and click Sign in
		loginErrorPageObject logingErrorPage = MundijuegosPage.failLogin(username, password);

		// Explicit wait
		logingErrorPage.waitForErrorMessage();
		
		// Verifications:
		// Check if error message is displayed
		Assert.assertTrue(logingErrorPage.isErrorMessageVisible());
		
		// Check URL
		String expectedURL = "https://www.mundijuegos.com/register/login.php?error";
		String actualURL = logingErrorPage.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL, "URL is incorrect");
	}
}