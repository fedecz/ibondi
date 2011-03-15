package com.uade.pfi.core.facade;

import com.uade.pfi.core.dto.LocationDTO;
import com.uade.pfi.core.dto.TransportLocationDTO;

public interface MobileInterface {

	public abstract Long checkIn(String transportName);
	
	public abstract TransportLocationDTO[] getAllLocations();
	
	public abstract TransportLocationDTO[] getLocations(LocationDTO myLocation);
	
	public abstract Boolean postLocation(TransportLocationDTO location);

}