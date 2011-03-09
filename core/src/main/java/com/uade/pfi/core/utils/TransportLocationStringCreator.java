/**
 * 
 */
package com.uade.pfi.core.utils;

import org.springframework.core.style.ToStringCreator;

import com.uade.pfi.core.dto.TransportLocation;

/**
 * @author chiwi
 *
 */
public class TransportLocationStringCreator {

	public static String convert(TransportLocation location) {
		ToStringCreator c = new ToStringCreator(location);
		c.append("Name", location.getName());
		c.append("Latitude",location.getLocation().getLatitude());
		c.append("Longitude",location.getLocation().getLongitude());
		return c.toString();
	}

}
