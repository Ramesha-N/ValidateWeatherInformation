package com.abc.accuWeather.API.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString 
public class WeatherMainFieldsResponseDTO {

	private float temp;
	private float feels_like;
	private float temp_min;
	private float temp_max;
	private float pressure;
	private float humidity;
}
