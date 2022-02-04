package com.abc.accuWeather.API.core;

import com.abc.accuWeather.core.util.ConfigReader;

public class BaseTest extends APIBaseTest{

	@Override
	public void setBaseUrl() {
		super.baseURL = configReader.getStringProperty("api.Weather.baseURL");
	}

	@Override
	public void setEndpoint() {
		super.endpoint = configReader.getStringProperty("api.Weather.endpoint");
	}
	
	public BaseTest() {
		if(configReader == null)
			configReader = ConfigReader.getInstance();
	}

}
