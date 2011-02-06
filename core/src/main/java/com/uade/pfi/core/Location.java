package com.uade.pfi.core;

public class Location {

	private int latitude;
	private int longitude;

	public Location(int latitude, int longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location() {
	}
	
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getLatitude() {
		return latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	
}
