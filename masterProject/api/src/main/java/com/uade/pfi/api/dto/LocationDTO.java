/**
 * 
 */
package com.uade.pfi.api.dto;

/**
 * Represents the radian longitude/latitude location
 *
 */
public class LocationDTO {
	private Float latitude;
	private Float longitude;

	public LocationDTO() {
	}
	
	public LocationDTO(float latitude, float longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public Float getLongitude() {
		return longitude;
	}

}
