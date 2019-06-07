package driverFactory;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxLocalDriverManager extends DriverManager{
    
    @Override
    public void createWebDriver() {
    	System.setProperty("webdriver.gecko.driver","src/test/resources/drivers/win/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        this.driver = new FirefoxDriver(options);
    }

}