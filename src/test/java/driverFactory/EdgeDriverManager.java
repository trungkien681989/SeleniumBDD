package driverFactory;

import org.openqa.selenium.edge.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager extends DriverManager{
	
	@Override
	public void createWebDriver() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		this.driver = new EdgeDriver(options);
	}

}