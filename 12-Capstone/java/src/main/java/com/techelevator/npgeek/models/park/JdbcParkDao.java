package com.techelevator.npgeek.models.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcParkDao implements ParkDao {
	
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public List<Park> getAllParks() {
		List <Park> parks = new ArrayList<>();
		String sql = "SELECT * FROM park ORDER BY parkname";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			Park park = mapRowToPark(results);
			parks.add(park);
		}
		return parks;
	}

	@Override
	public Park getParkById(String parkCode) {
		String sql = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		Park park = new Park();
		if(results.next()) {
			park = mapRowToPark(results);
		}
		return park;	
	}
	
	@Override
	public void createNewPark(Park park) {
		String sql = "INSERT INTO PARK(parkcode, parkname, state,"
				+ " acreage, elevationinfeet, milesoftrail, numberofcampsites,"
				+ " climate, yearfounded, annualvisitorcount, "
				+ " inspirationalquote, inspirationalquotesource,"
				+ " parkdescription, entryfee, numberofanimalspecies)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				jdbcTemplate.update(sql, park.getParkCode(),
				park.getParkName(), park.getState(), park.getAcreage(), park.getElevationFeet(),
				park.getMilesOfTrail(), park.getNumberOfCampsites(), park.getClimate(), park.getYearFounded(),
				park.getAnnualVisitorCount(),park.getInspirationalQuote(), park.getInspirationalQuoteSource(),
				park.getParkDescription(), park.getEntryFee(), park.getNumberOfAnimalSpecies());
		}
	
	
	private Park mapRowToPark(SqlRowSet result) {
		Park park = new Park();
		park.setParkCode(result.getString("parkcode").toLowerCase());
		park.setParkName(result.getString("parkname"));
		park.setState(result.getString("state"));
		park.setAcreage(result.getInt("acreage"));
		park.setElevationFeet(result.getInt("elevationinfeet"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setNumberOfCampsites(result.getInt("numberofcampsites"));
		park.setClimate(result.getString("climate"));
		park.setYearFounded(result.getInt("yearfounded"));
		park.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
		park.setInspirationalQuote(result.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(result.getString("inspirationalquotesource"));
		park.setParkDescription(result.getString("parkdescription"));
		park.setEntryFee(result.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));
		
		return park;
	}



}
