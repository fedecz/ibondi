/**
 * 
 */
package com.uade.pfi.core.dto;

/**
 * @author fedec
 *
 */
public class TransportLocationUpdateDto {
	
	private String sessionId;
	private float latitude;
	private float longitude;

	public TransportLocationUpdateDto(String sessionId, float latitude, float longitude) {
		super();
		this.sessionId = sessionId;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public String getSessionId() {
		return sessionId;
	}

}
