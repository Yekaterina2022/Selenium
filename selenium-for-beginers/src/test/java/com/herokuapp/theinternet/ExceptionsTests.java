package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionsTests {
	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) {
//		Create driver
		switch (browser) {
		case "chrome":

			driver = new ChromeDriver();
			break;

		case "firefox":
			
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Do not know how to start " + browser + ", starting chrome instead");
			
			driver = new ChromeDriver();
			break;
		}
		
		// Move browser window to the left monitor
		driver.manage().window().setPosition(new Point(-1000,200));

		// maximize browser window
		driver.manage().window().maximize();
	}
	@Test
	public void noSuchElementExceptionTest() {
		// Test case 1: NoSuchElementException
		//Open page	 
	driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		//Click Add button
	WebElement addButtonElement = driver.findElement(By.id("add_btn"));
	addButtonElement.click();
	
	
	//Explicit wait
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='row2']/input")));
    
		//Verify Row 2 input field is displayed
WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input"));
Assert.assertTrue(row2Input.isDisplayed(), "Row 2 is not displayed");
		
	
}
	
	@Test
	public void elementNotInteractableException() {
		//Test case 2: elementNotInteractableException
		
		//Open page
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		//Click Add button
		WebElement addButtonElement = driver.findElement(By.id("add_btn"));
		addButtonElement.click();
		
		
		//Wait for the second row to load
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement row2input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
	    
		
		//Type text into the second input field
		row2input.sendKeys("Sushi");
		
		//Push Save button using locator By.name(“Save”)
		WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@name='Save']"));
		saveButton.click();
		//Verify text saved
		WebElement confirmationMessage = driver.findElement(By.xpath("//div[@id='confirmation']"));
		String messageText = confirmationMessage.getText();
		Assert.assertEquals(messageText, "Row 2 was saved", "Confirmation message text is not expected");
	}
	
	
	
	


private void implicitlyWait(Duration ofSeconds) {
		// TODO Auto-generated method stub
		
	}
@AfterMethod(alwaysRun = true)
private void tearDown() {
	// Close browser
	driver.quit();
}
}