/**
 * 
 */
package com.uade.pfi.core.dto;

/**
 * 
 * Esta clase es usada <strong>solamente</strong> como medio de transporte
 * entre la web y el dispositivo movil.
 *
 * @author chiwi
 */
public class TransportLocationDTO {
	private String	sessionId;
	private String 	name;
	private Float	latitude;
	private Float	longitude;
	
	public TransportLocationDTO(){	}
	
	public TransportLocationDTO(String name, Integer latitud, Integer longitude){
		this.name = name;
		this.latitude = new Float(latitud);
		this.longitude = new Float(longitude);
	}
	
	public TransportLocationDTO(String name, Float latitud, Float longitud){
		this.name = name;
		this.latitude = latitud;
		this.longitude = longitud;
	}
	
	public TransportLocationDTO(String sessionId, String name, Float latitud, Float longitud){
		this(name, latitud,longitud);
		this.sessionId = sessionId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
