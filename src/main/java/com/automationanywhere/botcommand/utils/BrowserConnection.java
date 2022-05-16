package com.automationanywhere.botcommand.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;




public class BrowserConnection {


		private WebDriver driver;
		private String library;
		private String browser ;
		private Date lastRelease = new Date(System.currentTimeMillis()-3600000L); // minus one hour;
		
		public BrowserConnection(String driverpath, String profilePath, String browser, Boolean headless, Integer port,String libraryCode, Integer height, Integer width) throws Exception {

		    this.library = (libraryCode == null) ? "" : libraryCode;
		    String property = "java.io.tmpdir";
		    String tempDir = System.getProperty(property);
		    
		    
	    	List<String> arguments = new ArrayList<>(List.of("--disable-gpu", "--window-size="+width+","+height,"--ignore-certificate-errors"));
	    	if (profilePath != "") {
	    		arguments.add("user-data-dir="+profilePath);
	    	}
	    	
		    
		    if (browser == "Chrome") {
		    	File file = new File(tempDir + "/chromedriver.exe");

		    	if (!file.exists()) {
		      
		    		copyDriver(file,"chromedriver.exe");
		    	} 
		    	else {
		    		Date modifiedDate = new Date(file.lastModified());
		    		if (modifiedDate.before(lastRelease)) {
		    			file.delete();
			    		copyDriver(file,"chromedriver.exe");
		    		}
		    			
		    	}
			
		    	driverpath = (driverpath == null) ? file.getAbsolutePath() : driverpath;
		    	System.setProperty("webdriver.chrome.driver", driverpath);
		    	ChromeOptions options = new ChromeOptions();
		    	options.addArguments(arguments);
		    	options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		    	options.setHeadless(headless);
		    	
		    	if (port != null) {
		    		options.setExperimentalOption("debuggerAddress","localhost:"+port.toString());
		    	}
		    	this.driver = new ChromeDriver(options);
		    	this.browser = "chrome";
		    }
		    
		    
		    if (browser == "Edge") {
		    	File file = new File(tempDir + "/msedgedriver.exe");
		    	if (!file.exists()) {
		    		copyDriver(file,"msedgedriver.exe");
		    	} 
		    	else {
		    		Date modifiedDate = new Date(file.lastModified());
		    		if (modifiedDate.before(lastRelease)) {
		    			file.delete();
			    		copyDriver(file,"msedgedriver.exe");
		    		}
		    			
		    	}
			
		    	driverpath = (driverpath == null) ? file.getAbsolutePath() : driverpath;
		    	System.setProperty("webdriver.edge.driver", driverpath);
		    	EdgeOptions options = new EdgeOptions();
		    	options.addArguments(arguments);
		    	options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		    	options.setHeadless(headless);
		    	if (port != null) {
		    		
		    		options.setExperimentalOption("debuggerAddress","localhost:"+port.toString());
		    	}
		    	this.driver = new EdgeDriver(options);
		    	this.browser = "edge";
		    }
		    
		    
		/*   Currently Gecko Driver is not stable enough 8/16/2021
		  
		   		if (browser == "Firefox") {

		    	File file = new File(tempDir + "/geckodriver.exe");
		    	if (!file.exists()) {
		      
		    		InputStream driverexe = getClass().getResourceAsStream("/driver/geckodriver.exe");
		    		Files.copy(driverexe, file.getAbsoluteFile().toPath(), new java.nio.file.CopyOption[0]);
		    		driverexe.close();
		    	} 
			
		    	driverpath = (driverpath == null) ? file.getAbsolutePath() : driverpath;
		    	System.setProperty("webdriver.gecko.driver", driverpath);
		    	FirefoxOptions options = new FirefoxOptions();
		    	options.setCapability("marionette", true);
		    	if (profilePath != "") {
				  options.addArguments("-P", profilePath);
		    	}
		    	options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		    	this.driver = new FirefoxDriver(options);
		    	this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		    	this.browser = "firefox";
		    }
		    
		*/
		    
		}
		
	

		public WebDriver getDriver() {
			return this.driver;
		}
		
		public String getLibrary() {
			return this.library;
		}
		
		public void closeDriver() {
			this.driver.close();
		}
		
		public void quitDriver() {
			this.driver.quit();
		}
		
		
		public String getBrowserType() {
			return this.browser;
		}
		
		private void copyDriver(File driverFile, String drivername) throws IOException {
    		InputStream driverexe = getClass().getResourceAsStream("/driver/"+drivername);
    		Files.copy(driverexe, driverFile.getAbsoluteFile().toPath(), new java.nio.file.CopyOption[0]);
    		driverexe.close();
		}
		

		
	}

