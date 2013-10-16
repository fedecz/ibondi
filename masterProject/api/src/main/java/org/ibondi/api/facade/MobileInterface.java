package org.ibondi.api.facade;

import org.ibondi.api.dto.LocationDTO;
import org.ibondi.api.dto.SessionCheckInDTO;
import org.ibondi.api.dto.SessionUpdateDto;
import org.ibondi.api.dto.TransportListDTO;
import org.ibondi.api.dto.TransportLocationListDTO;
import org.ibondi.api.dto.TransportTypeListDTO;

public interface MobileInterface {

	public String checkIn(SessionCheckInDTO sessionCheckIn);
	
	public TransportLocationListDTO getAllLocations();
	
	public TransportLocationListDTO getLocations(LocationDTO myLocation);
	
	public Boolean postLocation(SessionUpdateDto location);
	
	public TransportListDTO getTransportListBy(String transportType);

	public TransportTypeListDTO getTransportTypeList(String locale);
}