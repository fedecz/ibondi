/**
 * 
 */
package com.uade.pfi.core.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.index.IndexDirection;
import org.springframework.data.document.mongodb.index.Indexed;
import org.springframework.data.document.mongodb.mapping.Document;

import com.uade.pfi.core.utils.TransportMeStringCreator;

/**
 * @author chiwi
 *
 */
@Document
public class TransportSession {
	@Id
	private String 			id;
	private String 			name;
	private Location 		lastKnownLocation;
	private List<Location> 	locations = new ArrayList<Location>();
	@Indexed(direction=IndexDirection.DESCENDING)
	private Date			lastUpdated;

	public TransportSession() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return TransportMeStringCreator.toString(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public void setLastKnownLocation(Location lastKnownLocation) {
		this.lastKnownLocation = lastKnownLocation;
	}
	public Location getLastKnownLocation() {
		return lastKnownLocation;
	}
	

}
