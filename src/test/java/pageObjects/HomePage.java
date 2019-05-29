package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends GeneralPage{
	
	WebDriver ldriver;

	public HomePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// Selectors that help the WebDriver object find the web elements you want to interact with.
	@FindBy(linkText="Sign in")
	@CacheLookup
	private WebElement signinButton;
	
	// Methods to perform on the page.
	public void goToLoginPage() {
		signinButton.click();
	}
	
}
