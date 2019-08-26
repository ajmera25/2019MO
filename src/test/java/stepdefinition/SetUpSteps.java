package stepdefinition;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import core.Controller;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import utils.DriverUtils;


public class SetUpSteps {

	private static boolean initialized = false;
	protected String url;

	protected Controller mmpController;
	
	public static HashMap<String,String> testMap = new HashMap<String,String>();
	
	private static ThreadLocal<RemoteWebDriver> threadMobileWebDriver = new ThreadLocal<RemoteWebDriver>();
	private static ThreadLocal<RemoteWebDriver> threadDesktopWebDriver = new ThreadLocal<RemoteWebDriver>();
	private static ThreadLocal<String> featureFile = new ThreadLocal<String>();
	private static ThreadLocal<String> scenarioName = new ThreadLocal<String>();
	private static ThreadLocal<String> featureGroup = new ThreadLocal<String>();
	private static ThreadLocal<String> threadUrl = new ThreadLocal<String>();
	private static ThreadLocal<String> threadAccountInfo = new ThreadLocal<String>();
	private static ThreadLocal<LinkedHashMap<String,String>> contextVariables = new ThreadLocal<LinkedHashMap<String,String>>();
	
	protected static String testDataPath = null;
	
	
	public WebDriver getMobileWebDriver() {
		WebDriver wdriver = threadMobileWebDriver.get();
		return wdriver;
	}
	
	public WebDriver getDesktopWebDriver() {
		WebDriver wdriver = threadDesktopWebDriver.get();
		return wdriver;
	}
	
	public static void setMobileWebDriver(RemoteWebDriver driver) {
		threadMobileWebDriver.set(driver);
	}
	
