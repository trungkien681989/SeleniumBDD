package testNG;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.report.ReportHelper;

public class LoginFail extends TestBase{
	
    @Test
    public void Login_Fail() throws Throwable {
    	logger = Logger.getLogger("Test invalid Welcome Message");
    	ReportHelper.Log(logger, Status.INFO, "Go to Login Page", driver);
    	HomePage homePage = new HomePage(driver);
    	homePage.goToLoginPage();
    	
    	ReportHelper.Log(logger, Status.INFO, "Login Account", driver);
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.login(username, password);
    	
    	ReportHelper.Log(logger, Status.INFO, "Verify Welcome Message", driver);
    	AccountPage accountPage = new AccountPage(driver);
    	SoftAssert softAssert = new SoftAssert();
    	softAssert.assertEquals(accountPage.getWelcomeMessage(),"Fail");
    	softAssert.assertAll();
    }
}
