package org.ibondi.api.dto;

import java.util.List;

public class TransportTypeListDTO {

	public TransportTypeListDTO() {
	}
	private List<String> transportTypes;

	public TransportTypeListDTO(List<String> transportTypes) {
		this.transportTypes = transportTypes;
	}

	public List<String> getTransportTypes() {
		return transportTypes;
	}
}
