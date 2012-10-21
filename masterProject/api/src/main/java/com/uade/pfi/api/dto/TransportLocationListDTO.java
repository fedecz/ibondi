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

	public TransportLocationDTO[] getTransports() {
		return transports;
	}
	public void setTransports(TransportLocationDTO[] transports) {
		this.transports = transports;
	}
	public TransportLocationListDTO() {
	}
}
