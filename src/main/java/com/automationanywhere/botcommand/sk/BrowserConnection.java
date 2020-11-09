package com.automationanywhere.botcommand.sk;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserConnection {


		private ChromeDriver driver;
		private String library;
		
		public BrowserConnection(String driverpath, Boolean headless, Integer port,String libraryCode) throws Exception {
			
			   
		    String property = "java.io.tmpdir";
		    String tempDir = System.getProperty(property);
		    this.library = (libraryCode == null) ? "" : libraryCode;
		    
		    File file = new File(tempDir + "/chromedriver.exe");
		    if (!file.exists()) {
		      
		      InputStream driverexe = getClass().getResourceAsStream("/driver/chromedriver.exe");
		      Files.copy(driverexe, file.getAbsoluteFile().toPath(), new java.nio.file.CopyOption[0]);
		      driverexe.close();
		    } 
			
		    System.out.println(file.getAbsolutePath());
			driverpath = (driverpath == null) ? file.getAbsolutePath() : driverpath;
			System.setProperty("webdriver.chrome.driver", driverpath);
		   	ChromeOptions options = new ChromeOptions();
		   	if (headless) {
		   		options.addArguments("--headless","--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		   	}
		   	else {
		   		options.addArguments("--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		   	}
	        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	        if (port != null) {
	        	options.setExperimentalOption("debuggerAddress","localhost:"+port.toString());
	        }
		   	this.driver = new ChromeDriver(options);
		}
		
		public ChromeDriver getDriver() {
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
		

		
	}

