package base;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


import org.openqa.selenium.remote.DesiredCapabilities;


import komoot.page_object_model.HomePage;
import komoot.page_object_model.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.testng.Assert;


public class TestBase {

	public static Properties CONFIG = null;
	public static Properties OR = null;

	public static boolean isinitialized = false;

	public static EnhancedAndroidDriver<MobileElement> driver = null;
	public static HomePage hp = null;
	public static LoginPage lp = null;
	
	public static String platformName = null;
	public static String caseName = null;
	public static DesiredCapabilities cap = null;



	
	public void initialize(String description, String Category, String testcasenumber)
			throws IOException {

		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(classLoader.getResource(""));
		CONFIG = new Properties();
		
		InputStream ip = classLoader.getResourceAsStream("CONFIG.properties");
		CONFIG.load(ip);
		OR = new Properties();
		InputStream ip_OR = classLoader.getResourceAsStream("OR.properties");
		OR.load(ip_OR);
		platformName = CONFIG.getProperty("platformName");
		caseName = this.getClass().getSimpleName();
	
		hp = new HomePage(driver, OR, CONFIG);
		lp = new LoginPage(driver, OR, CONFIG);
		isinitialized = true;			
			}
	

	public void openApp(Boolean fullreset) throws IOException, InterruptedException {
		

		
		cap = new DesiredCapabilities();

		
		File f = new File("C:\\Users\\TCBALTAY\\Desktop\\Komoot — Cycling Hiking Mountain Biking Maps_v9.8.8_apkpure.com.apk");

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "a7849932");//turkcell t80 kurumsal hat
		
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		cap.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, false);
		cap.setCapability("noReset",!fullreset);
		cap.setCapability("fullReset",fullreset);
		cap.setCapability("autoGrantPermissions", true);
		

		
		cap.setCapability(MobileCapabilityType.APP, f.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,	"25");

		driver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);	

		if(fullreset==true){
		Thread.sleep(10000);
		}
		
	}

	
	public void presskeysinkeyboard(String chars) throws Throwable {
		driver.getKeyboard().pressKey(chars);
	}
	

	public void closeApp() throws Throwable {

		driver.quit();

	}
	
	public void swipe(int startx,int starty,int endx ,int endy,int time) throws Throwable {
		 driver.swipe(startx, starty, endx, endy, time);
	}
	
	public boolean isElementPresent(By by, int waitInSeconds) throws Throwable {
		try{
			 
			driver.manage().timeouts().implicitlyWait(waitInSeconds,TimeUnit.SECONDS);
			 				 
			driver.findElement(by);	
			driver.manage().timeouts().implicitlyWait(35,TimeUnit.SECONDS);
			return true;
		 }
		 catch(NoSuchElementException e){
			 return false;
		 }
	}
	
	public boolean isElementPresent(By by, boolean isValidation) throws Throwable {
		try{
			if(isValidation){
			driver.manage().timeouts().implicitlyWait(55,TimeUnit.SECONDS);
			}
			else{
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			}
			driver.findElement(by);	
			driver.manage().timeouts().implicitlyWait(55,TimeUnit.SECONDS);
			return true;
		 }
		 catch(NoSuchElementException e){
			 return false;
		 }
	}




	public AndroidElement getObjectByXpath(String xpathKey) throws Throwable {
		try {
			driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
//			IOSElement ele = (IOSElement) driver.findElementByXPath(OR
//					.getProperty(xpathKey));
			AndroidElement ele = (AndroidElement) driver.findElement(By.xpath(OR
			.getProperty(xpathKey)));
			return ele;
		} catch (Throwable t) {
			Assert.assertTrue(false,"Couldn't find object with getObjectByXpath on this Device: "+xpathKey+"="+OR.getProperty(xpathKey));
			return null;
		}
	}
	

	public MobileElement getObjectByAccessibilityId(String accesibilityId) throws Throwable {
		try {
			MobileElement ele = driver.findElementByAccessibilityId(OR
					.getProperty(accesibilityId));
			return ele;
		} catch (Throwable t) {
			
			Assert.assertTrue(false,"Couldn't find object with getObjectByAccessibilityId on this Device: "+accesibilityId+"="+OR.getProperty(accesibilityId));
			return null;
		}
	}


	public List<MobileElement> getObjectsByAccessibilityId(String accesibilityId) throws Throwable {
		try {
			List<MobileElement> ele = driver.findElementsById(OR.getProperty(accesibilityId));
			return ele;
		} catch (Throwable t) {

			Assert.assertTrue(false,"Couldn't find object with getObjectsByAccessibilityId on this Device: "+accesibilityId+"="+OR.getProperty(accesibilityId));
			return null;
		}
	}
	

	public List<MobileElement> getObjectsByClassName(String idKey) throws Throwable {
		try {
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			List<MobileElement> eles =  driver.findElements(By.className(OR
					.getProperty(idKey)));
			return eles;
		} catch (Throwable t) {
			Assert.assertTrue( false,"Couldn't find object with getObjectsByClassName on this Device: "+idKey+"="+OR.getProperty(idKey));
			return null;
		}}
	
	public List<MobileElement> getObjectsByXpath(String idKey) throws Throwable {
		try {
			driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);

			List<MobileElement> eles =  driver.findElements(By.xpath(OR
					.getProperty(idKey)));
			return eles;
		} catch (Throwable t) {
			Assert.assertTrue(false,"Couldn't find object with getObjectsByXpath on this Device: "+idKey+"="+OR.getProperty(idKey));
			return null;
		}}
		

	public MobileElement getObjectById(String idKey) throws Throwable {
		try {
		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//			AndroidElement ele = (AndroidElement) driver.findElement(By.id(OR.getProperty(idKey)));
			MobileElement ele = (MobileElement) driver.findElementById(OR.getProperty(idKey));
			return ele;
		} catch (Throwable t) {
			
			Assert.assertTrue(false,"Couldn't find object with getObjectById on this Device: "+idKey+"="+OR.getProperty(idKey));
			return null;
		}
	}



	public MobileElement getObjectByClassName(String idKey) throws Throwable {
		try {
			MobileElement ele = (MobileElement) driver
					.findElement(By.className(OR.getProperty(idKey)));
			return ele;
		} catch (Throwable t) {
			Assert.assertTrue(false,"Couldn't find object with getObjectByClassName on this Device: "+idKey+"="+OR.getProperty(idKey));
			return null;
		}
	}

	public MobileElement getObjectByName(String idKey) throws Throwable {
		try {
			MobileElement ele = (MobileElement) driver.findElementByName(OR
					.getProperty(idKey));
			return ele;
		} catch (Throwable t) {
			Assert.assertTrue( false,"Couldn't find object with getObjectByName on this Device: "+idKey+"="+OR.getProperty(idKey));
			return null;
		}
	}
	public void tap( int x, int y){
		new TouchAction((EnhancedAndroidDriver<MobileElement>) driver).tap(x, y).perform();
	}

}
