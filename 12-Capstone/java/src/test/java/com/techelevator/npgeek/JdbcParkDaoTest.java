package com.techelevator.npgeek;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.models.park.JdbcParkDao;
import com.techelevator.npgeek.models.park.Park;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcParkDaoTest {
	private static SingleConnectionDataSource dataSource;
	private JdbcParkDao ParkDao;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	public JdbcParkDaoTest() {
		ParkDao = new JdbcParkDao(dataSource);
	}
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void return_list_of_parks() {
		List <Park> allParks = ParkDao.getAllParks();
		String sql = "SELECT * FROM PARK";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		int countParks = 0;
		while (results.next()) {
			countParks += 1;
		}
		assertEquals(countParks, allParks.size());
	
	}
	
	}
	

