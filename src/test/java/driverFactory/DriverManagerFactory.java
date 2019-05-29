package driverFactory;

public class DriverManagerFactory {
	
	public enum DriverType{
		CHROME,CHROMEHEADLESS, FIREFOX, FIREFOXHEADLESS,EDGE,IE;
	}
	
	public static DriverManager getDriverManager(DriverType type) {
		DriverManager driverManager = null;
		
		switch (type) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case CHROMEHEADLESS:
			driverManager = new ChromeHeadlessDriverManager();
			break;
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
			break;
		case FIREFOXHEADLESS:
			driverManager = new FirefoxHeadlessDriverManager();
			break;
		case EDGE:
			driverManager = new EdgeDriverManager();
			break;
		default:
			driverManager = new IEDriverManager();
			break;
		}
		return driverManager;
	}
}
