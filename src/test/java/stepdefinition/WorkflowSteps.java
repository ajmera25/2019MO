package stepdefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIUtils;
import utils.JsonUtil;

public class WorkflowSteps extends BaseSteps{

	// PicoContainer injects class ContextSteps
		public WorkflowSteps(SetUpSteps ctx) {
		   super(ctx);
		}
		
	@When("User open youtube url {string}")
	public void user_open_youtube_url(String url) throws Throwable{
		mmpController.YoutubeDesktopPage().setUrl(url);
	}
	
	@When("User Search for {string}")
	public void search_youtube(String searchText) throws Throwable{
		mmpController.YoutubeDesktopPage().search(searchText);
	}
	
	@When("User open {string} channel")
	public void user_open_channel(String selectChannel) throws Throwable{
		mmpController.YoutubeDesktopPage().openChannel(selectChannel);
	}
	
	@When("User navigate to {string} tab")
	public void user_navigate_to_channel_tab(String channelMenu) throws Throwable{
		mmpController.YoutubeDesktopPage().selectHeader(channelMenu);
	}
	
	@When("User fetch the video name {string} from the api call {string}")
	public void fetch_the_video_name(String videoName,String apiUrl) throws Throwable{
		SetUpSteps.setContextVariable(videoName,APIUtils.fetchVideoName());
		System.out.println(SetUpSteps.getContextVariable(videoName));
	}

	@When("Locate the video {string} fetch from the api call")
	public void locate_the_video_fetch_from_api_call(String videoName) throws Throwable{
		mmpController.YoutubeDesktopPage().locateVideoToCentre(SetUpSteps.getContextVariable(videoName));
	}
	
	@Then("Capture the screenshot")
	public void capture_screenshot() throws Throwable{
		SetUpSteps.setContextVariable("screenShot","required");	
	}
	
	@When("User click the video {string}")
	public void user_click_video(String videoName) throws Throwable{
		mmpController.YoutubeDesktopPage().selectVideo(SetUpSteps.getContextVariable(videoName));	
	}
	
	@When("Change the video quality to {string}")
	public void change_the_video_quality(String videoQuality) throws Throwable{
		mmpController.YoutubeDesktopPage().changeVideoQuality(videoQuality);
	}
	
	@When("Get the list of upcoming videos")
	public void Get_list_of_upcoming_videos() throws Throwable{
		JsonUtil.createJson(mmpController.YoutubeDesktopPage().getUpcomingVideos());
	}
	
	
}