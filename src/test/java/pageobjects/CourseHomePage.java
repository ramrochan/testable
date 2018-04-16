package pageobjects;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.asserts.SoftAssert;


public class CourseHomePage extends BaseClass{
	
	public CourseHomePage(WebDriver driver) {
		super(driver);
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class); 
		softly = new SoftAssert();
	}
	
	FluentWait<WebDriver> wait;
	SoftAssert softly;
	
	@FindBy(xpath = ".//span[@aria-hidden='true']")
	private WebElement discardLinking;
	
	@FindBy(xpath = ".//div[@class='course-listing-title']")
	private WebElement course;
	

	public void verfiyUrl() {
		wait.withTimeout(5, TimeUnit.SECONDS);
		String expected=driver.getCurrentUrl();
		softly.assertEquals(expected, "http://takehome.zeachable.com/");
		
		
	}
	
	public void closePopup(){
		wait.until(ExpectedConditions.visibilityOf(discardLinking));
		discardLinking.click();
	}
	
	public void startCourse(){
		course.click();
	}
	
}
