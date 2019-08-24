package stepdefinition;

import org.openqa.selenium.WebDriver;

import core.Controller;

public class BaseSteps {

	protected WebDriver desktopWebDriver;
	protected WebDriver mobileWebDriver;
	protected String url = null;
	protected String testDataPath;
	protected Controller mmpController;
	
	public BaseSteps(SetUpSteps ctx) {
		// TODO Auto-generated constructor stub
		desktopWebDriver = ctx.getDesktopWebDriver();
		mobileWebDriver = ctx.getMobileWebDriver();
		mmpController = ctx.getController();
	}

}
