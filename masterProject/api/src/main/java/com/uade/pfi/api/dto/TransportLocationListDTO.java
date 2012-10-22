/**
 * 
 */
package com.uade.pfi.api.dto;

/**
 * @author fedec
 *
 */
public class TransportLocationListDTO {
	private TransportLocationDTO[] transports = new TransportLocationDTO[0];
	private LocationDTO center = new LocationDTO(0,0);
	private int	zoom=2;

	public TransportLocationListDTO() {
	}
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	public TransportLocationDTO[] getTransports() {
		return transports;
	}
	public void setTransports(TransportLocationDTO[] transports) {
		this.transports = transports;
	}
	public LocationDTO getCenter() {
		return center;
	}
	public void setCenter(LocationDTO center) {
		this.center = center;
	}
}
