package pageobjects;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LectureSlidesPage extends BaseClass {


	public LectureSlidesPage(WebDriver driver) {
		super(driver);
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class); 
	}
	
	FluentWait<WebDriver> wait;
	
	@FindBy(id = "lecture_complete_button")
	private WebElement completeAndContinue;
	
	@FindBy(xpath = ".//span[@class='percentage']")
	private WebElement getPercent;
	
	@FindBy(xpath = ".//h2[@data-previous-lecture-id='2862496']")
	private WebElement video;
	
	@FindBy(xpath = ".//h2[@data-lecture-id='2862498']")
	private WebElement lectureWithPdf;
	
	@FindBy(xpath = ".//h2[@data-lecture-id='2862499']")
	private WebElement lectureWithText;
	
	public void clickComplete(){
		completeAndContinue.click();
	}
	
	public void checkForPercent(){
		Assert.assertEquals(completeAndContinue.getText().trim(), "Complete and continue");
	}
	
	public void checkVideoText(){
		wait.until(ExpectedConditions.visibilityOf(video));
		Assert.assertEquals(video.getText().trim(), "Lecture with video");
	}
	
	public void checkPdfLecture(){
		wait.until(ExpectedConditions.visibilityOf(lectureWithPdf));
		Assert.assertEquals(lectureWithPdf.getText().trim(), "Lecture with pdf");
	}
	
	public void checkTextLecture(){
		wait.until(ExpectedConditions.visibilityOf(lectureWithText));
		Assert.assertEquals(lectureWithText.getText().trim(), "Lecture with text");
	}

	public void waitFor5Sec() {
		wait.withTimeout(7, TimeUnit.SECONDS);
		
	}
	
}
