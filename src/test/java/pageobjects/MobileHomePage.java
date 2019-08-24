package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class MobileHomePage extends BasePage {
	
	public MobileHomePage(WebDriver driver) {
		super(driver);
	}
	 	 
	String rowOptionXpath = "//a[text()='%s']/ancestor::div[@class='recents-item__wrapper']//div[@class='mc-popover']";
	
	String sharedFilename = "//div[@class='unread-item__text']//*[text()='%s']";
	
	 @FindBy(xpath= "//button[contians(text(),'Share')]")
	 private WebElement shareLink;
	 
	 @FindBy(xpath = "//div[@class='db-modal-box']//textarea[@id='unified-share-modal-contacts-tokenizer']")
	 private WebElement sharingTextArea;
	 
	 @FindBy(xpath = "//div[@class='db-modal-box']//button[text()='Share']")
	 private WebElement shareButton;
	 
	 
	 @FindBy(xpath ="//div[@class='account-menu mc-popover']")
	 private WebElement accountIcon;
	 
	@FindBy(xpath ="//a[text()='Sign out']")
	private WebElement signOut;

	public WebElement getShareLink() {
		return shareLink;
	}

	public void setShareLink(WebElement shareLink) {
		this.shareLink = shareLink;
	}

	public WebElement getSharingTextArea() {
		return sharingTextArea;
	}

	public void setSharingTextArea(WebElement sharingTextArea) {
		this.sharingTextArea = sharingTextArea;
	}

	public WebElement getShareButton() {
		return shareButton;
	}

	public void setShareButton(WebElement shareButton) {
		this.shareButton = shareButton;
	}

	public WebElement getAccountIcon() {
		return accountIcon;
	}

	public void setAccountIcon(WebElement accountIcon) {
		this.accountIcon = accountIcon;
	}

	public WebElement getSignOut() {
		return signOut;
	}

	public void setSignOut(WebElement signOut) {
		this.signOut = signOut;
	}
	
	
	public boolean openShareDialogue(String filename) throws Exception {
		pageWebDriverClient.click(rowOptionXpath.replace("%s", filename));
		pageWebDriverClient.click(shareLink);
		return true;
	}
	
	public boolean shareFileWithEmail(List<String> email) throws Exception {
		for(String mail : email) {
			pageWebDriverClient.setTextAndEnter(sharingTextArea, mail);
		}
		pageWebDriverClient.click(shareButton);
		return true;
	}
	
	public boolean checkSharedFile(String filename) {
		return pageWebDriverClient.isWebElementDisplayed(sharedFilename.replace("%s", filename));
		
	}

	 
	 
}
