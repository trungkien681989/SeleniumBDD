package driverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadlessDriverManager extends DriverManager{
	
	@Override
	public void createWebDriver() {
    	System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/win/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        this.driver = new ChromeDriver(options);
	}

}