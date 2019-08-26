package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.cucumber.core.api.Scenario;
import stepdefinition.SetUpSteps;


public class DriverUtils {

	public static void takeScreenShot(ITestResult result, WebDriver driver) {
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			ITestNGMethod testMethod = result.getMethod();
			File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			String nameScreenshot = ((RemoteWebDriver) driver).getCapabilities().getBrowserName() + "_" + testMethod.getTestClass().getRealClass().getSimpleName() + "_" + testMethod.getMethodName();
			String path = getFullPath(nameScreenshot);
			
			FileUtils.copyFile(screenshot, new File(path));
			Reporter.log("Screenshot - " + path);
			
		 	Reporter.log("<img src='../../screenShots/" + getFileName(nameScreenshot) +"'"+ " target='_blank' >" + "<br>" + getFileName(nameScreenshot) );
		}
		catch (IOException e) {
	         e.printStackTrace();
	    }
	}
	
	public static void takeScreenShot(Scenario scenario, WebDriver driver) {
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			byte[] byte_screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
			String nameScreenshot = ((RemoteWebDriver) driver).getCapabilities().getBrowserName() + "_" + SetUpSteps.getScenarioName().toLowerCase();
			String path = getFullPath(nameScreenshot);
			
			
			FileUtils.copyFile(screenshot, new File(path));
			scenario.embed(byte_screenshot, "image/png");
			}
		catch (IOException e) {
	         e.printStackTrace();
	    }
	}
	
	public static byte[] takeScreenShot(WebDriver driver) {
		byte[] byte_screenshot = null;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			byte_screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
			String nameScreenshot = ((RemoteWebDriver) driver).getCapabilities().getBrowserName() + "_" + SetUpSteps.getScenarioName().toLowerCase();
			String path = getFullPath(nameScreenshot);
			FileUtils.copyFile(screenshot, new File(path));
			}
		catch (IOException e) {
	         e.printStackTrace();
	    }
		return byte_screenshot;
	}
	
	private static String getFileName(String nameTest) throws IOException {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss");	
		return dateFormat.format(date) + "_" + nameTest + ".png";
	}
	
	private static  String getFullPath(String nameTest) throws IOException {
		File directory = new File(".");
		System.out.println("Path in getpath" + directory.getCanonicalPath());
	 	String newFileNamePath = directory.getCanonicalPath() + "//screenShots//" + getFileName(nameTest);
		return newFileNamePath;
	}
	
	public static String getReportsPath() throws Exception {
		String dir_path=SetUpSteps.getTestDataPath() + "/Reports/";
		if(File.separator.equals("/")){
			dir_path = dir_path.replace((char)92,(char)47);
		}
		else
			dir_path = dir_path.replace((char)47,(char)92);
		
		return dir_path;
	}
	
	public static String getCommonUploadsPath() throws Exception {
		String dir_path=SetUpSteps.getResourcesPath() + "test-data/Uploads/";
		if(File.separator.equals("/")){
			dir_path = dir_path.replace((char)92,(char)47);
		}
		else
			dir_path = dir_path.replace((char)47,(char)92);
		
		return dir_path;
	}
	
	public static String getCommonDataPath() throws Exception {
		String dir_path=SetUpSteps.getResourcesPath() + "test-data/CommonData/";
		if(File.separator.equals("/")){
			dir_path = dir_path.replace((char)92,(char)47);
		}
		else
			dir_path = dir_path.replace((char)47,(char)92);
		
		return dir_path;
	}
	
	public static void extractJSLogs(RemoteWebDriver driver, String browser) {
	    LogEntries logEntries = driver.manage().logs().get(browser);
	    for (LogEntry entry : logEntries) {
	        Reporter.log(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage(), true);
	    }
	} 


}
