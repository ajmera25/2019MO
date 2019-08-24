package core;

import org.openqa.selenium.WebDriver;

import utils.WebDriverClient;


public class Provider {
	
	private WebDriverClient mobileWebDriverClient;
	private WebDriverClient desktopWebDriverClient;

	public  WebDriverClient getWebDriverClient(WebDriver driver,String platform) {
		if (mobileWebDriverClient==null && platform.equalsIgnoreCase("mobileWeb")) {
			mobileWebDriverClient = new WebDriverClient(driver);
			return mobileWebDriverClient;
  		}else if(desktopWebDriverClient==null && platform.equalsIgnoreCase("desktopWeb")) {
  			desktopWebDriverClient = new WebDriverClient(driver);
  			return desktopWebDriverClient;
  		}
		return desktopWebDriverClient;
	}

	
	
	
}
