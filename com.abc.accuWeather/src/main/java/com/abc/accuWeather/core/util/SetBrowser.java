package com.abc.accuWeather.core.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetBrowser extends BaseUtil{
	
	ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>(); 
	protected WebDriver driver;
	
	
	public void setBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driverThread.set(new ChromeDriver());
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driverThread.set(new FirefoxDriver());
		}
	}
	
	public WebDriver getDriver() {
		return this.driverThread.get();
	}
	

}
