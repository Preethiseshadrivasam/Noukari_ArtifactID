package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	
	@FindBy(xpath="//input[@id='usernameField']")
	WebElement email_ID;
	
	@FindBy(xpath="//input[@id='passwordField']")
	WebElement psw;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement login_button;
	
	// Actions
	
	public void setUsername(String email) {
		
		email_ID.sendKeys(email);
	}
	
  public void setPassword(String pass) {
		
		psw.sendKeys(pass);
	}
	

  public void loginClick( ) {
		
	  login_button.click();
		
	}
	

}
