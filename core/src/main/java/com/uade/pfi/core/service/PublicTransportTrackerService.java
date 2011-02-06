package com.uade.pfi.core.service;

import java.util.List;

import com.uade.pfi.core.TransportLocation;

public interface PublicTransportTrackerService {

	List<TransportLocation> retrieveLocations();

	void updatePosition(TransportLocation location);
	
	
}
