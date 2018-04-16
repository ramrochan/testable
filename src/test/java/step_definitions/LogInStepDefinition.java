package step_definitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.CourseHomePage;
import pageobjects.HomePage;
import pageobjects.LogInPage;


public class LogInStepDefinition {
	public WebDriver driver;
	LogInPage logIn;
	HomePage homePage;
	CourseHomePage coursePage;
	
	
	
	public LogInStepDefinition(){
		driver = Hooks.driver;
		logIn= new LogInPage(driver);
		homePage= new HomePage(driver);
		coursePage= new CourseHomePage(driver);
	}
	
	@Given("^I navigate to Course home page$")
	public void navigateCourseHomePage() throws Throwable {
		PageFactory.initElements(driver, logIn);
		PageFactory.initElements(driver, homePage);
		PageFactory.initElements(driver, coursePage);
	    driver.get("http://takehome.zeachable.com/");
	}

	@When("^I click the login button$")
	public void clickLoginButton() throws Throwable {
	   homePage.clickLogIn();
	}

	@When("^it navigates to LogIn page$")
	public void navigatesLogInPage() throws Throwable {
	    logIn.checkForButton();
	}

	@When("^I click Log In after entering valid info$")
	public void logIn() throws Throwable {
	   logIn.logInwithCredentials();
	   
	   
	}

	@Then("^I should land in the Course home page with Take home test course$")
	public void verificationStep() throws Throwable {
		coursePage.closePopup();
		coursePage.verfiyUrl();
	}
}
