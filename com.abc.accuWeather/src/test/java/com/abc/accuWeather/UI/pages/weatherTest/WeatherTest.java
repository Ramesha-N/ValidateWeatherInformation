package com.abc.accuWeather.UI.pages.weatherTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abc.accuWeather.API.DTO.Response.WeatherFieldsResponseDTO;
import com.abc.accuWeather.API.core.BaseTest;
import com.abc.accuWeather.UI.core.UIBaseTest;
import com.abc.accuWeather.UI.pages.homePage.Homepage;
import com.abc.accuWeather.UI.pages.weatherPage.WeatherPage;

import io.restassured.response.Response;

public class WeatherTest extends UIBaseTest{
	

	//@Test
	public void verifyPageTitle() {
		Homepage homepage = new Homepage(driver);
		homepage.isLoaded();
		Assert.assertEquals(homepage.getPageTitle(), "Local, National, & Global Daily Weather Forecast | AccuWeather");
	}
	
	//@Test
	public void selectSearchOptions() {
		Homepage homepage = new Homepage(driver);
		homepage.isLoaded();
		Assert.assertEquals(homepage.getActiveValueSearchOptionDropDown(),"Location");
		homepage.changeSearchOptionDropDown("News");
		Assert.assertEquals(homepage.getActiveValueSearchOptionDropDown(),"News");
		
	}
	
	@Test
	public void getWeatherReport() {
		try {
			String loc = configReader.getStringProperty("data.cityName")+
					","+configReader.getStringProperty("data.stateCode")+
					","+configReader.getStringProperty("data.countryCode");

			Homepage homepage = new Homepage(driver);
			homepage.isLoaded();
			homepage.inputValueToSearchTextBox(loc);

			WeatherPage weatherPage = new WeatherPage(driver);
			weatherPage.isLoaded();
			float tempFromUI = convertTempStringToFloat(weatherPage.getCurrentWeatherValue());
			System.out.println("Temperature from UI : " + tempFromUI);
			
			float tempFromAPI = getTemperatureFromAPI();
			
			float diff = findDifference(tempFromUI, tempFromAPI);
			
			float minVariance = Float.parseFloat(configReader.getStringProperty("data.minVariance"));
			float maxVariance = Float.parseFloat(configReader.getStringProperty("data.maxVariance"));
			
			boolean avtVariance = variance(minVariance,maxVariance,diff);
			
			System.out.println("temperature falls under safe limit : "+avtVariance);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public float getTemperatureFromAPI() {
		WeatherFieldsResponseDTO weatherResp = new WeatherFieldsResponseDTO();
		
		Map<String, String> data = new ConcurrentHashMap<>();
		data.put("{city name}", configReader.getStringProperty("data.cityName"));
		data.put("{state code}", configReader.getStringProperty("data.stateCode"));
		data.put("{country code}", configReader.getStringProperty("data.countryCode"));
		
		BaseTest test = new BaseTest();
		test.setBaseUrl();
		test.setEndpoint();
		
		Response respone = test.getMethod(data);
		weatherResp = respone.as(WeatherFieldsResponseDTO.class);
		float tempFromAPI = test.convertKelvinToCelsius(weatherResp.getMain().getTemp());
		System.out.println("Temperature from API : "+tempFromAPI);
		return tempFromAPI;
	}
	
	public float findDifference(float tempFromUI, float tempFromAPI) {
		if(tempFromUI != 0 && tempFromAPI != 0)
			return tempFromUI - tempFromAPI;
		
		return 0;
	}
	
	public boolean variance(float min, float max, float actualDiff) {
		return min < actualDiff && actualDiff < max;
	}
	

}
