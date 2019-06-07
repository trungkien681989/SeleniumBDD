package driverFactory;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class IELocalDriverManager extends DriverManager{
	
	@Override
	public void createWebDriver() {
		System.setProperty("webdriver.ie.driver","src/test/resources/drivers/win/IEDriverServer.exe");
		InternetExplorerOptions options = new InternetExplorerOptions();
		this.driver = new InternetExplorerDriver(options);
	}

}
