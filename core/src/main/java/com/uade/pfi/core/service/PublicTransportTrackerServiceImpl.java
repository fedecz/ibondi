package com.uade.pfi.core.service;

import java.util.ArrayList;
import java.util.List;

import com.uade.pfi.core.Location;
import com.uade.pfi.core.TransportLocation;

public class PublicTransportTrackerServiceImpl implements
		PublicTransportTrackerService {
	
	private List<TransportLocation> locations = new ArrayList<TransportLocation>();

	public List<TransportLocation> retrieveLocations() {
		return this.locations;
	}

	public void updatePosition(TransportLocation location) {
		this.mergeOrInsertPosition(location);
	}

	private void mergeOrInsertPosition(TransportLocation aLocation) {
		for (TransportLocation location : locations) {
			if(location.getName().equals(aLocation.getName())){
				Location newLocation = new Location();
				newLocation.setLatitude((location.getLocation().getLatitude() + aLocation.getLocation().getLatitude()) / 2);
				location.setLocation(newLocation);
				return;
			}
		}
		this.locations.add(aLocation);
	}


}
