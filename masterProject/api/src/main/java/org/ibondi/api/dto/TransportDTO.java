package org.ibondi.api.dto;

import org.ibondi.api.enums.TransportTypeEnum;

/**
 * Represents a Transport based on a name, a branch (ramal), heading (sentido) and a type
 *
 */
public class TransportDTO {
	private String id;
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
	
	public TransportDTO() {
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
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTransportType(TransportTypeEnum transportType) {
		this.transportType = transportType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
