package step_definitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.CourseIndexPage;
import pageobjects.CourseHomePage;
import pageobjects.HomePage;
import pageobjects.LectureSlidesPage;
import pageobjects.LogInPage;

public class TakeCourseStepDefinition {
	public WebDriver driver;
	LogInPage logIn;
	HomePage homePage;
	CourseHomePage coursePage;
	CourseIndexPage courseIndexPage;
	LectureSlidesPage lectureSlidesPage;
	
	
	public TakeCourseStepDefinition(){
		driver = Hooks.driver;
		logIn= new LogInPage(driver);
		homePage= new HomePage(driver);
		coursePage= new CourseHomePage(driver);
		courseIndexPage= new CourseIndexPage(driver);
		lectureSlidesPage = new LectureSlidesPage(driver);
	}
	
	@Given("^I login with valid credentials$")
	public void i_login_with_valid_credentials() throws Throwable {
		PageFactory.initElements(driver, logIn);
		PageFactory.initElements(driver, homePage);
		PageFactory.initElements(driver, coursePage);
		PageFactory.initElements(driver, courseIndexPage);
		PageFactory.initElements(driver, lectureSlidesPage);
	    driver.get("https://sso.zeachable.com/secure/136655/users/sign_in?clean_login=true&reset_purchase_session=1");
	    logIn.logInwithCredentials();
	    
	    
	}

	@When("^I dicard the linking process$")
	public void i_dicard_the_linking_process() throws Throwable {
		coursePage.closePopup();
	}

	@When("^it lands in the course page$")
	public void it_lands_in_the_course_page() throws Throwable {
	    coursePage.verfiyUrl();
	}

	@When("^I click on the course to start course$")
	public void i_click_on_the_course_to_start_course() throws Throwable {
	    coursePage.startCourse();
	}

	@Then("^I click on course with text to start that lecture$")
	public void i_click_on_course_with_text_to_start_that_lecture() throws Throwable {
	    courseIndexPage.clickLesson("first");
	}

	@Then("^I click complete and continue$")
	public void i_click_complete_and_continue() throws Throwable {
	    lectureSlidesPage.clickComplete();
	}

	@Then("^it should have (\\d+)% complete$")
	public void it_should_have_complete(int arg1) throws Throwable {
	    lectureSlidesPage.checkForPercent();
	}

	@Then("^it should land on next video lecture$")
	public void it_should_land_on_next_video_lecture() throws Throwable {
	   lectureSlidesPage.checkVideoText();
	}

	@Then("^I wait for (\\d+) seconds to complete the video$")
	public void i_wait_for_seconds_to_complete_the_video(int arg1) throws Throwable {
		lectureSlidesPage.waitFor5Sec();
	}

	@Then("^it should land on next lecture with pdf$")
	public void it_should_land_on_next_lecture_with_pdf() throws Throwable {
	    lectureSlidesPage.checkPdfLecture();
	}

	@Then("^it should land on next lecture with text$")
	public void it_should_land_on_next_lecture_with_text() throws Throwable {
		lectureSlidesPage.checkTextLecture();
	}
}
