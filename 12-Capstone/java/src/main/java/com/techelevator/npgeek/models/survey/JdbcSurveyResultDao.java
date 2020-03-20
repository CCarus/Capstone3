package com.techelevator.npgeek.models.survey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyResultDao implements SurveyResultDao {
	
	private final JdbcTemplate jdbcTemplate;
	
    @Autowired
    public JdbcSurveyResultDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    
	@Override
	public SurveyResult getSurveyById(Long id) {
		SurveyResult survey = null;
		String sql = "SELECT * " + 
		"FROM survey_result WHERE surveyid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
			if(results.next()) {
				survey = mapRowToSurvey(results);
			}
			return survey;
	}

	@Override
	public List<SurveyResult> getFavoriteSurveys() {
		List <SurveyResult> surveys = new ArrayList<>(); 
		String sql = "SELECT sr.parkcode, p.parkname,"
				+ " count(sr.parkcode) as numberofsurveys FROM survey_result sr" + 
				" JOIN park p ON  sr.parkcode = p.parkcode" + 
				" GROUP BY sr.parkcode, p.parkname" + 
				" ORDER BY p.parkname";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			SurveyResult survey = new SurveyResult();
			survey.setParkCode(results.getString("parkcode"));
			survey.setParkName(results.getString("parkname"));
			survey.setSurveyCount(results.getInt("numberofsurveys"));
			surveys.add(survey);
		}
		return surveys;
	}

	@Override
	public SurveyResult saveNewSurvey(SurveyResult survey) {
		survey.setSurveyId(getNextId());
		String sql = "INSERT INTO survey_result(surveyid, parkcode, emailaddress," +
				" state, activitylevel) VALUES(?, ?,?,?,?)";
		jdbcTemplate.update(sql, survey.getSurveyId(),
				survey.getParkCode(), survey.getEmail(), survey.getState(),
				survey.getActivityLevel());
		return survey;
	} 
		
		private Long getNextId() {
			String sqlSelectNextId = "SELECT MAX(surveyid) FROM survey_result";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
			Long id = null;
			if (results.next()) {
			id = results.getLong(1) + 1;
			} else {
			throw new RuntimeException("Something strange happened, unable to select next order id from sequence");
			}
			return id;
	}
		
		private SurveyResult mapRowToSurvey(SqlRowSet results) {
			SurveyResult survey = new SurveyResult();
			survey.setSurveyId(results.getLong("surveyid")); 
			survey.setParkCode(results.getString("parkcode"));
			survey.setEmail(results.getString("emailaddress"));
			survey.setState(results.getString("state"));
			survey.setActivityLevel(results.getString("activitylevel"));
	
			return survey;
		}

	
}