package com.uade.pfi.core.dto;

/** 
 * Represents the user session check in with the associated transport
 *
 */
public class SessionCheckInDTO {
	private String transportName;
	private LocationDTO initialLocation;

	public SessionCheckInDTO(String transportName, LocationDTO initialLocation) {
		this.transportName = transportName;
		this.initialLocation = initialLocation;
	}

	public String getTransportName() {
		return transportName;
	}
	
	public LocationDTO getInitialLocation() {
		return initialLocation;
	}

}
