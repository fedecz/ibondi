package org.ibondi.core.service;

import java.util.List;

import org.ibondi.core.beans.Location;
import org.ibondi.core.beans.TransportSession;

public interface PublicTransportTrackerService {
	TransportSession findSession(String sessionId);
	
	List<TransportSession> retrieveAllSessions();

	List<TransportSession> retrieveSessions(Location myLocation);

	void updatePosition(Location location, String sessionId);

	String checkIn(String transportName);

}
