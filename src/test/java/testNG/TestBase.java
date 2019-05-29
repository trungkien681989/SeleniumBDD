package testNG;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;

import driverFactory.DriverManager;
import driverFactory.DriverManagerFactory;
import driverFactory.DriverManagerFactory.DriverType;
import utilities.data.ReadConfig;
import utilities.report.ReportHelper;

public class TestBase {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	
	public static WebDriver driver;
	protected DriverManager driverManager;
	public static Logger logger;
	
	@BeforeClass(alwaysRun = true)
    public void initLog() throws Throwable {
		logger = Logger.getLogger("Test \"automationpractice.com\"");
		PropertyConfigurator.configure("./src/test/resources/log4j.properties");
    }
	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
    public void setupDriver(DriverType type) throws Throwable {
		driverManager = DriverManagerFactory.getDriverManager(type);
        driver = driverManager.getWebDriver();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	driver.get(baseURL);
    }
	
	@AfterMethod(alwaysRun = true)
    public void checkResult(ITestResult result) throws Exception {
    	switch (result.getStatus()) {
        case ITestResult.FAILURE:
        	ExtentTestManager.getTest(result).fail(result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(
                    		ReportHelper.getScreenShot(driver, result.getName())).build());
            logger.info(result.getThrowable().getMessage());
        	break;
        case ITestResult.SKIP:
            ExtentTestManager.getTest(result).skip(result.getThrowable().getMessage());
            logger.info(result.getThrowable().getMessage());
            break;
        default:
            break;
        }
    	driverManager.quitWebDriver();
    }
	
	@AfterSuite(alwaysRun = true)
	public void saveReport() throws Throwable {
		File htmlSource = new File("./test-output/HtmlReport/ExtentHtml.html");
		File htmlDestination = new File("./Reports/HtmlReports/ExtentHtml" 
		+ System.currentTimeMillis() + ".html");
		FileUtils.copyFile(htmlSource, htmlDestination);
		File loggerSource = new File("./test-output/LoggerReport/");
		File loggerDestination = new File("./Reports/LoggerReport" 
		+ System.currentTimeMillis());
		FileUtils.copyDirectory(loggerSource, loggerDestination);
	}
	
}
