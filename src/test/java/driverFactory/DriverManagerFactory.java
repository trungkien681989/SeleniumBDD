package driverFactory;

public class DriverManagerFactory {
	
	public enum DriverType{
		CHROME,FIREFOX,EDGE,IE,FIREFOXLOCAL,CHROMELOCAL,IELOCAL,EDGELOCAL,CHROMEHEADLESS,FIREFOXHEADLESS;
	}
	
	public static DriverManager getDriverManager(DriverType type) {
		DriverManager driverManager = null;
		
		switch (type) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case CHROMELOCAL:
			driverManager = new ChromeLocalDriverManager();
			break;
		case CHROMEHEADLESS:
			driverManager = new ChromeHeadlessDriverManager();
			break;
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
			break;
		case FIREFOXLOCAL:
			driverManager = new FirefoxLocalDriverManager();
			break;
		case FIREFOXHEADLESS:
			driverManager = new FirefoxHeadlessDriverManager();
			break;
		case EDGE:
			driverManager = new EdgeDriverManager();
			break;
		case EDGELOCAL:
			driverManager = new EdgeLocalDriverManager();
			break;
		case IE:
			driverManager = new IEDriverManager();
			break;
		case IELOCAL:
			driverManager = new IELocalDriverManager();
			break;
		default:
			break;
		}
		return driverManager;
	}
}
