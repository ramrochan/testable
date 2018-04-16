Feature: Complete the course

Scenario: Complete the course 
	Given I login with valid credentials
	When I dicard the linking process
	And it lands in the course page
	When I click on the course to start course
	Then I click on course with text to start that lecture
	And I click complete and continue
	And it should have 25% complete
	Then it should land on next video lecture
	And I wait for 5 seconds to complete the video
	And I click complete and continue
	And it should have 50% complete
	Then it should land on next lecture with pdf
	And I click complete and continue
	And it should have 75% complete
	Then it should land on next lecture with text
	And I click complete and continue
	And it should have 100% complete 
	