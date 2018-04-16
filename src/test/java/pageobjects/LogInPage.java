package pageobjects;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;



public class LogInPage extends BaseClass{

	public LogInPage(WebDriver driver) {
		super(driver);
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class); 
	}
	
	FluentWait<WebDriver> wait;
	
	@FindBy(xpath = ".//a[@class='link-below-button']")
	private WebElement forgotPassword;
	
	@FindBy(xpath = ".//input[@type='email']")
	private WebElement emailField;
	
	@FindBy(xpath = ".//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath = ".//input[@type='submit']")
	private WebElement logInButton;
	
	public void checkForButton(){
		wait.until(ExpectedConditions.visibilityOf(forgotPassword));
		String expected= forgotPassword.getText();
		Assert.assertEquals(expected, "Forgot Password?");
	}
	
	public void logInwithCredentials(){
		wait.until(ExpectedConditions.visibilityOf(logInButton));
		emailField.clear();
		emailField.sendKeys("test123@gmail.com");
		passwordField.clear();
		passwordField.sendKeys("test123");
		logInButton.click();
	}
}
