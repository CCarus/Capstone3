package com.techelevator.npgeek;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.models.survey.JdbcSurveyResultDao;
import com.techelevator.npgeek.models.survey.SurveyResult;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcSurveyResultTest {
	
	private static SingleConnectionDataSource dataSource;
	private JdbcSurveyResultDao dao;
	
	public JdbcSurveyResultTest() {
		dao = new JdbcSurveyResultDao(dataSource);
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
	public void create_survey_and_read_it_back() {
		SurveyResult theSurvey = getSurveyResult("tekcase@gmail.com",
				"MI", "Active", "CVNP");
		System.out.println("created an object");
		theSurvey = dao.saveNewSurvey(theSurvey);
		SurveyResult savedSurvey = dao.getSurveyById(theSurvey.getSurveyId());
		assertSurveysAreEquals(theSurvey, savedSurvey);
	}
	
	
	private SurveyResult getSurveyResult(String email, String state, String activityLevel,
			String parkCode) {
		SurveyResult survey = new SurveyResult();
		survey.setEmail(email);
		survey.setState(state);
		survey.setActivityLevel(activityLevel);
		survey.setParkCode(parkCode);

		return survey;
	} 
	
	
	private void assertSurveysAreEquals(SurveyResult theSurvey, SurveyResult savedSurvey) {
		// asserts to make sure each attribute is the same for the cities to be equal
		assertEquals(theSurvey.getActivityLevel(), savedSurvey.getActivityLevel());
		assertEquals(theSurvey.getEmail(), savedSurvey.getEmail());
		assertEquals(theSurvey.getParkCode(), savedSurvey.getParkCode());
		assertEquals(theSurvey.getState(), savedSurvey.getState());
	}
 

}
