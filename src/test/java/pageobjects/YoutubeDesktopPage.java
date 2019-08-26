package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class YoutubeDesktopPage extends BasePage {
	
	public YoutubeDesktopPage(WebDriver driver) {
		super(driver);
	}
	
	 @FindBy(id= "search")
	 private WebElement searchInputButton;
	 
	 @FindBy(id= "search-icon-legacy")
	 private WebElement searchButton;
	 
	
	 private String videoLocator = ".//a[text()='%s']";
	 private String channel_xpath = ".//h3[@id='channel-title']/span[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='%s']";
	 private String menu_xpath = "//paper-tab[contains(*,'%s')]";
	 private String settings = "//button[@class = 'ytp-button ytp-settings-button']";
	 private String menu ="//div[@class= 'ytp-popup ytp-settings-menu']//div[@class='ytp-menuitem-label' and text()='Quality']";
	 private String quality_panel="//div[@class= 'ytp-panel ytp-quality-menu']//*[text()='%s']";
     private String upcomingVideos = "//div[@id='items']//*[contains(@class,'style-scope ytd-watch-next-secondary-results-renderer')]";
     private String spinner = "//div[@id='items']/following-sibling::div[@id='continuations']//*[@id='spinner']";
     private String upcomingVideosName = "//div[@id='items']//*[contains(@class,'style-scope ytd-watch-next-secondary-results-renderer')]//span[@id='video-title']";
     
	 public void setUrl(String searchText) throws Exception {
			pageWebDriverClient.setURL("https://www.youtube.com/");
		}
	 
	public void search(String searchText) throws Exception {
		pageWebDriverClient.waitForVisibilityOfElement(searchInputButton);
		pageWebDriverClient.setText(searchInputButton, searchText);
		pageWebDriverClient.click(searchButton);
	}
	
	public void openChannel(String channelName) throws Exception {
		pageWebDriverClient.waitForVisibilityOfElementLocatedBy(String.format(channel_xpath, channelName));
		pageWebDriverClient.click(String.format(channel_xpath,channelName));
	}	
	
	public void selectHeader(String headerName) throws Exception {
		pageWebDriverClient.waitForVisibilityOfElementLocatedBy(String.format(menu_xpath, headerName));
		pageWebDriverClient.click(String.format(menu_xpath,headerName));
	}	
	
	public void locateVideoToCentre(String videoName) throws Exception{
		//pageWebDriverClient.findElement(String.format(videoLocator, videoName));
		pageWebDriverClient.scrollWindowToFindElement(String.format(videoLocator, videoName));
		pageWebDriverClient.scrollElementToCentre(pageWebDriverClient.findElement(String.format(videoLocator, videoName)));
	}
	
	public void selectVideo(String videoName) throws Exception{
		pageWebDriverClient.waitForElementToBeClickable(String.format(videoLocator, videoName));
		pageWebDriverClient.click(String.format(videoLocator, videoName));
	}
	
	public void changeVideoQuality(String videoQuality) throws Exception{
		pageWebDriverClient.waitForVisibilityOfElementLocatedBy(settings);
        pageWebDriverClient.click(settings);
        pageWebDriverClient.waitForVisibilityOfElementLocatedBy(menu);
        pageWebDriverClient.click(menu);
        pageWebDriverClient.waitForVisibilityOfElementLocatedBy(String.format(quality_panel, videoQuality));
        pageWebDriverClient.click(String.format(quality_panel, videoQuality));
	}
	
	public List<String> getUpcomingVideos() throws Exception{
		int count = pageWebDriverClient.findElements(upcomingVideos).size();
		List<String> upcomingVideosNamesList = new ArrayList<String>();
		while(true) {
			pageWebDriverClient.scrollWindow();
			count = pageWebDriverClient.findElements(upcomingVideos).size();
			pageWebDriverClient.scrollWindow();
			count = pageWebDriverClient.findElements(upcomingVideos).size();
			if(!pageWebDriverClient.isWebElementDisplayed(spinner) && count == pageWebDriverClient.findElements(upcomingVideos).size())
				break;
			count = pageWebDriverClient.findElements(upcomingVideos).size();
		}
		System.out.println("Total Videos: " + count);
		for(int i=1;i<=count;i++) {
			upcomingVideosNamesList.add(pageWebDriverClient.getText("(" + upcomingVideosName + ")[" + i + "]"));
		}
		return upcomingVideosNamesList;
	}
}
