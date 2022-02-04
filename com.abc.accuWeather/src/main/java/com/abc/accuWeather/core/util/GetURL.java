package com.abc.accuWeather.core.util;

public class GetURL {
	
	private final static String accuWeather_DEV = "https://www.accuweather.com/";
	private final static String accuWeather_QA = "https://www.accuweather.com/";
	private final static String accuWeather_UAT = "https://www.accuweather.com/";
	private final static String accuWeather_PROD = "https://www.accuweather.com/";
	
	
	public String geBaseUrl(String envrionment) {
		if(envrionment.equalsIgnoreCase("DEV"))
			return accuWeather_DEV;
		else if(envrionment.equalsIgnoreCase("QA"))
			return accuWeather_QA;
		else if(envrionment.equalsIgnoreCase("UAT"))
			return accuWeather_UAT;
		else if(envrionment.equalsIgnoreCase("PROD"))
			return accuWeather_PROD;
		else
			return null;
	}

}
