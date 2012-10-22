/**
 * 
 */
package com.uade.pfi.api.dto;

/**
 * 
 * Esta clase es usada <strong>solamente</strong> como medio de transporte
 * entre la web y el dispositivo movil.
 *
 * @author chiwi
 */
public class TransportLocationDTO {
	private String transportId;
	private LocationDTO location;
	
	public TransportLocationDTO(){	}
	
	public TransportLocationDTO(String transportId, LocationDTO location){
		this.transportId = transportId;
		this.location = location;
	}
	
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

}
