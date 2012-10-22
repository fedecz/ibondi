package com.uade.pfi.api.enums;

import java.util.HashMap;
import java.util.Map;

//TODO: AGREGAR EL RESTO DE LOS MAPEOS SI FUERA NECESARIO
public enum TransportTypeEnum {
	BUS, TRAIN, SUBWAY, SHIP;
		
	private static final Map<String, TransportTypeEnum> transportTypeMap = new HashMap<String, TransportTypeEnum>();
	
	static {
		transportTypeMap.put("BUS", BUS);
		transportTypeMap.put("COLECTIVO", BUS);
		transportTypeMap.put("TRAIN", TRAIN);
		transportTypeMap.put("TREN", TRAIN);
	}
	
	public static TransportTypeEnum createTransportType(String headingAsString) {
		return transportTypeMap.get(headingAsString);
	}
	
}
