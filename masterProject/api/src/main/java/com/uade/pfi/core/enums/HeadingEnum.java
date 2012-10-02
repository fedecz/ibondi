package com.uade.pfi.core.enums;


public enum HeadingEnum {
	IDA("IDA"), VUELTA("VUELTA");

	private String description;
	
	private HeadingEnum(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	

}
