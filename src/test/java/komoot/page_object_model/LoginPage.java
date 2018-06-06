package komoot.page_object_model;

import java.util.Properties;

import org.openqa.selenium.By;

import base.TestBase;
import io.appium.java_client.android.AndroidDriver;



public class LoginPage extends TestBase {

	@SuppressWarnings("rawtypes")
	AndroidDriver driver = null;
	Properties OR = null;
	Properties CONFIG = null;

	@SuppressWarnings("rawtypes")
	public LoginPage(AndroidDriver driver, Properties OR, Properties CONFIG) {
		this.driver = driver;
		this.OR = OR;
		this.CONFIG = CONFIG;
	}

	
	
	public void login(String username, String password) throws Throwable {

		getObjectById("komootmainlogoid");
		getObjectById("continuewithemailid").click();
		getObjectById("noneofabove").click();
		
		getObjectById("emailfield").sendKeys(username);
		getObjectById("nextbuttonid").click();
		
		getObjectById("passwordfield").sendKeys(password);
		getObjectById("loginbuttonid").click();
		

	}
	
	
	
}