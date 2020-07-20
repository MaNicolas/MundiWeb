package com.mundijuegos.registerTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mundijuegos.base.TestUtilities;
import com.mundijuegos.pages.MundijuegosPageObject;

public class RegisterTest extends TestUtilities {

	@Test(priority = 1, enabled = true)
	public void registerTest() {
		log.info("Starting register test");
		
		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click register Button
		MundijuegosPage.clickOnRegisterButton();

		// Generate random username and email
		String username = randomName();
		String email = randomEmail();
		
		// Enter username, password email and create new account
		MundijuegosPage.registerNewAccount(username, "RegisterTestPassword", email);
		
		// Explicit wait
		//MundijuegosPage.waitForAccountName();
		MundijuegosPage.switchToWindowWithTitle("Mundijuegos");
		MundijuegosPage.waitForAccountName();

		// Verifications:
		// Check if account name is displayed
		Assert.assertTrue(MundijuegosPage.isAccountNameVisible(), "Register has failed!");
		
		// Check if account name is correct
		String expectedAccountName = username;
		String actualAccountName = MundijuegosPage.getAccountName();
		Assert.assertEquals(actualAccountName, expectedAccountName, "Incorrect account has been logged");
	}
}
