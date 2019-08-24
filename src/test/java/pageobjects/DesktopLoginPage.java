package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class DesktopLoginPage extends BasePage {
	
	public DesktopLoginPage(WebDriver driver) {
		super(driver);
	}
	 
	/* @FindBy(linkText="Sign in")
	 private WebElement signInHeaderButton;*/
	 
	@FindBy(id = "sign-up-in")
	 private WebElement signInHeaderButton;
	 
	 @FindBy(linkText ="Download")
	 private WebElement downloadButton;

	@FindBy(xpath = "//input[contains(@id,'login_email')]")
	 private WebElement userNameTextBox;
	 
	 @FindBy(xpath= "//input[contains(@id,'login_password')]")
	 private WebElement passwordTextBox;
	 
	 @FindBy(xpath = "//button[contains(@class,'login-button')]")
	 private WebElement signInButton;	 
	 
	 private String homePage_Xpath = ".//div//h1[text()='Home']";
	
	public void doLogin() throws Exception {
		//pageWebDriverClient.waitForVisibilityOfElement(downloadButton);
		pageWebDriverClient.click(signInHeaderButton);
		//enter your username and password
		pageWebDriverClient.setText(userNameTextBox, "***********");
		pageWebDriverClient.setText(passwordTextBox, "***********");
		pageWebDriverClient.click(signInButton);
		pageWebDriverClient.waitForVisibilityOfElementLocatedBy(homePage_Xpath);
	}
	 
}
