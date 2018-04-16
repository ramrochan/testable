Feature: Log In to see the welcome message

Scenario: Login with valid info 
	Given I navigate to Course home page
	When I click the login button
	And it navigates to LogIn page
	When I click Log In after entering valid info
	Then I should land in the Course home page with Take home test course