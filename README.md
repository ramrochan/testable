# Takehome for QA engineer position. 


Testing Framework Used: Selenium Webdriver , Cucumber

Design Patter: Page object Model

Language: Java

The best way to test is to run the tests in Browserstack using the following command

mvn test "-Dconfig=browserstack_chrome61"

BrowserStack LogIn Info

username: ramrochanmts+test1@gmail.com
password: Test123

I have tests run there previously, you can take a look at that as well. This account is a trial account. If the tests need to be run in a different environment you can create a new file with necessary desired capabilities in the browserConfigs folder and give the arguments in the command. It should follow the same name and format as given in the example. Example for the name: browserstack_firefox58



If you have your machine setup for running Java projects(Maven and Java) then you can run using the following command.  It can be run in different browser by changing the arguments(to firefox).

mvn test "-Dbrowser=chrome"

Assumptions: All the webdrivers are added to the system path.


