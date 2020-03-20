package com.techelevator.npgeek;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.models.park.JdbcParkDao;
import com.techelevator.npgeek.models.park.Park;
import com.techelevator.npgeek.models.survey.JdbcSurveyResultDao;
import com.techelevator.npgeek.models.survey.SurveyResult;
import com.techelevator.npgeek.models.weather.JdbcWeatherDao;
import com.techelevator.npgeek.models.weather.Weather;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcWeatherDaoTest {
	

	private static SingleConnectionDataSource dataSource;
	private JdbcWeatherDao weatherDao;
	private JdbcParkDao parkDao;
	
	public JdbcWeatherDaoTest() {
		weatherDao = new JdbcWeatherDao(dataSource);
		parkDao = new JdbcParkDao(dataSource);	}

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource() {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		weatherDao = new JdbcWeatherDao(dataSource);
		parkDao = new JdbcParkDao(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Test
	public void return_all_weather_size_increment_by_one() {
		Park park = new Park("CT", "CTPARK", "MI", 10, 0, 10, 1,
				"woodland", 2020, 10, "this is a quote", "Casey",
				"a park", 0, 5);
		parkDao.createNewPark(park);
		
		Weather weather = getWeather("CT", 1, 10, 30, "sunny");
		weatherDao.saveNewWeather(weather);
		int size = weatherDao.getFiveDayForecast(park.getParkCode()).size();
		Weather theWeather = getWeather("CT", 2, 10, 20, "sunny");
		weatherDao.saveNewWeather(theWeather);
		
		assertEquals(size + 1, weatherDao.getFiveDayForecast(park.getParkCode()).size());
	}
	
		@Test 
		public void create_weather_and_read_it_back() {
		Park park = new Park("CT", "CTPARK", "MI", 10, 0, 10, 1,
				"woodland", 2020, 10, "this is a quote", "Casey",
				"a park", 0, 5);
		parkDao.createNewPark(park);
		Weather theWeather = getWeather("CT", 1, 10, 20, "sunny");
		weatherDao.saveNewWeather(theWeather);
		Weather savedWeather = weatherDao.getWeatherByParkCode(theWeather.getParkCode());
		assertWeatherAreEqual(theWeather, savedWeather);
		
	}
	
	private void assertWeatherAreEqual(Weather theWeather, Weather savedWeather) {
		assertEquals(theWeather.getParkCode(), savedWeather.getParkCode());
		assertEquals(theWeather.getFiveDayForecastValue(), savedWeather.getFiveDayForecastValue());
		assertEquals(theWeather.getHighTemp(), savedWeather.getHighTemp());
		assertEquals(theWeather.getLowTemp(), savedWeather.getLowTemp());
		assertEquals(theWeather.getForecast(), savedWeather.getForecast());

	}
	
	private Weather getWeather(String parkCode, int foreCastValue, int low,
			int high, String forecast) {
		Weather weather = new Weather();
		weather.setParkCode(parkCode);;
		weather.setFiveDayForecastValue(foreCastValue);
		weather.setHighTemp(high);
		weather.setLowTemp(low);
		weather.setForecast(forecast);

		return weather;
	} 
}
