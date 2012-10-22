package com.uade.pfi.api.facade;

import com.uade.pfi.api.dto.LocationDTO;
import com.uade.pfi.api.dto.SessionCheckInDTO;
import com.uade.pfi.api.dto.TransportLocationListDTO;
import com.uade.pfi.api.dto.SessionUpdateDto;

public interface MobileInterface {

	public String checkIn(SessionCheckInDTO sessionCheckIn);
	
	public TransportLocationListDTO getAllLocations();
	
	public TransportLocationListDTO getLocations(LocationDTO myLocation);
	
	public Boolean postLocation(SessionUpdateDto location);

}