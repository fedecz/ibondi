/**
 * 
 */
package com.uade.pfi.api.dto;

/**
 * @author fedec
 *
 */
public class SessionUpdateDto {
	
	private String sessionId;
	private double latitude;
	private double longitude;

	public SessionUpdateDto() {
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public String getSessionId() {
		return sessionId;
	}

}
