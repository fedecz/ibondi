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

	public TransportLocationUpdateDto() {
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
