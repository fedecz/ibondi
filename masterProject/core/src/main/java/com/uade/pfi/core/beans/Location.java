package com.uade.pfi.core.beans;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import com.uade.pfi.core.utils.TransportMeStringCreator;

public class Location {
	@Field("lat")
	private Float 	latitude;
	@Field("long")
	private Float 	longitude;
	private Date	trackedOn;

	public Location(Float latitude, Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String toString(){
		return TransportMeStringCreator.toString(this);
	}
	
	public Location() {
	}
	
	public Location(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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

	public Date getTrackedOn() {
		return trackedOn;
	}

	public void setTrackedOn(Date trackedOn) {
		this.trackedOn = trackedOn;
	}
	
}
