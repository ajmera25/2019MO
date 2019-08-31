package stepdefinition;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import utils.WebDriverClient;

public class BaseSteps {

	protected WebDriver desktopWebDriver;
	protected WebDriver mobileWebDriver;
	public WebDriverClient pageWebDriverClient = null;
	protected AppiumDriver appiumDriver;
	protected String url = null;
	protected String testDataPath;
	
	
	public BaseSteps(SetUpSteps ctx) {
		// TODO Auto-generated constructor stub
		desktopWebDriver = ctx.getDesktopWebDriver();
		mobileWebDriver = ctx.getMobileWebDriver();
		appiumDriver = ctx.getAppiumDriver();
		//mmpController = ctx.getController();
		
	}

	
}
