package utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.Assert;

import stepdefinition.SetUpSteps;


public class TestFrameworkException extends Exception {
		
	private static final long serialVersionUID = 1L;
	private String exceptionURL = "";
	private SetUpSteps ctxSteps = new SetUpSteps();

	public TestFrameworkException()	{
		super();
	}
	
	public TestFrameworkException(String message){
		try{
			message = message  + Character.toString ((char) 10) + 
					"URL: " + getExceptionURL();
			Assert.fail(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public TestFrameworkException(String message, Exception e){			
		try{
			message = message  + Character.toString ((char) 10) +
					"URL: " + getExceptionURL() + Character.toString ((char) 10) + 
					getStackTrace(e);
			Assert.fail(message);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String getExceptionURL()	{
		if(ctxSteps.getMobileWebDriver() != null){
			exceptionURL = ctxSteps.getMobileWebDriver().getCurrentUrl();
		}else if(ctxSteps.getDesktopWebDriver() != null){
			exceptionURL = ctxSteps.getDesktopWebDriver().getCurrentUrl();
		} 
		return exceptionURL;
	}
	
	public String getStackTrace(Exception e){
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		return exceptionAsString;
	}
	
}
