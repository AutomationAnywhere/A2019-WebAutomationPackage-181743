package com.automationanywhere.botcommand.utils;




import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.google.common.io.Files;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//import com.microsoft.edge.seleniumtools.EdgeDriver;


public class BrowserUtils {
	
	
	public static final String CSS = "CSS";
	public static final String XPATH = "XPATH";
	public static final String TAG = "TAG";
	public static final String ID = "ID";
	public static final String JS = "JS";
	
	public static final String Click = "Click";
	public static final String DoubleClick = "DoubleClick";
	public static final String RightClick = "RightClick";

	public Boolean hasTableHeader = false;
	
	private static final Logger logger = LogManager.getLogger(BrowserUtils.class);

	
	
	public BrowserUtils() { 

	}


   public String getPageContent(WebDriver driver) {
    			
    	String html = driver.getPageSource();
		return html;
    	   
    }
   
   public void openURL(WebDriver driver,String url) {
		
   		driver.get(url);
   	   
   }
   
   public void doClick(WebDriver driver,String search, String type,String clickType, String library, Integer timeout,String attribute) throws Exception {
	   	if (type.equals(BrowserUtils.JS)) {
		    String jspath = getJSScript(search, type);
		    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);	
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    jsExecutor.executeScript(library+"\r\n"+jspath+".click()");
	   	}
	   	else {
	   		WebElement element = findElement(driver, search, type);	
	   		if( element != null ) {
	   			String jspath = getJSScript(search, type);
	   			Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
	   			Actions actions = new Actions(driver);
	   			switch (clickType) {
		   			case Click: actions.click(element).perform();
		   						break;
		   			case DoubleClick: actions.doubleClick(element).perform();
								break;
		   			case RightClick: actions.contextClick(element).perform();
								break;

	   			  }

	   	   }
	   	   
	   	}

   }
   
   
   
   public void doSubmit(WebDriver driver,String search,String type, String library, Integer timeout,String attribute) throws Exception {
	   	if (type.equals(BrowserUtils.JS)) {
		    String jspath = getJSScript(search, type);
		    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);	
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    jsExecutor.executeScript(library+"\r\n"+jspath+".submit()");
	   	}
	   	else {
	   		WebElement element = findElement(driver, search, type);	
	   		if( element != null ) {
	   			String jspath = getJSScript(search, type);
	   			Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
	   			element.submit();
	   		}
	   	}
   }
   
   
   
   public void doCheck(WebDriver driver,String search,String type, String library, Boolean check,Integer timeout,String attribute) throws Exception {
		
	    String jspath = getJSScript(search, type);
	   
	    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    if (check) {
	    	jsExecutor.executeScript(library+"\r\n"+jspath+".checked = true");
	    }
	    else {
	    	jsExecutor.executeScript(library+"\r\n"+jspath+".checked = false");
	    }

   }
   
   public void doSelect(WebDriver driver,String search,String type,String library , String newvalue,Integer timeout,String attribute) throws Exception {
	    String jspath = getJSScript(search, type);
	    Boolean status = waitforElement(driver, jspath, library,timeout, attribute);
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    String script = library+"\r\n function internalsetSelected(el, newvalue) {\r\n" + 
	    		"    for (var i = 0; i < el.options.length; ++i) {\r\n" + 
	    		"        if (el.options[i].text === newvalue)\r\n" + 
	    		"            el.options[i].selected = true;\r\n" + 
	    		"    }\r\n" + 
	    		"}\r\n" +
	    		"newvalue = \""+newvalue+"\";el ="+ jspath+";internalsetSelected(el	,newvalue);";
	    jsExecutor.executeScript(script);
   }
   
   public void scrollIntoView(WebDriver driver,String search,String type,String library) throws Exception {
	    String jspath = getJSScript(search, type);
	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   jsExecutor.executeScript(library+"\r\n element = "+jspath+";element.scrollIntoView(true);");

   	}
   
   public String getValue(WebDriver driver,String search,String type,String library,Integer timeout,String attribute) throws Exception {
	    String jspath = getJSScript(search, type); 
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
   
   
   public String getText(WebDriver driver,String search,String type, String library, Integer timeout,String attribute) throws Exception {
		
	    String jspath = getJSScript(search, type);
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
   
   
   
   public String doFocus(WebDriver driver,String search,String type, String library, Integer timeout,String attribute) throws Exception {
		
	    String jspath = getJSScript(search, type);
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
   
   
   public Boolean isPageLoaded(WebDriver driver) {
	   String jsQuery = "function pageLoaded() "
	   + "{var loadingStatus=(document.readyState=='complete');"
	   + "return loadingStatus;};"
	   + "return pageLoaded()";

	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   return (Boolean)jsExecutor.executeScript(jsQuery);
	 }
   
   
   public Boolean waitUntilPageLoaded(WebDriver driver,Integer seconds) throws Exception {
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
   
   
   public Boolean waitUntilElementLoaded(WebDriver driver,String search, String type , String library, Integer seconds,String attribute) throws Exception {
	   String jspath = getJSScript(search, type);
	   Boolean status = waitforElement(driver, jspath, library,seconds, attribute);
	   return status;
   }
   
   
   

   public void setValue(WebDriver driver,String search,String type, String library, String newvalue,Integer timeout,String attribute) throws Exception {
	
	   String jspath = getJSScript(search, type);
	    
	   Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   jsExecutor.executeScript(library+"\r\n"+jspath+".value=\""+newvalue+"\"");
   }

   public String executeJS(WebDriver driver,String js) throws Exception {
	
	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	   String value = (String)jsExecutor.executeScript(js);
	   return (value != null) ? value.toString() : "" ;

   }
	    


   public String getCurrentURL(WebDriver driver) throws Exception {
	
	   return driver.getCurrentUrl();


   }

   public String getCurrentSessionID(WebDriver driver, String browser) throws Exception {
	   String id = "";
	   if (browser == "chrome") {
		   id = ((ChromeDriver)driver).getSessionId().toString();
	   }
	   if (browser == "edge") {
		   id = ((EdgeDriver)driver).getSessionId().toString();
	   }
	   
	   
	   return id;

   }

   private Boolean waitforElement(WebDriver driver, String js,String library, Integer seconds,String attribute) {
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


   public void quit(WebDriver driver) throws Exception {
		
	   
	    driver.quit();

   }
   

   public void clearInput(WebDriver driver,String search, String type, String library,Integer timeout,String attribute) throws Exception 
   {
	   WebElement element = findElement(driver, search, type);
	   if (element != null)  {
		   String jspath = getJSScript(search, type);
		   Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
		   element.clear();

	   }
   }
   
   
   public void draganddrop(WebDriver driver,String search1,String search2,String type,String library,Integer timeout,String attribute) throws Exception 
   {
	   
		  WebElement fromElement = findElement(driver, search1, type);
		  WebElement toElement = findElement(driver, search2, type);
		  if( fromElement != null && toElement != null) {
			   String jspath = getJSScript(search1, type);
			   Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
			  Actions actions = new Actions(driver);
			  actions.dragAndDrop(fromElement, toElement).perform();
		  }

   }
   
   
   
   
   public void clickandHold(WebDriver driver,String search,String type,String library,Integer timeout,String attribute) throws Exception 
   {
	   
		  WebElement element = findElement(driver, search, type);	
		  if( element != null ) {
			  String jspath = getJSScript(search, type);
			  Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
			  Actions actions = new Actions(driver);
			  actions.clickAndHold(element).perform();
		  }

   }
   
   
   public void moveTo(WebDriver driver,String search,String type,String library,Integer timeout,String attribute) throws Exception 
   {
	   
		  WebElement element = findElement(driver, search, type);	
		  if( element != null ) {
			  String jspath = getJSScript(search, type);
			  Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
			  Actions actions = new Actions(driver);
			  actions.moveToElement(element).perform();
		  }

   }
   
   
   
   public void release(WebDriver driver) throws Exception 
   {
	  
		Actions actions = new Actions(driver);
		actions.release().perform();

   }
   
   
   public Map<String,String> elementDetails(WebDriver driver,String search,String type,String filename, String library,Integer timeout,String attribute) throws Exception 
   {
	   
		  WebElement element = findElement(driver, search, type);	
		  HashMap<String,String> details = new HashMap<String,String>();
		  if( element != null ) {
			  String jspath = getJSScript(search, type);
			  Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
			  String elAttribute = element.getTagName();
			  if (elAttribute != null) {
				  details.put("tag", elAttribute);
			  }
			  elAttribute = element.getText();
			  if (elAttribute != null) {
				  details.put("text", elAttribute);
			  }
			  
			  elAttribute = element.getAttribute("value");
			  if (elAttribute != null) {
				  details.put("value", elAttribute);
			  }
			  
			  elAttribute = element.getAttribute("class");
			  if (elAttribute != null) {
				  details.put("class", elAttribute);
			  }
			  
			  elAttribute = ((Integer)element.getRect().getX()).toString();
			  if (elAttribute != null) {
				  details.put("topX",elAttribute);
			  }
			  
			  elAttribute = ((Integer)element.getRect().getY()).toString();
			  if (elAttribute != null) {
				  details.put("topY", elAttribute);
			  }
			  elAttribute = ((Integer)element.getRect().getHeight()).toString();
			  if (elAttribute != null) {
				  details.put("height", elAttribute);
			  }
			  elAttribute = ((Integer)element.getRect().getWidth()).toString();
			  if (elAttribute != null) {
				  details.put("width", elAttribute);
			  }
			  
			  if (!filename.equals("")) {
				  File screenshot = element.getScreenshotAs(OutputType.FILE);
				  if (screenshot != null) {
					  File copied = new File(filename);
					  Files.copy(screenshot, copied);
				  }
			  }
			  

	
		  }
		  
		  return details;

   }
   
   public void switchToFrame(WebDriver driver,String search,String type,String library,Integer timeout,String attribute) throws Exception 
   {
	   
		WebElement frameelement = findElement(driver, search, type);
		if (frameelement != null) {
			String jspath = getJSScript(search, type);
			Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
			driver.switchTo().frame(frameelement);
		}
   }
   
   
   public void switchToWindow(WebDriver driver,String windowHandle) throws Exception 
   {
	   
	   driver.switchTo().window(windowHandle);
   }
   
   
   public List<String> getWindowHandles(WebDriver driver) throws Exception 
   {
	   Set<String> handles = driver.getWindowHandles();
	   return new ArrayList<String>(handles);
   }
   
   public String getCurrentWindowHandle(WebDriver driver) throws Exception 
   {
	   return driver.getWindowHandle();
   }
   
   
   
   public void toDefault(WebDriver driver) throws Exception 
   {
		driver.switchTo().defaultContent();

   }
   
   public void sendKeys(WebDriver driver,String search, String type, String text,String library,Integer timeout,String attribute) throws Exception 
   {
	   
	   char[] characters = text.toCharArray();
	   boolean capslockpressed = false;
	   
	   Actions action = new Actions(driver);
	   WebElement element = findElement(driver, search, type);
	   Robot robot = new Robot();
	   if (element != null)  {
		   String jspath = getJSScript(search, type);
		   Boolean status = waitforElement(driver, jspath, library, timeout, attribute);
		   for (int i = 0; i < characters.length; i++) {
			   if (characters[i] == '[') {
				   String key = "";
				   boolean endbracket = false;
				   int j = i;
				   while (j<characters.length  && !endbracket) {
					   key = text.substring(i, j+1);
					   if (characters[j] == ']') {
						   endbracket = true;
							 switch (key) {
							  case "[CTRL DOWN]": action = action.keyDown(Keys.CONTROL);
						      					break;
						      case "[CTRL UP]":  action = action.keyUp(Keys.CONTROL);
		      									break;
						      case "[SHIFT DOWN]": action = action.keyDown(Keys.SHIFT);
		      									break;
						      case "[SHIFT UP]": action = action.keyUp(Keys.SHIFT);
												break;
						      case "[ALT DOWN]": action = action.keyDown(Keys.ALT);
						      					break;
						      case "[ALT UP]":    action = action.keyUp(Keys.ALT);
						      					break;
						      case "[ALT-GR DOWN]": action = action.keyDown(Keys.CONTROL).keyDown(Keys.ALT);
												break;
						      case "[ALT-GR UP]": action = action.keyUp(Keys.ALT).keyUp(Keys.CONTROL);
		      									break;
						      case "[PAGE DOWN]": action = action.sendKeys(Keys.PAGE_DOWN);
		      									break;
						      case "[PAGE UP]": action = action.sendKeys(Keys.PAGE_UP);
		      									break;
						      case "[ENTER]":   action = action.sendKeys(Keys.ENTER);
												break;
						      case "[BACKSPACE]": action = action.sendKeys(Keys.BACK_SPACE);
												break;
						      case "[CAPS-LOCK]": if (!capslockpressed) {
						      						action = action.keyDown(Keys.SHIFT);
							      					capslockpressed = true;
						      					} else
						      					{
						      						action = action.keyUp(Keys.SHIFT);
						      						capslockpressed = false;
						      					}
												break;
						      case "[INSERT]": 	action = action.sendKeys(Keys.INSERT);
												break;
						      case "[DELETE]": 	action = action.sendKeys(Keys.DELETE);
												break;
						      case "[HOME]": 	action = action.sendKeys(Keys.HOME);
												break;
						      case "[PAUSE]": 	action = action.sendKeys(Keys.PAUSE);
												break;
						      case "[DOLLAR]": 	action = action.sendKeys("$");
												break;
						      case "[TAB]": 	action = action.sendKeys(Keys.TAB);
												break;
						      case "[END]": 	action = action.sendKeys(Keys.END);
												break;
						      case "[LEFT-ARROW]": action = action.sendKeys(Keys.ARROW_LEFT);
												break;
						      case "[RIGHT-ARROW]": action = action.sendKeys(Keys.ARROW_RIGHT);
												break;
						      case "[UP-ARROW]": action = action.sendKeys(Keys.ARROW_UP);
												break;
						      case "[DOWN-ARROW]": action = action.sendKeys(Keys.ARROW_DOWN);
												break;	
						      case "[ESC]": 	action = action.sendKeys(Keys.ESCAPE);
												break;
						      case "[F1]": 		robot.keyPress(KeyEvent.VK_F1);
												break;
						      case "[F2]": 		robot.keyPress(KeyEvent.VK_F2);
						      					break;
						      case "[F3]": 		robot.keyPress(KeyEvent.VK_F3);
												break;
						      case "[F4]": 		robot.keyPress(KeyEvent.VK_F4);
												break;
						      case "[F5]": 		robot.keyPress(KeyEvent.VK_F5);
												break;
						      case "[F6]": 		robot.keyPress(KeyEvent.VK_F6);
												break;
						      case "[F7]": 		robot.keyPress(KeyEvent.VK_F7);
												break;
						      case "[F8]": 		robot.keyPress(KeyEvent.VK_F8);
												break;
						      case "[F9]": 		robot.keyPress(KeyEvent.VK_F9);
												break;
						      case "[F10]": 	robot.keyPress(KeyEvent.VK_F10);
												break;
						      case "[F11]": 	robot.keyPress(KeyEvent.VK_F11);
												break;
						      case "[F12]": 	robot.keyPress(KeyEvent.VK_F12);
												break;					
						      default:			action.sendKeys(key);
						    	  				break;
						   }
					   }
					   j++;
				   }
				   if (!endbracket) {
					   action.sendKeys(key);
					   i = j;
				   }
				   else {
					   i = j-1;
				   }
	
				   
			   }
			   else {
				   action.sendKeys(Character.toString(characters[i]));
			   }
		   }
			
		   action.sendKeys(element,"").perform();

	   }

   }

   
  private WebElement findElement(WebDriver driver,String search, String type) {
	   WebElement element = null;
	   try {
	       switch (type) {
	       case ID: 	element = driver.findElement(By.id(search));
	       				break;
	       case CSS: 	element = driver.findElement(By.cssSelector(search));
						break;
	       case XPATH: element = driver.findElement(By.xpath(search));
						break;
	       case TAG: element = driver.findElement(By.tagName(search));
						break;
	       }
	   }
	   catch (Exception e) {
		   
	   }
       return element;

   }
  
  
  private String getJSScript(String search, String type) {
	  
	  String js = null;
	  search = search.replaceAll("\"", "'");
	  switch (type) {
	      case ID: 	js = "document.getElementById(\""+search+"\")";
	      				break;
	      case CSS: 	js = "document.querySelector(\""+search+"\")";
						break;
	      case XPATH: js = "document.evaluate(\""+search+"\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue";
						break;
	      case TAG:   js = "document.getElementsByTagName(\""+search+"\")[0]";
						break;
	      case JS:   js = search;
						break;
	      }
	  
	  return js;
	  
  }


  public List<Row> getTable(WebDriver driver,String search,String type, String library, Integer timeout,String attribute) throws Exception {
		
	    String jspath = getJSScript(search, type);
	    Boolean status = waitforElement(driver, jspath, library, timeout, attribute);   
	    WebElement table = findElement(driver, search, type);

	  	String bodyHTML = table.getAttribute("outerHTML");
	  	
	    
	  	Document bodyObj = Jsoup.parse(bodyHTML);
	  	
	    Element bodyElement = bodyObj.body();
	    
	    System.out.println(bodyElement.toString());
	    
	    List<Row> rows = new ArrayList<Row>();	 
	    
	   
	    List<Element> header = bodyElement.select("thead");

	    if (header.size() > 0) {
	    	if (header.get(0).select("tr").size() > 0) {
		    	Element headerRow = header.get(0).select("tr").get(0);
		    	List<Value> headers = getHeaders(headerRow);
		    	rows.add(new Row(headers));
	    	}
	    	
	    }


	    List<Element> body = bodyObj.select("tbody");

	    if (body.size() > 0) {
	    	bodyElement = body.get(0);
		    
	    }

	    rows = getRows(bodyElement,rows);



		return rows;

   }
  
  	private List<Value> getHeaders(Element headerrow) {
  		
  		List<Value> headerValues = new ArrayList<Value>();
    	List<Element> cellRow = headerrow.select("th");
    	if (cellRow.size()> 0) {
    		for (Iterator iterator2 = cellRow.iterator(); iterator2.hasNext();) {
    			Element cell = (Element) iterator2.next();
    			headerValues.add(new StringValue(cell.text()));
    		}
    		this.hasTableHeader = true;
    	 }
		 return headerValues;
  	}
  	
  	
  	
  	private List<Row>  getRows(Element body,List<Row> rows ) {
  		
  		List<Element> allRows = body.select("tr");
		List<CompletableFuture<Row>> cfs = new ArrayList<>();
	    
	  	ExecutorService executor = Executors.newFixedThreadPool(50, new ThreadFactory() {
	  	    int count = 1;

	  	    @Override
	  	    public Thread newThread(Runnable runnable) {
	  	        return new Thread(runnable, "table-executor-" + count++);
	  	    }
	  	});
	  	
	  	

 	    for (Iterator iterator = allRows.iterator(); iterator.hasNext();) {
 			 Element row = (Element) iterator.next();
 			 cfs.add(CompletableFuture.completedFuture(row).thenApplyAsync(s -> {
 				 List<Value> rowValues = new ArrayList<Value>();
 				 if (row.select("th").size() > 0 && !this.hasTableHeader) {
 					 rowValues = getHeaders(row);
 				 }
 				 else {
 					 List<Element> cellRow = row.select("td");
 					 for (Iterator iterator2 = cellRow.iterator(); iterator2.hasNext();) {
 						 Element cell = (Element) iterator2.next();
 						 rowValues.add(new StringValue(cell.text()));
 					 }
 				 }
 				 return new Row(rowValues);
 			},executor));
 		}
 	    Boolean allDone;
 	    
 	    do {
 	 	   allDone = true;
 	 	    for (Iterator iterator = cfs.iterator(); iterator.hasNext();) {
 				CompletableFuture<Row> completableFuture = (CompletableFuture<Row>) iterator.next();
 				if (!completableFuture.isDone()) {
 					allDone = false;
 				}
 			  
 	 	   }
			
		} while (!allDone);
 	    
	    
 	    for (Iterator iterator = cfs.iterator(); iterator.hasNext();) {
			CompletableFuture<Row> completableFuture = (CompletableFuture<Row>) iterator.next();
 			    rows.add(completableFuture.getNow(null));	
 		}

 	    
    	return rows;
  	}
  	
  	
  	
  	
  	
  	


	    
	
}
    