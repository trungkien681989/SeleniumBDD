package testNG;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.report.ReportHelper;
import utilities.data.XLUtils;

@Listeners({ExtentITestListenerClassAdapter.class})
public class LoginDataDriven extends TestBase{
	
    @Test(dataProvider="Account")
    public void Warning_Message_Display_For_Invalid_Login(String user,String pwd) throws Throwable {
    	
    	logger = Logger.getLogger("Test Datadriven");
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
