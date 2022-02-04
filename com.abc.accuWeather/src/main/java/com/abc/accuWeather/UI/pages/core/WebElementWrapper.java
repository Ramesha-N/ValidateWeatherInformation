package com.abc.accuWeather.UI.pages.core;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebElementWrapper {
	
	protected WebDriver driver;
	
	protected WebElementWrapper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public abstract boolean isLoaded();
	public abstract String getPageTitle();
	
	public WebDriver getDriver() {
		return this.driver;
	}		
	
	public void selectDropDownByVisibleText(WebElement element, String text) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(text);		
	}
	
	public void selectDropDownByValue(WebElement element, String value) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
		Select dropDown = new Select(element);
		dropDown.selectByValue(value);		
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public boolean isElementPresent(By locator) {
		try {
			Thread.sleep(5000);
			driver.findElement(locator);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isElementsPresent(By locator) {
		try {
			Thread.sleep(5000);
			driver.findElements(locator);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
