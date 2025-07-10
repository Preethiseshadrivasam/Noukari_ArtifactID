package TestCases;

import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;

public class TC001_LoginTest extends BaseClass {

	
	@Test
	public void verify_LoginTest() throws Exception {
		
		HomePage hp=new HomePage(driver);
		hp.login();
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setUsername(p.getProperty("emailID"));
		Thread.sleep(2000);
		lp.setPassword(p.getProperty("passWord"));
		lp.loginClick();
	}
	
	
   
	
}
