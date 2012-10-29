package com.uade.pfi.api.facade;

import com.uade.pfi.api.dto.LocationDTO;
import com.uade.pfi.api.dto.SessionCheckInDTO;
import com.uade.pfi.api.dto.SessionUpdateDto;
import com.uade.pfi.api.dto.TransportListDTO;
import com.uade.pfi.api.dto.TransportLocationListDTO;

public interface MobileInterface {

	public String checkIn(SessionCheckInDTO sessionCheckIn);
	
	public TransportLocationListDTO getAllLocations();
	
	public TransportLocationListDTO getLocations(LocationDTO myLocation);
	
	public Boolean postLocation(SessionUpdateDto location);
	
	public TransportListDTO getTransportListBy(String transportType);

}