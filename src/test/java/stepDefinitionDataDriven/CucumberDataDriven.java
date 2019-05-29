package stepDefinitionDataDriven;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import driverFactory.DriverManager;
import driverFactory.DriverManagerFactory;
import driverFactory.DriverManagerFactory.DriverType;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;

import pageObjects.AccountPage;
import pageObjects.GeneralPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;


public class CucumberDataDriven {
    
	WebDriver driver;
	DriverManager driverManager;
    GeneralPage generalPage;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    
    @Before
    public void setUp() throws Throwable {
    	driverManager = DriverManagerFactory.getDriverManager(DriverType.FIREFOX);
        driver = driverManager.getWebDriver();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    }
    
    @Given("^user is on homepage$")
    public void user_is_on_homepage() throws Throwable {  
    	driver.get("http://automationpractice.com/index.php");
    }
    
    @When("^user navigates to Login Page$")
    public void user_navigates_to_Login_Page() throws Throwable {
    	homePage = PageFactory.initElements(driver, HomePage.class);
    	homePage.goToLoginPage();
    }
    
    @When("^user enters username and Password$")
    public void user_enters_username_and_Password() throws Throwable {
    	loginPage = PageFactory.initElements(driver, LoginPage.class);
    	loginPage.login("blog.cucumber@gmail.com", "Cucumber@blog"); 
    }
    
    @Then("^success \"([^\"]*)\" is displayed$")
    public void success_message_is_displayed(String welcomeMessage) throws Throwable {
    	accountPage = PageFactory.initElements(driver, AccountPage.class);
    	assertEquals(accountPage.getWelcomeMessage(), welcomeMessage);
    }
    
    @After
    public void cleanUp(Scenario scenario) throws Exception {
    	if (scenario.isFailed()) {
    		// Take a screenshot...
    		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    		scenario.embed(screenshot, "image/png"); // ... and embed it in the report
    	}
    	driverManager.quitWebDriver();
    }
	
}
