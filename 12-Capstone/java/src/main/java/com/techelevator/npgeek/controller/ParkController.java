package com.techelevator.npgeek.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.models.park.Park;
import com.techelevator.npgeek.models.park.ParkDao;
import com.techelevator.npgeek.models.weather.Weather;
import com.techelevator.npgeek.models.weather.WeatherDao;

@Controller
public class ParkController {
	
	@Autowired
	private ParkDao parkDao;

	@Autowired
	private WeatherDao weatherDao;
	
	@RequestMapping("/parkDetail")
	public String displayParkDetails(@RequestParam String parkCode, ModelMap map) {
		String newParkCode = parkCode.toUpperCase();
		Park park = parkDao.getParkById(newParkCode);
		map.put("park", park);

		return "parkDetail";
	}

	@RequestMapping("/weather")
	public String displayWeatherForecast(@RequestParam String parkCode, ModelMap map, HttpSession session) {
		List<Weather> forecast = weatherDao.getFiveDayForecast(parkCode.toUpperCase());
		//Calls helper method for String array of recommendations based on weather conditions
		List<String> forecastStatement = getWeatherRecommendation(forecast);
		
		//Weather page displays fahrenheit by default
		Boolean isF = true;
		
		//if weather has not been toggled yet and session is empty, session is set to fahrenheit
		if(session.getAttribute("tempType")==null) {
		session.setAttribute("tempType", isF);
		}
	
		session.setAttribute("parkCode", parkCode);
		String parkName = parkDao.getParkById(parkCode.toUpperCase()).getParkName();
		map.put("forecast", forecast);
		map.put("forecastStatement", forecastStatement);
		map.put("parkName", parkName);
		
		return "weather";
	}
	
	@RequestMapping("/tempToggle")
	public String displayTempToggle(ModelMap map, HttpSession session, Boolean tempType) {
		String parkCode = (String)session.getAttribute("parkCode");
		
		//change session attribute for new tempature type
		session.setAttribute("tempType", tempType );
		List<Weather> forecast = weatherDao.getFiveDayForecast(parkCode.toUpperCase());
		List<String> forecastStatement = getWeatherRecommendation(forecast);
		map.put("forecast", forecast);
		map.put("forecastStatement", forecastStatement);
		
		//returns to same page with new temperature type
		return "weather";
		
	}
	
	//helper method
	public List<String> getWeatherRecommendation(List<Weather> weatherForecast) {
		List<String> weatherStatement = new ArrayList<>();
		String forecast = weatherForecast.get(0).getForecast();
		int high = weatherForecast.get(0).getHighTemp();
		int low = weatherForecast.get(0).getLowTemp();	

		if (forecast.equals("snow")) { 
			weatherStatement.add("Pack your snowshoes!");
		}
		else if (forecast.equals("rain")) { 
			weatherStatement.add("Pack rain gear and wear waterproof shoes!");
		}
		else if (forecast.equals("thunderstorms")) {
			weatherStatement.add("Seek Shelter and avoid hiking on exposed ridges.");
		}
		else if(forecast.equals("sunny")) {
			weatherStatement.add("Protect your skin and use sunblock.");
		}
		else if( high > 75) {
			weatherStatement.add("Bring and extra gallon of water.");
		}
		else if( low < 20) {
			weatherStatement.add("DANGER! Frigid temperatures.");
		}
		else if( high - low > 20) {
			weatherStatement.add("Wear breathable layers.");
		}
		return weatherStatement;
	}
	

}
