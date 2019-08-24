package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	        strict = true,
	        features = {"classpath:features"},	        
			plugin = {"pretty","json:smoke/cucumber.json","html:smoke/cucumber.html"},
	        glue = {"stepdefinition"},
	       // tags = {"@mobileWeb"},
	        monochrome = true)
	public class SmokeRunner extends AbstractTestNGCucumberTests {
	}