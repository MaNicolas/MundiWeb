package com.mundijuegos.loginWithGoogle;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mundijuegos.base.TestUtilities;
import com.mundijuegos.pages.GoogleLoginPageObject;
import com.mundijuegos.pages.MundijuegosPageObject;

public class LoginWithGoogleTests extends TestUtilities{

	@Parameters({ "username", "password" })
	@Test(priority = 1, enabled = true, groups = { "positiveTests" })
	public void positiveGoogleLoginTest(@Optional("nicotangelo1@gmail.com") String emailAddress, @Optional("Baator1985") String password) {
		log.info("Starting positive loginTest with Google");

		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click StartSession Button
		MundijuegosPage.clickLoginButton();

		// Find and switch to Google login page
		GoogleLoginPageObject GoogleLoginPage = MundijuegosPage.loginWithGoogle();
		
		// Enter email, password and click next
		MundijuegosPage = GoogleLoginPage.successfullLogin(emailAddress, password);

		// Explicit wait
		MundijuegosPage.waitForAccountName();

		// Verifications:
		// Check if account name is displayed
		Assert.assertTrue(MundijuegosPage.isAccountNameVisible(), "Connection has failed");

		// Check if account name is correct
		String expectedAccountName = "Nicotangelo1";
		String actualAccountName = MundijuegosPage.getAccountName();
		Assert.assertEquals(actualAccountName, expectedAccountName, "Incorrect account has been logged");		
	}

}
