package com.uade.pfi.core.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uade.pfi.core.enums.TransportTypeEnum;

@Document(collection="transports")
public class Transport {
	@Id
	private String id;
	
	@Indexed
	private String name;
	
	@Indexed
	private String branch;
	
	@Indexed
	private String heading;
	
	private TransportTypeEnum transportType;
	
	public Transport(String name, String branch, String heading,
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

	public String getId() {
		return id;
	}
}
