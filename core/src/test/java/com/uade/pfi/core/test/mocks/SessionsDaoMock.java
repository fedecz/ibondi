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

	private Map<String,TransportSession> sessions = new HashMap<String, TransportSession>();

	public List<TransportSession> retrieveActiveSessions() {
		return Arrays.asList(sessions.values().toArray(new TransportSession[0]));
	}

	public String insert(TransportSession session) {
		sessions.put(""+session.hashCode(),session);
		return ""+session.hashCode();
	}

	public TransportSession get(String sessionId) {
		return sessions.get(sessionId);
	}

	public void save(TransportSession session) {
		sessions.put(""+session.hashCode(),session);		
	}

	public void save(Location location) {
		// TODO Auto-generated method stub
		
	}

	public void setLatestLocationTo(String sessionId, Location location) {
		// TODO Auto-generated method stub
		
	}

	public void addLocationToList(String sessionId, Location location) {
		// TODO Auto-generated method stub
		
	}

	public void updateTime(String sessionId) {
		// TODO Auto-generated method stub
		
	}



}

