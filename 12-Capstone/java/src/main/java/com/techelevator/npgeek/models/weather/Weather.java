package com.techelevator.npgeek.models.weather;

public class Weather {
	
	private String parkCode;
	private int fiveDayForecastValue;
	private int lowTemp;
	private int highTemp;
	private String forecast;
	private String imageName;
	private String dayLabel;
	

	public Weather() {

	}
	
	public String getImageName() {
		if(this.imageName.equals("partly cloudy")) {
			this.imageName = "partlyCloudy";
		}
		return this.imageName ;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public int getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}
	public int getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getDayLabel() {
		
		if(this.fiveDayForecastValue == 1) {
			this.dayLabel = "Today";
		}
		else if(this.fiveDayForecastValue == 2) {
			this.dayLabel = "Day 2";
			}
		else if(this.fiveDayForecastValue == 3) {
			this.dayLabel = "Day 3";
		}
		else if(this.fiveDayForecastValue == 4) {
			this.dayLabel = "Day 4";
		}
		else if (this.fiveDayForecastValue == 5) {
			this.dayLabel = "Day 5";
		}
		return dayLabel;
	}

	public void setDayLabel(String dayLabel) {
		this.dayLabel = dayLabel;
	}



}
