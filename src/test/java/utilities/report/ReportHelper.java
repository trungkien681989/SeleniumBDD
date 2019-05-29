package utilities.report;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;

public class ReportHelper {
	
	public static void Log(Logger log, Status status, String details, WebDriver driver) {
		
		log.info(details);
		
		try {
			ExtentTestManager.getTest().log(status, details, MediaEntityBuilder.createScreenCaptureFromPath
						(getScreenShot(driver, "SCREENSHOT")).build());
		} catch (IOException e) {
			System.out.println("Failed to log info with Screenshot" + e.getMessage());
		}
		
	}
	
	public static void Log(Logger log, Status status, String details) {
		
		log.info(details);
		ExtentTestManager.getTest().log(status, details);
				
	}
	
	
	public static String getScreenShot(WebDriver driver, String screenshotName) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			System.out.println("Failed to capture Screenshot" + e.getMessage());
		}
		
		return path;
		
	}
}
