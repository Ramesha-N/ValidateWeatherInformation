package com.abc.accuWeather.API.tests;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.abc.accuWeather.API.DTO.Response.WeatherFieldsResponseDTO;
import com.abc.accuWeather.API.core.APIBaseTest;
import io.restassured.response.Response;

public class WeatherInfoTest extends APIBaseTest{
	
	private float tempFromAPI;

	@BeforeClass(alwaysRun = true)
	@Override
	public void setBaseUrl() {
		baseURL = configReader.getStringProperty("api.Weather.baseURL");
	}

	@BeforeClass(alwaysRun = true)
	@Override
	public void setEndpoint() {
		endpoint = configReader.getStringProperty("api.Weather.endpoint");
	}
	
	@Test
	public void getWeatherInfo() {
		WeatherFieldsResponseDTO weatherResp = new WeatherFieldsResponseDTO();
		
		Map<String, String> data = new ConcurrentHashMap<>();
		data.put("{city name}", configReader.getStringProperty("data.cityName"));
		data.put("{state code}", configReader.getStringProperty("data.stateCode"));
		data.put("{country code}", configReader.getStringProperty("data.countryCode"));
		
		Response respone = getMethod(data);
		weatherResp = respone.as(WeatherFieldsResponseDTO.class);
		tempFromAPI = convertKelvinToCelsius(weatherResp.getMain().getTemp());
		System.out.println("Temperature from API : "+tempFromAPI);
	}

}
