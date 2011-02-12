package com.uade.pfi.core;

public class Location {

	private Float latitude;
	private Float longitude;

	public Location(Float latitude, Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location() {
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	
}
