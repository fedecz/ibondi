/**
 * 
 */
package com.uade.pfi.core.test.mocks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.SessionDao;

/**
 * @author chiwi
 *
 */
public class SessionsDaoMock implements SessionDao{

	private Map<Long,TransportSession> sessions = new HashMap<Long, TransportSession>();

	public List<TransportSession> retrieveActiveSessions() {
		return Arrays.asList(sessions.values().toArray(new TransportSession[0]));
	}

	public Long insert(TransportSession session) {
		sessions.put(new Long(session.hashCode()),session);
		return new Long(session.hashCode());
	}

	public TransportSession get(Long sessionId) {
		return sessions.get(sessionId);
	}

	public void save(TransportSession session) {
		sessions.put(new Long(session.hashCode()),session);		
	}

	public void save(Location location) {
		// TODO Auto-generated method stub
		
	}



}

