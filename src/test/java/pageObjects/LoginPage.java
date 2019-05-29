package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{

	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// Selectors that help the WebDriver object find the web elements you want to interact with.
	@FindBy(id="email")
	@CacheLookup
	private WebElement emailTextbox;
	@FindBy(id="passwd")
	@CacheLookup
	private WebElement passwordTextbox;
	@FindBy(id="SubmitLogin")
	@CacheLookup
	private WebElement loginButton;
	@FindBy(xpath="//li[.='Authentication failed.']")
	@CacheLookup
	private WebElement warningMessage;
	
	// Methods to perform on the page.
	public void login (String email, String password) {
		emailTextbox.sendKeys(email);
		passwordTextbox.sendKeys(password);
		loginButton.click();
	}
	
	public void enterEmail (String email) {
		emailTextbox.sendKeys(email);
	}
	
	public void enterPassword (String password) {
		passwordTextbox.sendKeys(password);
	}
	
	public void clickLogin () {
		loginButton.click();
	}
	
	public boolean isWarningDisplayed () {
		return warningMessage.isDisplayed();
	}

}
