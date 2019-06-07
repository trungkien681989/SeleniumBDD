package driverFactory;

import org.openqa.selenium.firefox.*;

public class FirefoxHeadlessDriverManager extends DriverManager{
	
	@Override
	public void createWebDriver() {
    	System.setProperty("webdriver.gecko.driver","src/test/resources/drivers/win/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        this.driver = new FirefoxDriver(options);
	}
	
}