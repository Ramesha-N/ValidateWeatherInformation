package com.abc.accuWeather.API.core;

import java.util.Map;

import org.testng.annotations.BeforeSuite;

import com.abc.accuWeather.core.util.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class APIBaseTest {
	
	protected ConfigReader configReader;
	
	protected String baseURL;
	protected String endpoint;
	
	public abstract void setBaseUrl();
	public abstract void setEndpoint();
	
	public Response getMethod(Map<String, String> pathParam) {
			RestAssured.baseURI = baseURL;
		
			RequestSpecification httpReq =	RestAssured.given();
			pathParam.put("{API key}", configReader.getStringProperty("api.auth.APIKey"));
			//httpReq.pathParams(pathParam);
			pathParam.keySet().stream().forEach(x -> {endpoint = endpoint.replace(x, pathParam.get(x));});
			Response response = httpReq.get(endpoint);
			return response;
	}
	
	public float convertKelvinToCelsius(float tempInKelvin) {
		return tempInKelvin - 273.15F;
	}
	
	
	
	
	
	@BeforeSuite(alwaysRun = true)
	public void readAllConfigurationPropertiesFile() {
		configReader = ConfigReader.getInstance();
	}

}
