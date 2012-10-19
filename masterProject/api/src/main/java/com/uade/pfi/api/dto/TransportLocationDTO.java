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
	private Float	latitude;
	private Float	longitude;
	
	public TransportLocationDTO(){	}
	
	public TransportLocationDTO(String transportId, Integer latitud, Integer longitude){
		this.transportId = transportId;
		this.latitude = new Float(latitud);
		this.longitude = new Float(longitude);
	}
	
	
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

}
