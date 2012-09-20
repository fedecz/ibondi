/**
 * 
 */
package com.uade.pfi.core.dto;

/**
 * Represents the radian longitude/latitude location
 *
 */
public class LocationDTO {
	private Float latitude;
	private Float longitude;

	public LocationDTO(Float latitude, Float longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public Float getLongitude() {
		return longitude;
	}

}
