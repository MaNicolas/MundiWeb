package com.mundijuegos.loginWithFacebook;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mundijuegos.base.TestUtilities;
import com.mundijuegos.pages.FacebookLoginPageObject;
import com.mundijuegos.pages.MundijuegosPageObject;

public class LoginWithFacebookTest extends TestUtilities {

	@Parameters({ "username", "password" })
	@Test(priority = 1, enabled = true, groups = { "positiveTests" })
	public void positiveFacebookLoginTest(@Optional("nicotangelo1@gmail.com") String emailAddress, @Optional("Baator1985") String password) {
		log.info("Starting positive loginTest with Facebook");

		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click StartSession Button
		MundijuegosPage.clickLoginButton();

		// Find and switch to Facebook login page
		FacebookLoginPageObject FacebookloginPage = MundijuegosPage.loginWithFacebook();
		
		// Enter username, password and click Sign in
		MundijuegosPage = FacebookloginPage.successfullLogin(emailAddress, password);

		// Explicit wait
		MundijuegosPage.waitForAccountName();

		// Verifications:
		// Check if account name is displayed
		Assert.assertTrue(MundijuegosPage.isAccountNameVisible());

		// Check if account name is correct
		String expectedAccountName = "Nicotest";
		String actualAccountName = MundijuegosPage.getAccountName();
		Assert.assertEquals(actualAccountName, expectedAccountName, "Incorrect account has been logged");		
	}
	
	@Parameters({ "username", "password" })
	@Test(priority = 2, enabled = false, groups = { "negativeTests" })
	public void negativeFacebookLoginTest(@Optional("nicotangelo1@gmail.com") String emailAddress, @Optional("Baator198") String password) {
		log.info("Starting negative loginTest with Facebook");

		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click StartSession Button
		MundijuegosPage.clickLoginButton();

		// Find and switch to Facebook login page
		FacebookLoginPageObject FacebookloginPage = MundijuegosPage.loginWithFacebook();
		
		// Enter username, incorrect password and click Sign in
		FacebookloginPage.failLogin(emailAddress, password);

		// Explicit wait
		FacebookloginPage.waitForErrorMessage();
				
		// Verifications:
		// Check if error message is displayed
		Assert.assertTrue(FacebookloginPage.isErrorMessageVisible());
	}
}
