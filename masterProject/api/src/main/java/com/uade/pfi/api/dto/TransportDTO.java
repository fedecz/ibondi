package com.uade.pfi.api.dto;

import com.uade.pfi.api.enums.TransportTypeEnum;

/**
 * Represents a Transport based on a name, a branch (ramal), heading (sentido) and a type
 *
 */
public class TransportDTO {
	
	private String name;
	private String branch;
	private String heading;
	private TransportTypeEnum transportType;
	
	public TransportDTO(String name, String branch, String heading,
			TransportTypeEnum transportType) {
		super();
		this.name = name;
		this.branch = branch;
		this.heading = heading;
		this.transportType = transportType;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public String getHeading() {
		return heading;
	}
	
	public TransportTypeEnum getTransportType() {
		return transportType;
	}

}
