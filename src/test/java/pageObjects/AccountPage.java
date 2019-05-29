package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends GeneralPage{
	
	WebDriver ldriver;
	
	public AccountPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// Selectors that help the WebDriver object find the web elements you want to interact with.
	@FindBy(css=".info-account")
	@CacheLookup
	private WebElement welcomeMessage;
	
	// Methods to perform on the page.
	public String getWelcomeMessage() {		
		return welcomeMessage.getText();
	}
	
}
