package com.abc.accuWeather.UI.pages.FindLocationPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abc.accuWeather.UI.pages.core.WebElementWrapper;

public class FindLocationPage extends WebElementWrapper{

	public FindLocationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = ".more-button.locations-list-show-button")
	protected WebElement showMoreLocation;
	
	@FindBy(css = ".locations-list.content-module a")
	protected WebElement getAllLocations;
	
	
	
	
	By showMoreLocationByLocator = By.cssSelector(".more-button.locations-list-show-button");
	By getAllLocationsByLocator = By.cssSelector(".locations-list.content-module a");

	@Override
	public boolean isLoaded() {
		try {
			if (isElementPresent(showMoreLocationByLocator)) {
				showMoreLocation.isDisplayed();
				return true;
			}else
				return false;
		}catch(NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle().trim();
	}
	
	public List<WebElement> getAllLocationsDisplayed() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(getAllLocations));
		List<WebElement> elements = driver.findElements(getAllLocationsByLocator);
		return elements;
	}
	
	public void clickOnExtactOrFirstRecordLocationMatch(String locationName) {
		if (isElementsPresent(getAllLocationsByLocator)) {
			WebElement element = getAllLocationsDisplayed().stream()
					.filter(x -> x.getText().equalsIgnoreCase(locationName)).findFirst()
					.orElse(getAllLocationsDisplayed().get(0));

			element.click();
		}
	}
	

}
