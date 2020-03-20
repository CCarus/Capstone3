package com.techelevator.npgeek.models.park;

import java.util.List;

public interface ParkDao {
	
	List<Park> getAllParks();
	
	Park getParkById(String parkCode);
	
	void createNewPark(Park park);
	
	

}
