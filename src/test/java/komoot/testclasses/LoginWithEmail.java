package komoot.testclasses;


import com.microsoft.appcenter.appium.Factory;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.testng.Assert;
import org.testng.annotations.*;

@SuppressWarnings("unused")
public class LoginWithEmail extends TestSuiteBase {
	private Boolean fullreset = false;
	String testCaseName = this.getClass().getSimpleName();
	
	@Rule
	    public TestWatcher watcher = Factory.createWatcher();
	 
	@BeforeTest
	public void beforeTest() throws Exception, Throwable {

		if (!isinitialized) {
			initialize("Login Email", "Homepage", "1");
		}
	}

	// retryAnalyzer=ReTryTestCase.class

	@Test
	public void f() throws Exception, Throwable {

		try {
			openApp(fullreset);
			
			lp.login("berkaltay@gmail.com","123456789");
			
			hp.validateHomePage();
			
			hp.viewProfile();
			
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.assertTrue(false,e.getMessage());
		}

	}

	@AfterTest
	public void afterTest() throws Throwable {

		isinitialized = false;

		closeApp();

	}

}
