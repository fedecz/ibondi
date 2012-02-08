/**
 * 
 */
package com.uade.pfi.core.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;

/**
 * @author chiwi
 *
 */
public class SimpleSessionsDaoImpl implements SessionDao {

	private Map<String, TransportSession> sessions = new Hashtable<String, TransportSession>();
	
	/* (non-Javadoc)
	 * @see com.uade.pfi.core.dao.SessionsDao#retrieveActiveSessions()
	 */
	public List<TransportSession> retrieveActiveSessions() {
		return Arrays.asList(sessions.entrySet().toArray(new TransportSession[0]));
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.dao.SessionsDao#get(java.lang.Long)
	 */
	public TransportSession get(String sessionId) {
		return sessions.get(sessionId);
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.dao.SessionsDao#save(com.uade.pfi.core.beans.TransportSession)
	 */
	public String insert(TransportSession session) {
		if(session.getId()==null)
			session.setId(new Date().getTime()+"");
		sessions.put(session.getId(), session);
		return session.getId();
	}

	public void save(TransportSession session) {
		sessions.put(session.getId(), session);
	}

	public void save(Location location) {
		
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
