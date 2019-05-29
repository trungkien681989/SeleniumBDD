package testNG;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.report.ReportHelper;

@Listeners({ExtentITestListenerClassAdapter.class})
public class LoginPass extends TestBase{
	
    @Test
    public void Login_Valid() throws Throwable {
    	
    	ReportHelper.Log(logger, Status.INFO, "Go to Login Page");
    	HomePage homePage = new HomePage(driver);
    	homePage.goToLoginPage();
    	
    	ReportHelper.Log(logger, Status.INFO, "Login Account");
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.login(username, password);
    	
    	ReportHelper.Log(logger, Status.INFO, "Verify Welcome Message");
    	AccountPage accountPage = new AccountPage(driver);
    	SoftAssert softAssert = new SoftAssert();
    	softAssert.assertEquals(accountPage.getWelcomeMessage(),"Welcome to your account. Here you can manage all of your personal information and orders.");
    	softAssert.assertAll();
    }
}