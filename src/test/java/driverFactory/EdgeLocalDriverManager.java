package driverFactory;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeLocalDriverManager extends DriverManager{
	
	@Override
	public void createWebDriver() {
		System.setProperty("webdriver.edge.driver","src/test/resources/drivers/win/MicrosoftWebDriver.exe");
		EdgeOptions options = new EdgeOptions();
		this.driver = new EdgeDriver(options);
	}

}