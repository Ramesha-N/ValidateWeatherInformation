package com.abc.accuWeather.UI.core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.abc.accuWeather.API.core.APIBaseTest;
import com.abc.accuWeather.core.util.ConfigReader;
import com.abc.accuWeather.core.util.GetURL;
import com.abc.accuWeather.core.util.SetBrowser;

public class UIBaseTest{
	
	protected ConfigReader configReader;
	protected WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"testBrowser"})
	public void setUp(@Optional("chrome") String testBrowser) {
		try {
			
			String testEnv = getSystemEnviromentValue("test.environment");
			SetBrowser browser = new SetBrowser();
			
			if(driver != null)
				return;
			
			if (testEnv != null) {
				browser.setBrowser(testBrowser);
				driver = browser.getDriver();
			}else {
				browser.setBrowser(configReader.getStringProperty("ui.default.browser"));
				driver = browser.getDriver();
			}
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.manage().window().maximize();
			
			GetURL url = new GetURL();
			if(testEnv != null) 				
				driver.navigate().to(url.geBaseUrl(testEnv));
			else if(!configReader.getStringProperty("test.environment").isBlank())
				driver.navigate().to(url.geBaseUrl(configReader.getStringProperty("test.environment")));
			else
				throw new NullPointerException("URL is not defined");
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String getSystemEnviromentValue(String key) {
		return System.getProperty(key);
	}
	
	public float convertTempStringToFloat(String temperature) {
		return Float.parseFloat(temperature.substring(0, temperature.indexOf("°")));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if(driver != null)
			driver.quit();
	}
	
	@BeforeSuite(alwaysRun = true)
	public void readAllConfigurationPropertiesFile() {
		configReader = ConfigReader.getInstance();
	}
	

}
