package com.uade.pfi.core.dao;

import java.util.List;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;

public interface SessionDao {

	List<TransportSession> retrieveActiveSessions();

	TransportSession get(String sessionId);
	
	String insert(TransportSession session);
	
	void save(TransportSession session);
	
	void setLatestLocationTo(String sessionId, Location location);

	void addLocationToList(String sessionId, Location location);

	void updateTime(String sessionId);

}
