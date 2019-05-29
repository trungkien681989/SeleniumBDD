package driverFactory;

import org.openqa.selenium.ie.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager extends DriverManager{
	
	@Override
	public void createWebDriver() {
		WebDriverManager.iedriver().setup();
		InternetExplorerOptions options = new InternetExplorerOptions();
		this.driver = new InternetExplorerDriver(options);
	}

}
