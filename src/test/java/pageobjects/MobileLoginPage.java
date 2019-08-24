package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
public class MobileLoginPage extends BasePage {

	public MobileLoginPage(WebDriver driver) {
		super(driver);
	}

	 @FindBy(xpath ="//button[text()='Sign in']")
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
		pageWebDriverClient.click(signInHeaderButton);
		pageWebDriverClient.setText(userNameTextBox, "***********");
		pageWebDriverClient.setText(passwordTextBox, "***********");
		pageWebDriverClient.click(signInButton);
		pageWebDriverClient.waitForVisibilityOfElementLocatedBy(homePage_Xpath);
	}
	 
}
