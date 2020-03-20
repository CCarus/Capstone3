package com.techelevator.npgeek.models.weather;

import java.util.List;

public interface WeatherDao {
	
	List <Weather> getFiveDayForecast(String parkcode);
	Weather saveNewWeather(Weather weather);
	Weather getWeatherByParkCode(String parkCode);

	
	
}
