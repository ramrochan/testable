package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

public class HomePage extends BaseClass{

	public HomePage(WebDriver driver) {
		super(driver);
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class); 
	}
	
	FluentWait<WebDriver> wait;
	@FindBy(xpath = ".//a[@class='navbar-link fedora-navbar-link']")
	private WebElement logInButton;
	
	public void clickLogIn(){
		logInButton.click();
	}
	
}
