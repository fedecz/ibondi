/**
 * 
 */
package com.uade.pfi.core.repositories;

import java.util.List;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;

/**
 * @author fedec
 *
 */
public interface CustomSessionRepository {
	
	void trackLocation(String sessionId, Location location);
	
	List<TransportSession> findActiveSessions();
	
	List<TransportSession> findActiveSessions(Location myLocation);

}
