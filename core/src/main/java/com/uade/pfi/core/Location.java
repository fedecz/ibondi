package com.uade.pfi.core;

public class Location {

	private Integer latitude;
	private Integer longitude;

	public Location(Integer latitude, Integer longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location() {
	}
	
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public Integer getLongitude() {
		return longitude;
	}
	
}
