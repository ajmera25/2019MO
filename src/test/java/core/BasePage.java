package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utils.WebDriverClient;

public class BasePage {
	public WebDriverClient pageWebDriverClient = null;
	protected WebDriver driver = null;
	public String currentURL = "";
	private static final int TIMEOUT = 5;
   
	public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }
	
	public WebDriverClient getWebDriverClient() {
		 return pageWebDriverClient;
	}

	public void setWebDriverClient(WebDriverClient client) {
		pageWebDriverClient = client;
	}
}
