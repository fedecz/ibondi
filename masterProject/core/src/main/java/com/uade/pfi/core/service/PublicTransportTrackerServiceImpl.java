package com.uade.pfi.core.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.SessionDao;
import com.uade.pfi.core.exception.InvalidSessionException;
import com.uade.pfi.core.utils.TransportMeStringCreator;

public class PublicTransportTrackerServiceImpl implements
		PublicTransportTrackerService {
	private Log logger = LogFactory.getLog(PublicTransportTrackerServiceImpl.class);
	
	private SessionDao dao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.uade.pfi.core.service.PublicTransportTrackerService#retrieveLocations()
	 */
	public List<TransportSession> retrieveAllSessions() {
		logger.debug("retriveAllSessions()");
		List<TransportSession> sessions = dao.retrieveActiveSessions();
		logger.debug("sessions: " + TransportMeStringCreator.toString(sessions));
		return sessions;
	}

	public void updatePosition(Location location, String sessionId) {
		logger.debug("updatePosition(), session id: "+ sessionId);
		logger.debug("location: "+ TransportMeStringCreator.toString(location).toString());
		validateLocation(location);
//		this.mergeOrInsertPosition(location);
		location.setTrackedOn(new Date());
//		dao.setLatestLocationTo(sessionId,location);
//		dao.addLocationToList(sessionId, location);
//		dao.updateTime(sessionId);
		TransportSession session = dao.get(sessionId);
		if(session==null)
			throw new InvalidSessionException("Invalid session: " + sessionId);
		logger.debug("got session: " + session);
		session.getLocations().add(location);
		session.setLastKnownLocation(location);
		session.setLastUpdated(new Date());
		dao.save(session);
		logger.debug("Saved session: " + TransportMeStringCreator.toString(session));
	}

	private void validateLocation(Location location) {
		if(location==null)
			throw new IllegalArgumentException("Location cannot be null");
		if(location.getLatitude()==null || location.getLongitude()==null)
			throw new IllegalArgumentException("Latitude or longitude cannot be null");
	}

	
	/**
	 * Hay que implementar "haversine".
	 * http://www.movable-type.co.uk/scripts/latlong.html
	 * http://www.codecodex.com/wiki/Calculate_Distance_Between_Two_Points_on_a_Globe
	 * 
	 * @param aLocation
	 */
//	private void mergeOrInsertPosition(TransportLocation aLocation) {
//		TransportLocation oldLocation = locations.get(aLocation.getName());
//		if(oldLocation!=null){
//			Location newLocation = new Location();
//			newLocation.setLatitude((oldLocation.getLocation().getLatitude() + aLocation.getLocation().getLatitude()) / 2);
//			newLocation.setLongitude((oldLocation.getLocation().getLongitude() + aLocation.getLocation().getLongitude()) / 2);
//			oldLocation.setLocation(newLocation);
//			return;	
//		}
//		locations.put(aLocation.getName(), aLocation);
//	}
	
	public void setDao(SessionDao dao) {
		this.dao = dao;
	}
	public SessionDao getDao() {
		return dao;
	}

	public List<TransportSession> retrieveSessions(Location myLocation) {
		//TODO esta implementacion devuelve los transportLocations alrededor de myLocation.
		return null;
	}

	public String checkIn(String transportName) {
		logger.debug("cheking in transportName: " + transportName);
		if(transportName== null || transportName.trim().equals(""))
			throw new IllegalArgumentException("Transport Name Should not be Null.");
		TransportSession session = new TransportSession();
		session.setName(transportName);
		String id = dao.insert(session);
		logger.debug("Inserted session: " + TransportMeStringCreator.toString(session));
		return id;
	}


}
