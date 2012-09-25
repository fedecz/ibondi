package com.uade.pfi.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum HeadingEnum {
	IDA, VUELTA;

	private static final Map<String, HeadingEnum> headingMap = new HashMap<String, HeadingEnum>();
	
	static {
		headingMap.put("IDA", IDA);
		headingMap.put("VUELTA", VUELTA);
	}
	
	public static HeadingEnum getHeadingFrom(String headingAsString) {
		return headingMap.get(headingAsString);
	}
}
