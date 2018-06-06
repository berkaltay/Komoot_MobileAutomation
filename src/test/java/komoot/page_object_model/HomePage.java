package komoot.page_object_model;

import java.util.Properties;

import org.openqa.selenium.By;

import base.TestBase;
import io.appium.java_client.android.AndroidDriver;



public class HomePage extends TestBase {

	@SuppressWarnings("rawtypes")
	AndroidDriver driver = null;
	Properties OR = null;
	Properties CONFIG = null;

	@SuppressWarnings("rawtypes")
	public HomePage(AndroidDriver driver, Properties OR, Properties CONFIG) {
		this.driver = driver;
		this.OR = OR;
		this.CONFIG = CONFIG;
	}

	
	
	public void validateHomePage() throws Throwable {

		getObjectById("komootlogoid");
	}
	
	public void viewProfile() throws Throwable {

		getObjectById("komootlogoid").click();
		getObjectById("nameFieldId").getText().equals("Berk Altay");
	}
	
	
}