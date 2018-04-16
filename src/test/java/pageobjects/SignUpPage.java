package pageobjects;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class SignUpPage extends BaseClass{
	
	public SignUpPage(WebDriver driver) {
		super(driver);
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class); 
	}
	
	FluentWait<WebDriver> wait;

	@FindBy(xpath = "//input[@id='user_name']")
	private WebElement userName;
	
	@FindBy(id = "user_email")
	private WebElement userEmail;
	
	@FindBy(id = "user_password")
	private WebElement userPassword;
	
	@FindBy(id = "user_password_confirmation")
	private WebElement passwordConfirmation;
	
	@FindBy(id = "user_agreed_to_terms")
	private WebElement terms;
	
	@FindBy(name = "commit")
	private WebElement submitButton;
	
	@FindBy(xpath = ".//p[text()='Oops! Please fix the following:']")
	private WebElement alertMessage;
	
	@FindBy(xpath = ".//li[text()=\"Name is required\"]")
	private WebElement alertUserName;
	
	@FindBy(xpath = ".//li[text()=\"Email is required\"]")
	private WebElement alertEmail;
	
	@FindBy(xpath = ".//li[text()=\"Password is required\"]")
	private WebElement alertPassword;
	
	@FindBy(xpath = ".//li[text()=\"Password confirmation doesn't match Password\"]")
	private WebElement passwordConfAlert;
	
	public void enterText(String text, String type){
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		switch(type){
		case "username":
			userName.clear();
			userName.sendKeys(text);
		case "email":
			userEmail.clear();
			userEmail.sendKeys(text);
		case "password":
			userPassword.clear();
			userPassword.sendKeys(text);
		case "passwordConfirmation":
			passwordConfirmation.clear();
			passwordConfirmation.sendKeys(text);
		}
	}
	
	public void submitForm(){
		terms.click();
		submitButton.click();
	}

	
	public void checkAlert(String alert) throws InterruptedException {
		String expectedAlert=alertMessage.getText();
		String nextAlert;
		switch(alert){
		
		case "Email is required":
			wait.until(ExpectedConditions.visibilityOf(alertEmail));
			nextAlert=alertEmail.getText();
			Assert.assertEquals(nextAlert, alert);
			break;
		case "Name is required":
			wait.until(ExpectedConditions.visibilityOf(alertUserName));
			nextAlert=alertUserName.getText();
			Assert.assertEquals(nextAlert, alert);
			break;
		case "Password is required":
			wait.until(ExpectedConditions.visibilityOf(alertPassword));
			nextAlert=alertPassword.getText();
			Assert.assertEquals(nextAlert, alert);
			break;
		case "Password confirmation doesn't match Password":
			wait.until(ExpectedConditions.visibilityOf(passwordConfAlert));
			nextAlert=passwordConfAlert.getText();
			Assert.assertEquals(nextAlert, alert);
			break;
		}
		Thread.sleep(1000);
		Assert.assertEquals(expectedAlert, "Oops! Please fix the following:");
		
		
	}

	public void goToUrl(String url) {
		
		driver.get(url);
		
	}
	
	
}

