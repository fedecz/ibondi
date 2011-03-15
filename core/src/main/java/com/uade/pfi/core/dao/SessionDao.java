package com.uade.pfi.core.dao;

import java.util.List;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;

public interface SessionDao {

	List<TransportSession> retrieveActiveSessions();

	TransportSession get(Long sessionId);
	
	Long insert(TransportSession session);
	
	void save(TransportSession session);
	
	void save(Location location);

}
