package com.uade.pfi.core;

public class TransportLocation {
	
	private Location 	location;
	private String 		name;
	
	public TransportLocation() {
	}
	
	public TransportLocation(int latitude, int longitude, String name) {
		this.location = new Location(latitude,longitude);
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}
	public String getName() {
		return name;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setName(String name) {
		this.name = name;
	}
}
