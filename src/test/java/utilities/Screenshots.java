package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Screenshots {

	public static String takeScreenshot(IOSDriver driver, String fileName) throws IOException {

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy--HH");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		fileName = fileName + ".png";
		String directory = "JetMobileReports/" + df.format(cal.getTime()).toString() + "/";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		String destination = directory + fileName;
		System.out.println("destination is " + destination);
		return destination;
	}
}


