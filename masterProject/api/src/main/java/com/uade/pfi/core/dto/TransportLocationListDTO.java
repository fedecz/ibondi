/**
 * 
 */
package com.uade.pfi.core.dto;

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
