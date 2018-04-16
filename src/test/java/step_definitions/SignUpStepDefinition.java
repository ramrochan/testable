package step_definitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.SignUpPage;


public class SignUpStepDefinition {

	public WebDriver driver;
	SignUpPage signUp;
	
	
	
	public SignUpStepDefinition(){
		driver = Hooks.driver;
		signUp= new SignUpPage(driver);
	}
	
	@Given("^I navigate to SignUp page$")
	public void navigateTo() throws Throwable {
		PageFactory.initElements(driver, signUp);
		String url="https://sso.zeachable.com/secure/136655/users/sign_up?reset_purchase_session=1";
	    signUp.goToUrl(url);
	}
	
	@When("^I enter \"([^\"]*)\" in the username field$")
	public void enterUsername(String username) throws Throwable {
		
		
		signUp.enterText(username, "username");
		
	}

	@When("^I enter \"([^\"]*)\" in the email field$")
	public void enterEmail(String email) throws Throwable {
		
		signUp.enterText(email, "email");
	}

	@When("^I enter \"([^\"]*)\" in the password field$")
	public void enterPassword(String password) throws Throwable {
		
		signUp.enterText(password, "password");
		
	}

	@When("^I enter \"([^\"]*)\" in the confirm password field$")
	public void enterConfirmPassword(String passwordConfirmation) throws Throwable {
		
		signUp.enterText(passwordConfirmation, "passwordConfirmation");
		
	}

	@When("^I submit after click giving consent to the aggrement$")
	public void submitForm() throws Throwable {
		
		signUp.submitForm();
		
	}

	@Then("^I get the following \"([^\"]*)\"$")
	public void i_get_the_following(String alert) throws Throwable {
		
		signUp.checkAlert(alert);
		
	}
}
