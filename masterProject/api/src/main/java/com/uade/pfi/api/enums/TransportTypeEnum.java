package com.uade.pfi.api.enums;

import java.util.Arrays;
import java.util.List;


public enum TransportTypeEnum {
	BUS("Colectivo"), TRAIN("Tren"), SUBWAY("Subte"), SHIP("Lancha");
		
	private String codeAsString;
	
	TransportTypeEnum(String description) {
		this.codeAsString = description;
	}
	
	public String code() {
		return codeAsString;
	}

	public static List<String> getTranportTypeEnumAsStringList(){
		return Arrays.asList(BUS.code(), TRAIN.code(), SUBWAY.code(), SHIP.code());
	}

	public static TransportTypeEnum getTransportTypeBy(String transportTypeName) {
		if (transportTypeName.contains("Colectivo")) return BUS;
		if (transportTypeName.contains("Tren")) return TRAIN;
		if (transportTypeName.contains("Subte")) return SUBWAY;
		if (transportTypeName.contains("Lancha")) return SHIP;
		return null;
	}
	
}
