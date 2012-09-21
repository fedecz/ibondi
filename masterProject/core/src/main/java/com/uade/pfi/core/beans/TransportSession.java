/**
 * 
 */
package com.uade.pfi.core.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uade.pfi.core.utils.TransportMeStringCreator;

/**
 * @author chiwi
 *
 */
@Document(collection="sessions")
public class TransportSession {
	@Id
	private String 			id;
	private String 			transportId; //por ahora solo el id. Despues vemos si usamos DBRefs.
	private Location 		lastKnownLocation;
	private List<Location> 	locations = new ArrayList<Location>();
	@Indexed(direction=IndexDirection.DESCENDING)
	private Date			lastUpdated;

	public TransportSession() {
	}
	
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String name) {
		this.transportId = name;
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
