Feature: Sign up to create a new account 

Scenario Outline: Sign up with invalid info 
	Given I navigate to SignUp page
	When I enter <username> in the username field
	When I enter <email> in the email field
	When I enter <password> in the password field
	When I enter <confirm password> in the confirm password field
	And I submit after click giving consent to the aggrement 
	Then I get the following <error>
	
	
	Examples: 
	
		| username | email | password | confirm password | error |
		|  " "  | "test@gmail.com" | "test123" |  "test123" | "Name is required" |
		| "Test" | " " | "test123" | "test123" | "Email is required" |
		| "Test" | "test@gmail.com" | " " | "test123" | "Password is required" |
		| "Test" | "test@gmail.com" | "test123" | " " | "Password confirmation doesn't match Password" |
