package com.mundijuegos.base;

import org.apache.commons.lang3.RandomStringUtils;

public class TestUtilities extends BaseTest {

	/** Put the driver to sleep so user can see what happens - Do not use it to wait **/
	protected void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** Generates and return a random name of length 6 composed of alphabetic characters**/
	protected String randomName() {
		String randomName = "0random" + RandomStringUtils.randomAlphanumeric(8).toLowerCase();
		return randomName;
	}

	/** Generates and return a random email of length 8 composed of alphabetic characters **/
	protected String randomEmail() {
		String randomEmail = RandomStringUtils.randomAlphanumeric(8).toLowerCase() + "@gmail.com";
		return randomEmail;
	}
}
