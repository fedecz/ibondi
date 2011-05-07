package com.uade.pfi.core.service;

import java.util.List;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;

public interface PublicTransportTrackerService {

	List<TransportSession> retrieveAllSessions();

	List<TransportSession> retrieveSessions(Location myLocation);

	void updatePosition(Location location, String sessionId);

	String checkIn(String transportName);

}
