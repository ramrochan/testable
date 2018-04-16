package baseFramework;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverUtil {
    public static long DEFAULT_WAIT = 20;
    protected WebDriver driver=null;
    static String currentPath = System.getProperty("user.dir");
    static Properties prop = new Properties();
    static DesiredCapabilities capability=null;
    static String config;
    static String preferredDriver;
    
    public DriverUtil(String browser, String browserStack) {
    	preferredDriver=browser;
    	if(browserStack==null)
    		config="";
    	else
    		config= browserStack;
    	
	}


	public static DesiredCapabilities getCapability(InputStream input) {
    	DesiredCapabilities capability = new DesiredCapabilities();
    	try {
    		prop.load(input);
    		if(prop.containsKey("app")) {
    			String appName = prop.getProperty("app");
    			if(!appName.contains("sauce-storage")) {
    				String appPath = currentPath+"/src/main/java/appUnderTest/"+appName;
    				prop.setProperty("app", appPath);
    			}
    		}
    		
    		// set capabilities
    		Enumeration<Object> enuKeys = prop.keys();
    		while (enuKeys.hasMoreElements()) {
    			String key = (String) enuKeys.nextElement();
    			String value = prop.getProperty(key);
    			capability.setCapability(key, value);
    		}
    		input.close();
    	}catch(Exception e) {
    		e.printStackTrace();
			System.exit(0);
    	}
    	return capability;
    }


	public WebDriver getDefaultDriver() {
		if (driver != null) {
			return driver;
		}
		
		String enviroment = "desktop";
		String platform = "";
		
		
		if(!config.isEmpty())
		{
			try{
				enviroment = config.split("_")[0].toLowerCase();
				platform = config.split("_")[1].toLowerCase();
				InputStream input = new FileInputStream(currentPath+"/src/main/java/browserConfigs/"+config+".properties");
				capability = getCapability(input);
			}
			catch(Exception e){
				System.out.println("\nException : File not present or Invalid config file name "+config+".properties");
				System.out.println("Config file format should be : enviroment_platform_device.properties");
				System.out.println("\nE.g : local_android_nexus5.properties");
				System.out.println("E.g : local_ios_iphone6.properties");
				System.out.println("E.g : browserstack_android_nexus5.properties");
				System.out.println("E.g : saucelab_windows7_chrome.properties");
				System.exit(0);
			}
		}
		
		switch(enviroment)
		{
			
			
			case "browserstack": driver = browserStackDriver(capability);
								 break;
			 					 
			case "desktop": DesiredCapabilities capabilities = null;
							capabilities = DesiredCapabilities.firefox();
					        capabilities.setJavascriptEnabled(true);
					        capabilities.setCapability("takesScreenshot", true);
					        driver = chooseDriver(capabilities);
					        driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
					        driver.manage().window().maximize();
					        break;
			
			default : 	System.out.println("\nException : Invalid platform "+enviroment);
						System.exit(0);
		}
        
        return driver;
    }

    /*
     * Returns saucelab remote driver instance by reading saucelab configuration
     * from platformConfigs/saucelab.properties
     * 
     * @param DesiredCapabilities create capabilities by reading browser config.
     * @return RemoteWebDriver
     */
   
    
    /*
     * Returns browserStack remote driver instance by reading browserStack configuration
     * from platformConfigs/browserstack.properties
     * 
     * @param DesiredCapabilities create capabilities by reading browser config.
     * @return RemoteWebDriver
     */
    private static WebDriver browserStackDriver(DesiredCapabilities capabilities) {
    	URL remoteDriverURL = null;
    	try {
	    	InputStream input = new FileInputStream(currentPath+"/src/main/java/platformConfigs/browserstack.properties");
			prop.load(input);
			
			String url = prop.getProperty("protocol")+
	    				 "://"+
	    				 prop.getProperty("username")+
	    				 ":"+
	    				 prop.getProperty("access_key")+
	    				 prop.getProperty("url");
			
			input.close();
			prop.clear();
	    	remoteDriverURL = new URL(url);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return new RemoteWebDriver(remoteDriverURL, capability);
    }
    
   
    
    /**
     * By default to web driver will be firefox
     *
     * Override it by passing -Dbrowser=Chrome to the command line arguments
     * @param <FirefoxOptions>
     * @param capabilities
     * @return webdriver
     */
    private <FirefoxOptions> WebDriver chooseDriver(DesiredCapabilities capabilities) {
		
		boolean headless = System.getProperty("headless", "false").equals("true");
		
		switch (preferredDriver.toLowerCase()) {
			case "safari":
				try {
					driver = new SafariDriver();
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.exit(0);
				}
				return driver;
			
			case "chrome":
				final ChromeOptions chromeOptions = new ChromeOptions();
				if (headless) {
					chromeOptions.addArguments("--headless");
				}
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				try
				{
					driver = new ChromeDriver(capabilities);
					ErrorHandler handler = new ErrorHandler();
					handler.setIncludeServerErrors(false);
					//driver.setErrorHandler(handler);
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.exit(0);
				}
				return driver;
			default:
				
				try {
					driver = new FirefoxDriver(capabilities);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
					System.exit(0);
				}
				return driver;
		}
    }

   
	
}
