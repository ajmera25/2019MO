package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class DesktopHomePage extends BasePage {
	
	public DesktopHomePage(WebDriver driver) {
		super(driver);
	}
	 	 
	String rowOptionXpath = "//tr[@data-filename='%s']/td//*[@class='mc-popover']";
	//%s = 'Dropbox.txt'
	
	 @FindBy(xpath= "//span[contains(@class,'popover-content-item') and text()='Share']")
	 private WebElement shareLink;
	 
	 @FindBy(xpath = "//div[contains(@class,'unified-share-modal')]//input[contains(@class,'mc-tokenized-input-input')]")
	 private WebElement sharingModalContact;
	 
	 @FindBy(xpath = "//div[contains(@class,'unified-share-modal')]//button/span[text()='Share']")
	 private WebElement shareButton;
	 
	 @FindBy(xpath ="//div[@class='account-menu mc-popover']")
	 private WebElement accountIcon;
	 
	@FindBy(linkText = "Sign out")
	private WebElement signOut;
	
	@FindBy(xpath = "//a[@id='files']")
	private WebElement filesLink;	
	
	@FindBy(xpath = "//div[contains(@class,'unified-share-modal')]")
	private WebElement shareModal;
	
	@FindBy(xpath = "//img[@class='mc-avatar-image']")
	private WebElement accountImage;
	
	@FindBy(xpath = ".//div[@class='mc-progress-bar-fg']")
	private WebElement progressBar;
	
	public boolean openShareDialogue(String fileName) throws Exception {
		pageWebDriverClient.click(filesLink);
		pageWebDriverClient.waitForVisibilityOfElementLocatedBy(rowOptionXpath.replace("%s", fileName));
		pageWebDriverClient.click(rowOptionXpath.replace("%s", fileName));
		pageWebDriverClient.click(shareLink);
		return true;
	}
	
	public boolean shareFileWithEmail(List<String> email) throws Exception {
		for(String mail : email) {
			pageWebDriverClient.setTextAndEnter(sharingModalContact, mail);
		}
		pageWebDriverClient.click(shareButton);
		return true;
	}

	public boolean shareFileWithEmail(String email) throws Exception {
		pageWebDriverClient.waitForVisibilityOfElement(shareModal);
		pageWebDriverClient.setTextAndEnter(sharingModalContact, email);
		pageWebDriverClient.click(shareButton);
		return pageWebDriverClient.waitForInvisibilityofElementLocatedBy(progressBar);
		
	}
	
	public boolean signOut() throws Exception {
		pageWebDriverClient.click(accountImage);
		return pageWebDriverClient.click(signOut);
	}

	public boolean isFileExist(String fileName) throws Exception {
		pageWebDriverClient.click(filesLink);
		return pageWebDriverClient.isWebElementDisplayed(rowOptionXpath.replace("%s", fileName));
	}
	 
}
