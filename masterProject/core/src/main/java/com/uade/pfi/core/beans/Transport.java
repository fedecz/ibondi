package com.uade.pfi.core.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uade.pfi.core.enums.HeadingEnum;
import com.uade.pfi.core.enums.TransportTypeEnum;

@Document(collection="transports")
@CompoundIndexes(
		@CompoundIndex(name="transport_index",unique=true,def="{'name': 1, 'branch': -1, 'heading': -1}")
)
public class Transport {
	@Id
	private String id;
	
	private String name;
	
	private String branch;
	
	private HeadingEnum heading;
	
	private TransportTypeEnum transportType;
	
	public Transport(String name, String branch, HeadingEnum heading,
			TransportTypeEnum transportType) {
		super();
		this.name = name;
		this.branch = branch;
		this.heading = heading;
		this.transportType = transportType;
	}
	
	@PersistenceConstructor
	public Transport(String id, String name, String branch, HeadingEnum heading,
			TransportTypeEnum transportType) {
		this(name, branch, heading, transportType);
		this.id = id;
	}
	
	public Transport() {
	}

	public String getName() {
		return name;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public HeadingEnum getHeading() {
		return heading;
	}
	
	public TransportTypeEnum getTransportType() {
		return transportType;
	}

	public String getId() {
		return id;
	}
}
