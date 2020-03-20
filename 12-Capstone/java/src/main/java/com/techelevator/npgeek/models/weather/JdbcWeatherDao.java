package com.techelevator.npgeek.models.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {
	
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcWeatherDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public List<Weather> getFiveDayForecast(String parkcode) {
		List<Weather> weatherList = new ArrayList<>();
		String sql = "SELECT * from weather WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkcode);
		while(results.next()) {
			Weather weather = new Weather();
			weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
			weather.setParkCode(results.getString("parkcode"));
			weather.setLowTemp(results.getInt("low"));
			weather.setHighTemp(results.getInt("high"));
			weather.setForecast(results.getString("forecast"));
			weather.setImageName(results.getString("forecast"));
			weatherList.add(weather);
		}
		return weatherList;
	}
	
	@Override
	public Weather saveNewWeather(Weather weather) {
		String sql = "INSERT INTO weather(parkcode, fivedayforecastvalue, "
				+ " low, high, forecast) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql, weather.getParkCode(), weather.getFiveDayForecastValue(),
				weather.getLowTemp(), weather.getHighTemp(), weather.getForecast());
		return weather;
	}

	@Override
	public Weather getWeatherByParkCode(String parkCode) {
	String sql = "SELECT * FROM weather WHERE parkcode = ?";
	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
	Weather weather = new Weather();
	while(results.next()) {
		weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		weather.setParkCode(results.getString("parkcode"));
		weather.setLowTemp(results.getInt("low"));
		weather.setHighTemp(results.getInt("high"));
		weather.setForecast(results.getString("forecast"));
		weather.setImageName(results.getString("forecast"));
		
		}return weather;
	}

}
