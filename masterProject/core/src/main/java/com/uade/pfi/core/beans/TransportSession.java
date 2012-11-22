/**
 * 
 */
package com.uade.pfi.core.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
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
	@GeoSpatialIndexed
	private Location 		lastKnownLocation;
	private List<Location> 	locations = new ArrayList<Location>();
	@Indexed(direction=IndexDirection.DESCENDING,name="lastUpdated")
	private Date			lastUpdated;

	
	public TransportSession(String transportId,
			Location lastKnownLocation, List<Location> locations,
			Date lastUpdated) {
		super();
		this.transportId = transportId;
		this.lastKnownLocation = lastKnownLocation;
		this.locations = locations;
		this.lastUpdated = lastUpdated;
	}
	
	public TransportSession(String transportId){
		this(transportId,null,null,null);
	}
	
	@PersistenceConstructor
	public TransportSession(String id, String transportId,
			Location lastKnownLocation, List<Location> locations,
			Date lastUpdated) {
		super();
		this.id = id;
		this.transportId = transportId;
		this.lastKnownLocation = lastKnownLocation;
		this.locations = locations;
		this.lastUpdated = lastUpdated;
	}

	public String getTransportId() {
		return transportId;
	}

	public String toString(){
		return TransportMeStringCreator.toString(this);
	}

	public String getId() {
		return id;
	}


	public Collection<Location> getLocations() {
		return locations;
	}


	public Date getLastUpdated() {
		return lastUpdated;
	}

	public Location getLastKnownLocation() {
		return lastKnownLocation;
	}
}
