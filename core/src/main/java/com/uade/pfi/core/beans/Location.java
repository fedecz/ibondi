package com.uade.pfi.core.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uade.pfi.core.utils.TransportMeStringCreator;


@Entity
@Table(name="location")
public class Location implements Comparable<Location>{
	@Id
	@GeneratedValue
	private Long 	id;
	
	private Float 	latitude;
	private Float 	longitude;
	private Date	trackedOn;

	public Location(Float latitude, Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int compareTo(Location l) {
		if(l.getTrackedOn() == null)
			return -1;
		if(this.getTrackedOn() == null)
			return 1;
		if(this.getTrackedOn() == null && l.getTrackedOn() == null)
			return 0;
		if((this.getTrackedOn().getTime() - l.getTrackedOn().getTime()) >= 0)
			return -1;
		return 1;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
}
