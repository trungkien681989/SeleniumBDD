package driverFactory;

import org.openqa.selenium.firefox.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxHeadlessDriverManager extends DriverManager{
	
	@Override
	public void createWebDriver() {
    	//System.setProperty("webdriver.gecko.driver","src/test/resources/drivers/win/geckodriver.exe");
    	WebDriverManager.firefoxdriver().setup();
    	FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        this.driver = new FirefoxDriver(options);
	}
	
}