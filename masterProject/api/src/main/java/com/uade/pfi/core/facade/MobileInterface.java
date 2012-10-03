package com.uade.pfi.core.facade;

import com.uade.pfi.core.dto.LocationDTO;
import com.uade.pfi.core.dto.SessionCheckInDTO;
import com.uade.pfi.core.dto.TransportLocationDTO;
import com.uade.pfi.core.dto.TransportLocationUpdateDto;

public interface MobileInterface {

	public String checkIn(SessionCheckInDTO sessionCheckIn);
	
	public TransportLocationDTO[] getAllLocations();
	
	public TransportLocationDTO[] getLocations(LocationDTO myLocation);
	
	public Boolean postLocation(TransportLocationUpdateDto location);

}