package stepdefinition;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.DesktopLoginPage;
import pageobjects.MobileLoginPage;

public class WorkflowSteps extends BaseSteps{

	// PicoContainer injects class ContextSteps
		public WorkflowSteps(SetUpSteps ctx) {
		   super(ctx);
		}
		
	@Given("{string} logs in to Dropbox web application.")
	public void logs_in_to_Dropbox_web_application(String string) throws Throwable{
		DesktopLoginPage loginPage = mmpController.desktopLoginPage("https://www.dropbox.com");
		loginPage.doLogin();
	}
	
	@Given("{string} logs in to Dropbox mobile application.")
	public void logs_in_to_Dropbox_mobile_application(String string) throws Throwable{
		MobileLoginPage loginPage = mmpController.mobileLoginPage("https://www.dropbox.com");
		loginPage.doLogin();
	}

	@When("\"([^\"]*)\" shares the \"([^\"]*)\" with dropbox \"([^\"]*)\"$")
	public void shares_with_dropbox_user(String string, String string2, String string3) throws Throwable{
		mmpController.desktopHomePage().openShareDialogue("fileshare.txt");
		mmpController.desktopHomePage().shareFileWithEmail("prachi20jain@gmail.com");
	}

	@Then("{string} receives the {string} on {string}")
	public void verify_user_receives_file(String string, String string2, String string3) throws Throwable{
		Assert.assertTrue(mmpController.desktopHomePage().isFileExist("fileshare.txt"),"file do not received by user 2"); 
	}
	
	@Then("{string} sign out from the application")
	public void sign_out_from_the_application(String string) throws Throwable{
		mmpController.desktopHomePage().signOut(); 
	}
}