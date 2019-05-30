package testNG;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import driverFactory.DriverManager;
import driverFactory.DriverManagerFactory;
import driverFactory.DriverManagerFactory.DriverType;
import utilities.data.ReadConfig;
import utilities.report.ReportHelper;
import utilities.report.ReportTestIReporter;
import utilities.report.ReportTestListener;

@Listeners({
	ExtentITestListenerClassAdapter.class,
	ReportTestListener.class, 
	ReportTestIReporter.class
	})

public class TestBase {
	
	protected ReadConfig readconfig = new ReadConfig();
	
	protected String baseURL = readconfig.getApplicationURL();
	protected String username = readconfig.getUsername();
	protected String password = readconfig.getPassword();
	
	protected static WebDriver driver;
	protected static DriverManager driverManager;
	protected static Logger logger;
	
	@BeforeSuite(alwaysRun = true)
    public void initLog() throws Throwable {
		PropertyConfigurator.configure("./src/test/resources/log4j.properties");
    }
	
	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
    public void setupDriver(DriverType type) throws Throwable {
		driverManager = DriverManagerFactory.getDriverManager(type);
        driver = driverManager.getWebDriver();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    }
	
	@BeforeMethod(alwaysRun = true)
    public void navigate() throws Throwable {
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
    }
	
	@AfterClass(alwaysRun = true)
    public void quitDriver() throws Exception {
		driverManager.quitWebDriver();
	}
	
	@AfterSuite(alwaysRun = true)
	public void saveReport() throws Throwable {
		File htmlSource = new File("./test-output/HtmlReport/ExtentHtml.html");
		File htmlDestination = new File("./Reports/HtmlReports/ExtentHtml_" 
		+ ReportHelper.getSystemTime() + ".html");
		FileUtils.copyFile(htmlSource, htmlDestination);
		File loggerSource = new File("./test-output/LoggerReport/");
		File loggerDestination = new File("./Reports/LoggerReport_" 
		+ ReportHelper.getSystemTime());
		FileUtils.copyDirectory(loggerSource, loggerDestination);
	}
	
}
