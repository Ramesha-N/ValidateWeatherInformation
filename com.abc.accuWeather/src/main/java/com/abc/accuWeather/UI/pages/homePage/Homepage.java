package com.abc.accuWeather.UI.pages.homePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abc.accuWeather.UI.pages.core.WebElementWrapper;

public class Homepage extends WebElementWrapper{

	public Homepage(WebDriver driver) {
		super(driver);
	}	
	
	@FindBy(css = "a[class='header-logo']")
	protected WebElement accuWeatherLogo;
	
	@FindBy(name = "query")
	protected WebElement searchTextBox;
	
	@FindBy(css =  ".displayed-item span")
	protected WebElement activeDropDown;
	
	@FindBy(css =  ".item span")
	protected WebElement selectSearchOptionDropDown;
	
	@FindBy(css =  ".search-options")
	protected WebElement dropDownBtn;	
	
	
	By accuWeatherLogoByLocator = By.cssSelector("a[class='header-logo']");
	By searchTextBoxByLocator = By.name("query");
	By activeDropDownByLocator = By.cssSelector(".displayed-item span");
	By selectSearchOptionDropDownByLocator = By.cssSelector(".item span");

	@Override
	public boolean isLoaded() {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(accuWeatherLogoByLocator));
			accuWeatherLogo.isDisplayed();
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle().trim();
	}
	
	public void inputValueToSearchTextBox(String inputValue) {
		searchTextBox.sendKeys(inputValue, Keys.ENTER);
		
	}
	
	public String getActiveValueSearchOptionDropDown() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(activeDropDownByLocator));
		return activeDropDown.getText().trim();
	}
	
	public void changeSearchOptionDropDown(String dropDownText) {
		dropDownBtn.click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(selectSearchOptionDropDown));
		WebElement element = driver.findElements(selectSearchOptionDropDownByLocator)
										.stream()
										.filter(x -> x.getText().equalsIgnoreCase(dropDownText))
										.findFirst().get();
		element.click();
	}
	
	

}
