package runners;

import core.TestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	        strict = true,
	        features = {"classpath:features/youtube.feature"},	        
			plugin = {"pretty","json:smoke/cucumber.json","html:smoke/cucumber.html"},
	        glue = {"stepdefinition"},
	        tags = "@nativemobileapp",
	        monochrome = true)
	public class YoutubeRunner extends TestNGCucumberTests {
	}