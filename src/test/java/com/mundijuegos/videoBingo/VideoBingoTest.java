package com.mundijuegos.videoBingo;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mundijuegos.base.TestUtilities;
import com.mundijuegos.pages.MundijuegosPageObject;
import com.mundijuegos.pages.VideoBingoGamePageObject;
import com.mundijuegos.pages.VideoBingoPageObject;

public class VideoBingoTest extends TestUtilities {

	@Parameters({ "username", "password" })
	@Test(priority = 1, enabled = true)
	public void launchVideoBingo(@Optional("Nicoroots") String username, @Optional("Baator1985") String password) {

		// Login		
		MundijuegosPageObject MundijuegosPage = this.login(username, password);
		
		sleep(5000);
		// Click on Video Bingo link
		VideoBingoPageObject VideoBingoPage = MundijuegosPage.clickVideoBingoLink();
		
		// Start Video Bingo
		VideoBingoGamePageObject VideoBingoGamePage = VideoBingoPage.startVideoBingo();
		
		// Explicit wait
		VideoBingoGamePage.waitforUrl();
		
		// Verifications
		Assert.assertEquals(
				VideoBingoGamePage.getCurrentUrl(),
				VideoBingoGamePage.getExpectedUrl(),
				"Video Bingo has failed to be launched!"
				);
	}
}