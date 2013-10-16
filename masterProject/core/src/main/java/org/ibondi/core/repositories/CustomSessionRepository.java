/**
 * 
 */
package org.ibondi.core.repositories;

import java.util.List;

import org.ibondi.core.beans.Location;
import org.ibondi.core.beans.TransportSession;

/**
 * @author fedec
 *
 */
public interface CustomSessionRepository {
	
	void trackLocation(String sessionId, Location location);
	
	List<TransportSession> findActiveSessions();
	
	List<TransportSession> findActiveSessions(Location myLocation);

}