	public static void setDesktopWebDriver(RemoteWebDriver driver) {
		threadDesktopWebDriver.set(driver);
	}
	
	
	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		try{
			String currentFeaturePath  = scenario.getUri();
			String currentFeatureName = currentFeaturePath.substring(currentFeaturePath.lastIndexOf("/") +1, currentFeaturePath.lastIndexOf(".")).toLowerCase();
			
			Collection<String> scenarioTag = scenario.getSourceTagNames();
			
			scenarioName.set(scenario.getName().split(":")[0]);
			String hub = System.getProperty("hub");
			String browser = System.getProperty("browser");
			if (!initialized) 
				featureFile.set(currentFeatureName);
			
			Reporter.log("Execution details: [{Feature = " + featureFile.get() + "} , {Scenario = " + scenarioName.get().toUpperCase() + "}, {Hub = " + hub + "}]", true);
			
			initializeDriver(hub,browser,scenarioTag);
			
			testDataPath = getResourcesPath() + "testdata/";
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public void initializeDriver(String hub,String browserName,Collection<String> platform) {
		HashMap<String, Object> plugin = new HashMap<String, Object>();
		String downloadPath = "X:/MMP_Downloads/Client/";
		
		try {
		if (browserName.equalsIgnoreCase("chrome") && platform.contains("@desktopWeb")) {
			ChromeOptions options = new ChromeOptions();
			plugin.put("enabled", false);
			plugin.put("name", "Chrome PDF Viewer");
			
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", downloadPath);
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("plugins.plugins_list", Arrays.asList(plugin));
			
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("start-maximized");
			options.addArguments("--disable-infobars");
			options.addArguments("--dns-prefetch-disable");
			setDesktopWebDriver(new RemoteWebDriver(new URL(hub), options));	
		}else if (browserName.equalsIgnoreCase("chrome") && platform.contains("@mobileWeb")) {
			ChromeOptions mobileoptions = new ChromeOptions();
			HashMap<String, String> mobileEmulation = new HashMap<String, String>();

				mobileEmulation.put("deviceName", "Galaxy S5");
				mobileoptions.setExperimentalOption("mobileEmulation", mobileEmulation);
				setMobileWebDriver(new RemoteWebDriver(new URL(hub), mobileoptions));	
		}else {
			plugin.put("enabled", false);
			plugin.put("name", "Chrome PDF Viewer");
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", downloadPath);
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("plugins.plugins_list", Arrays.asList(plugin));
			
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("start-maximized");
			options.addArguments("--disable-infobars");
			options.addArguments("--dns-prefetch-disable");
			setDesktopWebDriver(new RemoteWebDriver(new URL(hub), options));
			
			ChromeOptions mobileoptions = new ChromeOptions();
			HashMap<String, String> mobileEmulation = new HashMap<String, String>();

				mobileEmulation.put("deviceName", "Galaxy S5");
				mobileoptions.setExperimentalOption("mobileEmulation", mobileEmulation);
				setMobileWebDriver(new RemoteWebDriver(new URL(hub), mobileoptions));	
		}
		mmpController = new Controller(browserName,getDesktopWebDriver(),getMobileWebDriver());
		Reporter.log("Test trigerred from machine: " + System.getenv().get("COMPUTERNAME"), true);
		setContextVariable("screenShot","notRequired");
		//Reporter.log("Executing " + scenarioName.get().toUpperCase() + " on grid node: " +getGridExecutionNode(hub), true);
		initialized = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUrl(String url) {
		setContextVariable("baseURL", url);	
		threadUrl.set(url);		
	}
	
	public String getUrl() {
		this.url = threadUrl.get();
		return url;
	}
	
	public Controller getController() {
		return mmpController;
	}
	
	public static void setAccountInfo(String accountInfo) {
		threadAccountInfo.set(accountInfo);
	}
	
	public static String getContextVariable(String key) {
		return contextVariables.get().get(key);
	}

	public static Set<String> getContextVariableKeys() {
		return contextVariables.get().keySet();
	}
	
	public static void setContextVariable(String key, String value) {
		LinkedHashMap<String, String> contextVarHash = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> existingHash = new LinkedHashMap<String, String>();
		if(contextVariables.get()!=null) existingHash.putAll(contextVariables.get());
		contextVarHash.put(key, value);
		existingHash.putAll(contextVarHash);
		contextVariables.set(existingHash);
	}
	
	public static void setContextVariable(HashMap<String, String> contextVarHash) {
		LinkedHashMap<String, String> existingHash = new LinkedHashMap<String, String>();
		existingHash.putAll(contextVarHash);
		contextVariables.set(existingHash);
	}
	
	public static String getFeatureFile() {
		return featureFile.get();
	}
	
	public static String getScenarioName() {
		return scenarioName.get();
	}

	public static String getFeatureGroup() {
		return featureGroup.get();
	}
	
	public static void setFeatureGroup(String str_featureGroup) {
		featureGroup.set(str_featureGroup);
	}
	
	@After
	public void afterScenario(Scenario scenario) throws Exception {
		initialized = false;
		File directory = new File(".");
		System.out.println("Path in getpath" + directory.getCanonicalPath());
	 	try{
	 		//if(scenario.isFailed()) {
	          //  DriverUtils.takeScreenShot(scenario, getMobileWebDriver());
	           // DriverUtils.takeScreenShot(scenario, getDesktopWebDriver());
	        
		}catch(Exception e){
			Reporter.log("Error in Initializing the test", true);
			e.printStackTrace();
		}
		finally{
			if(mmpController != null){
				mmpController.getDesktopWebDriverClient().quit();
				//mmpController.getMobileWebDriverClient().quit();
			}
			}
    }
	
	
	@AfterStep
	public void afterScnearioStep(Scenario scenario) throws Exception{
		if(getContextVariable("screenShot").equals("required")) {
			DriverUtils.takeScreenShot(scenario, getDesktopWebDriver());
			setContextVariable("screenShot","notRequired");
		}
		
	}
	
	/*public String getGridExecutionNode(String hubUrl){
		String node = null;
		try{
			String hub = hubUrl.split("//")[1].split(":")[0]; // Grid Hub hostname
			if(hub.toUpperCase().contains("LOCALHOST")) return hubUrl;
			
			int port = Integer.parseInt(hubUrl.split("//")[1].split(":")[1].split("/")[0]); // Grid Hub port number
			
			OkHttpClient client = new OkHttpClient();
			
			HttpUrl url = new HttpUrl.Builder()
					.scheme("http")
					.host(hub)
					.port(port)
					.addPathSegments("grid/api/testsession")
					.addQueryParameter("session",threadDriver.get().getSessionId().toString())
					.build();
			
			Request request = new Request.Builder()
				      .url(url)
				      .build();
			
			 Response response = client.newCall(request).execute();
				JSONObject object = new JSONObject(response.body().string());
				String proxyID = (String) object.get("proxyId");
				node = proxyID.split("//")[1].split(":")[0];
		}catch(Exception e){
			Reporter.log("Error in figuring out grid node running this test", true);
			e.printStackTrace();
			
		}
		return node;
	}*/

	public static String getTestDataPath() {
		return testDataPath;
	}
	
	public static String getResourcesPath() {
		if(File.separator.equals("/")){
			return "test-classes/";
		} 
		else{
			return System.getProperty("user.dir") + "/src/test/resources/";
		}
	}
	
}
