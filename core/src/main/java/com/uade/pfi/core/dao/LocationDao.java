package com.uade.pfi.core.dao;

import java.util.List;

import com.uade.pfi.core.TransportLocation;

public interface LocationDao {

	void storeLocation(TransportLocation location);

	List<TransportLocation> retrieveLocations();

}