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
	private float 	latitude;
	@Field("long")
	private float 	longitude;
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
	
	/**
	 * format example "-58.581467,-34.423206"
	 * 
	 * @param fromString
	 */
	public Location(String fromString){
		StringTokenizer st = new StringTokenizer(fromString,",");
		NumberFormat nf = new DecimalFormat();
		try {
			this.latitude = nf.parse(st.nextToken()).floatValue();
			this.longitude = nf.parse(st.nextToken()).floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
