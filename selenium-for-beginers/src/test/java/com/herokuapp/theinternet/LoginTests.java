package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
	private WebDriver driver ;
	
	@BeforeMethod (alwaysRun = true)
	private void setUp() {
		
		// Create driver
				driver = new ChromeDriver();
				System.out.println("Browser Started");

				// open test page
				String url = "https://the-internet.herokuapp.com/login";
				driver.get(url);

				driver.manage().window().maximize();

				System.out.println("Page is opened");

		
	}
	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void positiveloginTest() {
		

	// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);

		driver.manage().window().maximize();

		System.out.println("Page is opened");

		// enter user name
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		// click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();
		// verifications:
		// new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
		
		// logout button is visible
		WebElement logOut = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOut.isDisplayed(), "Log Out button is not visible");
		
		
		// successful login message
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
		//Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contains expected. \nActual Message: "+ actualMessage 
				+ "\nExpected Message: " + expectedMessage);
		
	}	
		
	
	
		@Parameters({ "username", "password", "expectedMessage" })
		@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
		public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
			System.out.println("Starting negativeLoginTests with " + username + " and " + password);
			
			// open test page
			String url = "https://the-internet.herokuapp.com/login";
			driver.get(url);

			// enter user name
			WebElement usernameElement = driver.findElement(By.id("username"));
			usernameElement.sendKeys(username);

			// enter password
			WebElement passwordElement = driver.findElement(By.name("password"));
			passwordElement.sendKeys(password);
			// click login button
			WebElement logInButton = driver.findElement(By.tagName("button"));
			logInButton.click();
			
			//verifications
			WebElement errorMessage = driver.findElement(By.id("flash"));
			
			String actualErrorMessage = errorMessage.getText();
			
			Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
			"Actual error message does not contain expected.  \nActual: " 
			+ actualErrorMessage + " \nExpected: " 
			+ expectedErrorMessage);
			
		
	}
	

			
		@AfterMethod (alwaysRun = true)
		private void tearDown() {
			//close browser
			driver.quit();
		}
		}
	


	


