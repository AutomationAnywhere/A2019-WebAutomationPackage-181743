	package com.automationanywhere.botcommand.sk;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.WebDriverWait;


public class BrowserUtils {
	

	private static final Logger logger = LogManager.getLogger(BrowserUtils.class);

	private ClassLoader runtimeClassloader;
	
	
	public BrowserUtils() { 

	}


   public String getPageContent(ChromeDriver driver) {
    			
    	String html = driver.getPageSource();
		return html;
    	   
    }
   
   public void openURL(ChromeDriver driver,String url) {
		
   		driver.get(url);
   	   
   }
   
   public void doClick(ChromeDriver driver,String jspath,String library, Integer timeout,String attribute) throws Exception {
	    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);	
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    jsExecutor.executeScript(library+"\r\n"+jspath+".click()");

   }
   
   
   public void doCheck(ChromeDriver driver,String jspath,String library, Boolean check,Integer timeout,String attribute) throws Exception {
		
	   
	    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    if (check) {
	    	jsExecutor.executeScript(library+"\r\n"+jspath+".checked = true");
	    }
	    else {
	    	jsExecutor.executeScript(library+"\r\n"+jspath+".checked = false");
	    }

   }
   
   public void doSelect(ChromeDriver driver,String jspath,String library , String newvalue,Integer timeout,String attribute) throws Exception {
		
	    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    String script = library+"\r\n\function internalsetSelected(el, newvalue) {\r\n" + 
	    		"    for (var i = 0; i < el.options.length; ++i) {\r\n" + 
	    		"        if (el.options[i].text === newvalue)\r\n" + 
	    		"            el.options[i].selected = true;\r\n" + 
	    		"    }\r\n" + 
	    		"}\r\n" +
	    		"newvalue = \""+newvalue+"\";el ="+ jspath+";internalsetSelected(el	,newvalue);";
	    jsExecutor.executeScript(script);
   }
   
   public void scrollIntoView(ChromeDriver driver,String jspath,String library) throws Exception {
		
	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   jsExecutor.executeScript(library+"\r\n element = "+jspath+";element.scrollIntoView(true);");

   	}
   
   public String getValue(ChromeDriver driver,String jspath,String library,Integer timeout,String attribute) throws Exception {
	    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String  value = "";
		try {
	      value = (String) jsExecutor.executeScript(library+"\r\n var value = "+jspath+".value;return value");
		}
		catch (Exception e)  {
			value = "";
		}

		return value;

   }
   
   
   public String getText(ChromeDriver driver,String jspath,String library, Integer timeout,String attribute) throws Exception {
		
	   
	    Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String  value = "";
		try {
	      value = (String) jsExecutor.executeScript(library+"\r\n var value = "+jspath+".innerText;return value");
		}
		catch (Exception e)  {
			value = "";
		}

		return value;

   }
   
   
   
   public String doFocus(ChromeDriver driver,String jspath,String library, Integer timeout,String attribute) throws Exception {
		
	   
	    Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String  value = "";
		try {
	      value = (String) jsExecutor.executeScript(library+"\r\n"+ jspath+".focus();");
		}
		catch (Exception e)  {
			value = "";
		}

		return value;

  }
   
   
   public Boolean isPageLoaded(ChromeDriver driver) {
	   String jsQuery = "function pageLoaded() "
	   + "{var loadingStatus=(document.readyState=='complete');"
	   + "return loadingStatus;};"
	   + "return pageLoaded()";

	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   return (Boolean)jsExecutor.executeScript(jsQuery);
	 }
   
   
   public Boolean waitUntilPageLoaded(ChromeDriver driver,Integer seconds) throws Exception {
	   Boolean isLoaded = false;
	   
	   for (int i = 0; i < seconds; i++) {
		     isLoaded = isPageLoaded(driver);
		     if (isLoaded) {
		    	 break;
		     }
		 Thread.currentThread().sleep(1000);
	   }

	   return isLoaded;
	 }
   
   
   public Boolean waitUntilElementLoaded(ChromeDriver driver,String js, String library, Integer seconds,String attribute) throws Exception {
	    
	   Boolean status = waitforElement(driver, js, library,seconds, attribute);
	   return status;
   }
   
   
   

   public void setValue(ChromeDriver driver,String jspath,String library, String newvalue,Integer timeout,String attribute) throws Exception {
	
	   Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   jsExecutor.executeScript(library+"\r\n"+jspath+".value=\""+newvalue+"\"");
   }

   public String executeJS(ChromeDriver driver,String js) throws Exception {
	
	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   String value = (String)jsExecutor.executeScript(js);
	   return (value != null) ? value.toString() : "" ;

   }
	    


   public String getCurrentURL(ChromeDriver driver) throws Exception {
	
	   return driver.getCurrentUrl();

   }

   public String getCurrentSessionID(ChromeDriver driver) throws Exception {
	
	   return driver.getSessionId().toString();

   }

   private Boolean waitforElement(ChromeDriver driver, String js,String library, Integer seconds,String attribute) {
	   Boolean status=false;
	   if (seconds > 0)
	   {	   
		   try {
			  WebDriverWait wait = new WebDriverWait(driver, seconds,500);
			  JavascriptExecutor jsexec = (JavascriptExecutor) driver;
	   
			  final Object[] out = new Object[1];
			  status = wait.until(new ExpectedCondition<Boolean>() {
			   @Override
			   public Boolean apply(WebDriver d) {
				   try {
					   out[0] = jsexec.executeScript(library+"\r\n return "+js+"."+attribute);
				   } catch (Exception e) {
					   out[0] = null;
				   }
				   return out[0] != null;
			   }
			  });

		   }	
		   catch(Exception e)
		   {
			   status = false;
		   }	
	   }
	   else {
		   status = true;
	   }
	   
	   return status;
   }
 

}
    