package testNG;

import java.io.File;
import java.io.IOException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import driverFactory.DriverManager;
import driverFactory.DriverManagerFactory;
import driverFactory.DriverManagerFactory.DriverType;
import utilities.data.ReadConfig;
import utilities.data.XLUtils;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.report.ReportHelper;

@Listeners({ExtentITestListenerClassAdapter.class})
public class LoginDataDriven{
	
	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	public static WebDriver driver;
	protected DriverManager driverManager;
	public static Logger logger;
	
	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
    public void setUp(DriverType type) throws Throwable {
		logger = Logger.getLogger("Test \"automationpractice.com\"");
		PropertyConfigurator.configure("./src/test/resources/log4j.properties");
		driverManager = DriverManagerFactory.getDriverManager(type);
        driver = driverManager.getWebDriver();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	driver.get(baseURL);
    }
	
    @Test(dataProvider="Account")
    public void Warning_Message_Display_For_Invalid_Login(String user,String pwd) throws Throwable {
    	
    	ReportHelper.Log(logger, Status.INFO, "Go to Login Page");
    	HomePage homePage = new HomePage(driver);
    	homePage.goToLoginPage();
    	
    	ReportHelper.Log(logger, Status.INFO, "Login Account");
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.login(user, pwd);
    	
    	ReportHelper.Log(logger, Status.INFO, "Verify Warning Message");
    	SoftAssert softAssert = new SoftAssert();
    	softAssert.assertTrue(loginPage.isWarningDisplayed(), "Is warning message displayed ?? => ");
    	softAssert.assertAll();
    }
    
    @AfterMethod(alwaysRun = true)
    public void checkResult(ITestResult result) throws Exception {
    	String path = ReportHelper.getScreenShot(driver, result.getName());
    	switch (result.getStatus()) {
        case ITestResult.FAILURE:
        	ExtentTestManager.getTest(result).fail(result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
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
    public void cleanUp() {
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
    
    @DataProvider(name="Account")
	String [][] getData() throws IOException {
		
    	String path = "./src/test/resources/data/account/account.xlsx";
    	
		int rownum = XLUtils.getRowCount(path, "account");
		int colcount = XLUtils.getCellCount(path,"account", 1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i = 1; i <= rownum; i++)
		{
			for(int j = 0; j < colcount; j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"account", i, j); //1 0
			}
				
		}
		return logindata;
	}
    
}
