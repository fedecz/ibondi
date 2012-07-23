package com.uade.pfi.core.facade;

import com.uade.pfi.core.dto.LocationDTO;
import com.uade.pfi.core.dto.TransportLocationDTO;

public interface MobileInterface {

	public String checkIn(String transportName);
	
	public TransportLocationDTO[] getAllLocations();
	
	public TransportLocationDTO[] getLocations(LocationDTO myLocation);
	
	public Boolean postLocation(TransportLocationDTO location);

}