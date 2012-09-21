package com.uade.pfi.core.dto;

/** 
 * Represents the user session check in with the associated transport
 *
 */
public class SessionCheckInDTO {
	private String transportId;
	private LocationDTO initialLocation;

	public SessionCheckInDTO(String transportId, LocationDTO initialLocation) {
		this.transportId = transportId;
		this.initialLocation = initialLocation;
	}

	public String getTransportId() {
		return transportId;
	}
	
	public LocationDTO getInitialLocation() {
		return initialLocation;
	}

}
