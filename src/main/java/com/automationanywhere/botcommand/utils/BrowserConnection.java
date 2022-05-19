package com.automationanywhere.botcommand.utils;

import com.automationanywhere.botcommand.exception.BotCommandException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class BrowserConnection {


		private WebDriver driver;
		private String library;
		private String browser ;
		private Date lastRelease = new Date(System.currentTimeMillis()-3600000L); // minus one hour;

	public BrowserConnection(String driverpath, String profilePath, String browser, Boolean headless, Integer port,String libraryCode, Integer height, Integer width) throws Exception {

		this.library = (libraryCode == null) ? "" : libraryCode;

		List<String> arguments = new ArrayList<>(List.of("--disable-gpu", "--window-size="+width+","+height,"--ignore-certificate-errors"));
		if (!profilePath.isBlank()) {
			arguments.add("user-data-dir="+profilePath);
		}

		if (browser.equals("Chrome")) {
			this.browser = "chrome";
			ChromeOptions options = new ChromeOptions();
			options.addArguments(arguments);
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.setHeadless(headless);
			if (port != null)
				options.setExperimentalOption("debuggerAddress","localhost:"+ port);

			if (driverpath != null && !driverpath.isBlank() && new File(driverpath).exists()) {
				System.setProperty("webdriver.chrome.driver", driverpath);
				this.driver = new ChromeDriver(options);
			} else
				this.driver = WebDriverManager.chromedriver().capabilities(options).create();
			if(this.driver==null)
				throw new BotCommandException("Unable to find installed application: chrome browser");
		}


		if (browser.equals("Edge")) {
			this.browser = "edge";
			EdgeOptions options = new EdgeOptions();
			options.addArguments(arguments);
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.setHeadless(headless);
			if (port != null)
				options.setExperimentalOption("debuggerAddress","localhost:"+ port);

			if (driverpath != null && !driverpath.isBlank() && new File(driverpath).exists()) {
				System.setProperty("webdriver.edge.driver", driverpath);
				this.driver = new EdgeDriver(options);
			} else
				this.driver = WebDriverManager.edgedriver().capabilities(options).create();
			if(this.driver==null)
				throw new BotCommandException("Unable to find installed application: edge browser");
		}

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

