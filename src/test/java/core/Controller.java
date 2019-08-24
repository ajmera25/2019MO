package core;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pageobjects.DesktopHomePage;
import pageobjects.DesktopLoginPage;
import pageobjects.MobileHomePage;
import pageobjects.MobileLoginPage;
import utils.WebDriverClient;


public class Controller {

		// ---------------------------------------------------------------
		// Declare Members of the Class
		// ---------------------------------------------------------------
		
		private final Map<String, Object> clients = new HashMap<String, Object>();
		private final Provider provider = new Provider();
		
		// --------------------------------------------------
		// Declare Member Functions of the Class
		// --------------------------------------------------
	
		public Controller(String browser,WebDriver desktopWebdriver,WebDriver mobileWebdriver) {
			if(mobileWebdriver!=null) {
		    clients.put("mobileWebDriverClient", provider.getWebDriverClient(mobileWebdriver,"mobileWeb"));
			}
			if(desktopWebdriver!=null) {
		    clients.put("desktopWebDriverClient", provider.getWebDriverClient(desktopWebdriver,"desktopWeb"));
		}
		}
		
		public WebDriverClient getMobileWebDriverClient() {
		    return (WebDriverClient) clients.get("mobileWebDriverClient");
		}
		
		public WebDriverClient getDesktopWebDriverClient() {
		    return (WebDriverClient) clients.get("desktopWebDriverClient");
		}
		
		public Provider getProvider() {
		    return provider;
		}
		
		private <T extends BasePage> T initPage(Class<T> t) {
			T page = PageFactory.initElements(this.getDesktopWebDriverClient().getWebDriver(), t);
			page.setWebDriverClient((WebDriverClient) clients.get("desktopWebDriverClient"));
		    return page;
		}
		
		private <T extends BasePage> T mobileInitPage(Class<T> t) {
			T page = PageFactory.initElements(this.getMobileWebDriverClient().getWebDriver(), t);
			page.setWebDriverClient((WebDriverClient) clients.get("mobileWebDriverClient"));
		    return page;
		}
		
	
	// --------------------------------------------------
	// Page Controllers
	// --------------------------------------------------
	
	public DesktopLoginPage desktopLoginPage(String url) throws Exception {
		this.getDesktopWebDriverClient().setURL(url);
		DesktopLoginPage oPage = initPage(DesktopLoginPage.class);
		return oPage;
	}
	
	public DesktopHomePage desktopHomePage() throws Exception {
		DesktopHomePage oPage = initPage(DesktopHomePage.class);
		return oPage;
	}
	
	public MobileHomePage mobileHomePage() throws Exception {
		MobileHomePage oPage = mobileInitPage(MobileHomePage.class);
		return oPage;
	}
	
	public MobileLoginPage mobileLoginPage(String url) throws Exception {
		this.getMobileWebDriverClient().setURL(url);
		MobileLoginPage oPage = mobileInitPage(MobileLoginPage.class);
		return oPage;
	}
	
}