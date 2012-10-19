/**
 * 
 */
package com.uade.pfi.api.dto;

/**
 * @author fedec
 *
 */
public class TransportLocationListDTO {
	private TransportLocationDTO[] transports;

	public TransportLocationDTO[] getTransports() {
		return transports;
	}
	public void setTransports(TransportLocationDTO[] transports) {
		this.transports = transports;
	}
	public TransportLocationListDTO() {
	}
}
