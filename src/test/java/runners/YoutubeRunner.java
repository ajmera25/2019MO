package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	        strict = true,
	        features = {"classpath:features/youtube.feature"},	        
			plugin = {"pretty","json:smoke/cucumber.json","html:smoke/cucumber.html"},
	        glue = {"stepdefinition"},
	        monochrome = true)
	public class YoutubeRunner extends AbstractTestNGCucumberTests {
	}