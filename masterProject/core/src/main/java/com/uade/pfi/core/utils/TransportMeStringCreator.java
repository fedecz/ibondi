/**
 * 
 */
package com.uade.pfi.core.utils;

import java.util.Collection;
import java.util.List;

import org.springframework.core.style.ToStringCreator;

import com.uade.pfi.api.dto.SessionUpdateDto;
import com.uade.pfi.api.dto.TransportLocationDTO;
import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;

/**
 * @author chiwi
 *
 */
public class TransportMeStringCreator {

	public static String toString(TransportLocationDTO location) {
		ToStringCreator c = new ToStringCreator(location);
		c.append("TransportId",location.getTransportId());
		c.append("Latitude",location.getLocation().getLatitude());
		c.append("Longitude",location.getLocation().getLongitude());
		return c.toString();
	}
	
	
	public static String toString(TransportSession session) {
		ToStringCreator c = new ToStringCreator(session);
		c.append("Id",session.getId());
		c.append("TransportID", session.getTransportId());
		c.append("LastKnownLocation", session.getLastKnownLocation()!=null?TransportMeStringCreator.toString(session.getLastKnownLocation()):null);
		c.append("LastUpdated",session.getLastUpdated());
		c.append("Locations",TransportMeStringCreator.toString(session.getLocations()));
		return c.toString();
	}
	
	public static String toString(Collection<Location> locations) {
		if(locations== null){
			return "[ null ]";
		}
		ToStringCreator c = new ToStringCreator(locations);
		for (Location location : locations) {
			c.append(TransportMeStringCreator.toString(location));
		}
		return c.toString();
	}


	public static String toString(Location location) {
		ToStringCreator c = new ToStringCreator(location);
		c.append("Latitude", location.getLatitude());
		c.append("Longitude",location.getLongitude());
		c.append("Tracked On",location.getTrackedOn());
		return c.toString();
	}


	public static String toString(List<TransportSession> sessions) {
		ToStringCreator c = new ToStringCreator(sessions);
		for (TransportSession session : sessions) {
			c.append(TransportMeStringCreator.toString(session));
		}
		return c.toString();
	}


	public static String toString(SessionUpdateDto location) {
		ToStringCreator c = new ToStringCreator(location);
		c.append("SessionId", location.getSessionId());
		c.append("Latitude", location.getLatitude());
		c.append("Longitude",location.getLongitude());
		return c.toString();
	}

}
