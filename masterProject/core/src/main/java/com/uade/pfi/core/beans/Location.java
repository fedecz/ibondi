package com.uade.pfi.core.beans;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;

import org.springframework.data.mongodb.core.mapping.Field;

import com.uade.pfi.core.utils.TransportMeStringCreator;

public class Location {
	@Field("lat")
	private double 	latitude;
	@Field("long")
	private double 	longitude;
	private Date	trackedOn;
	
	public String toString(){
		return TransportMeStringCreator.toString(this);
	}
	
	public Location() {
	}
	
	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * format example "-58.581467,-34.423206"
	 * 
	 * @param fromString
	 */
	public Location(String fromString){
		StringTokenizer st = new StringTokenizer(fromString,",");
		NumberFormat nf = new DecimalFormat();
		try {
			this.latitude = nf.parse(st.nextToken()).doubleValue();
			this.longitude = nf.parse(st.nextToken()).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}

	public Date getTrackedOn() {
		return trackedOn;
	}

	public void setTrackedOn(Date trackedOn) {
		this.trackedOn = trackedOn;
	}
	
}
