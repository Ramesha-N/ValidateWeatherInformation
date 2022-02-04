package com.abc.accuWeather.API.DTO.Response;

import java.util.List;

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
public class WeatherFieldsResponseDTO {
	
	private Coord coord;
	private List<Weather> weather;
	private String base;
	private WeatherMainFieldsResponseDTO main;
	private String visibility;
	private Wind wind;
	private Clouds clouds;
	private int dt;
	private Sys sys;
	private int timezone;
	private Long id;
	private String name;
	private int cod;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Coord{
	private float lon;
	private float lat;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Weather{
	private int id;
	private String main;
	private String description;
	private String icon;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Wind{
	private float speed;
	private int deg;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Clouds{
	private int all;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Sys{
	private int type;
	private int id;
	private float message;
	private String country;
	private String sunrise;
	private String sunset;
}