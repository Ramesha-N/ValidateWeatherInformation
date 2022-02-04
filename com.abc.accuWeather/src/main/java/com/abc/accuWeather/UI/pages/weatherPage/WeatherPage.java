package com.abc.accuWeather.UI.pages.weatherPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abc.accuWeather.UI.pages.core.WebElementWrapper;

public class WeatherPage extends WebElementWrapper{

	public WeatherPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = ".cur-con-weather-card__title")
	protected WebElement currentWeatherText;
	
	@FindBy(css = ".cur-con-weather-card__panel .forecast-container .temp")
	protected WebElement currentWeatherValue;
	
	@FindBy(css = ".cur-con-weather-card__panel .forecast-container .temp .after-temp")
	protected WebElement degreeValue;
	
	@FindBy(id = "google_ads_iframe_/6581/web/in/interstitial/admin/search_0")
	protected WebElement googleAdsIframe;
	
	@FindBy(id = "dismiss-button")
	protected WebElement cancelIframePopup;	
	
	
	By currentWeatherTextByLocator = By.cssSelector(".cur-con-weather-card__title");
	By currentWeatherValueByLocator = By.cssSelector(".cur-con-weather-card__panel .forecast-container .temp");
	By degreeValueByLocator = By.cssSelector(".cur-con-weather-card__panel .forecast-container .temp .after-temp");
	By googleAdsIframeByLocator = By.id("google_ads_iframe_/6581/web/in/interstitial/admin/search_0");
	By cancelIframePopupByLocator = By.id("dismiss-button");
			

	@Override
	public boolean isLoaded() {
		try {
			if (isElementPresent(googleAdsIframeByLocator)) {
				switchToCurrentDislayedFrame();
				clickDismissBtn();
				driver.switchTo().defaultContent();
			}
			
			new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(currentWeatherTextByLocator));
			currentWeatherText.isDisplayed();
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle().trim();
	}
	
	public String getCurrentWeatherValue() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(currentWeatherValueByLocator));
		return currentWeatherValue.getText().trim();
	}
	
	public void switchToCurrentDislayedFrame() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(googleAdsIframe));
	}
	
	public void clickDismissBtn() {
		cancelIframePopup.click();
	}

}
