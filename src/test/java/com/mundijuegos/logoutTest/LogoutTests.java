package com.mundijuegos.logoutTest;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mundijuegos.base.TestUtilities;
import com.mundijuegos.pages.LogoutPageObject;
import com.mundijuegos.pages.MundijuegosPageObject;

public class LogoutTests extends TestUtilities{

	@Parameters({ "username", "password" })
	@Test(priority = 1, enabled = true)
	public void logoutTest(@Optional("Nicoroots") String username, @Optional("Baator1985") String password) {
		log.info("Starting logoutTest");

		// Open mundijuegos page
		MundijuegosPageObject MundijuegosPage = new MundijuegosPageObject(driver, log);
		MundijuegosPage.openPage();

		// Click StartSession Button
		MundijuegosPage.clickLoginButton();

		// Enter username, password and click Sign in
		MundijuegosPage.successfullLogin(username, password);

		// Explicit wait
		MundijuegosPage.waitForAccountName();
		
		// Logout
		LogoutPageObject logoutPage = MundijuegosPage.logout();

		// Verifications:
		// Check if Url is correct
		String expectedUrl = "https://www.mundijuegos.com/register/logout2.php";
		String actualUrl = logoutPage.getPageUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Logout has failed");
	}
}