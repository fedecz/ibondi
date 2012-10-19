package com.uade.pfi.api.dto;

/** 
 * Represents the user session check in with the associated transport
 *
 */
public class SessionCheckInDTO {
	private String transportId;
	private LocationDTO initialLocation;

	public SessionCheckInDTO() {
	}

	public String getTransportId() {
		return transportId;
	}

	public LocationDTO getInitialLocation() {
		return initialLocation;
	}
	public void setInitialLocation(LocationDTO initialLocation) {
		this.initialLocation = initialLocation;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

}
