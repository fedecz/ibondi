/**
 * 
 */
package com.uade.pfi.core.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.uade.pfi.core.utils.TransportMeStringCreator;

/**
 * @author chiwi
 *
 */
@Entity(name="session")
@Table(name="session")
public class TransportSession {
	@Id
	@GeneratedValue
	private Long 			id;
	private String 			name;
	
	@OneToOne
	private Location 		lastKnownLocation;
	
	@OneToMany
	@OrderBy("trackedOn DESC")
	private List<Location> 	locations = new ArrayList<Location>();
	
	@Temporal(TemporalType.TIMESTAMP)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
