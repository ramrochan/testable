package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class CourseIndexPage extends BaseClass{

	public CourseIndexPage(WebDriver driver) {
		super(driver);
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class); 
	}
	
	FluentWait<WebDriver> wait;
	
	@FindBy(xpath = "(.//span[@class='lecture-name'])[1]")
	private WebElement firstLesson;
	
	
	
	
	public void clickLesson(String whichLesson){
		wait.until(ExpectedConditions.visibilityOf(firstLesson));
		switch(whichLesson){
		case("first"):
			firstLesson.click();
			break;
		case("second"):
			//secondLesson.click();
			break;
		case("third"):
			//thirdLesson.click();
			break;
		case("fourth"):
			//fourthLesson.click();
			break;
		}
	}
}
