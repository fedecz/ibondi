package com.uade.pfi.core.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.repositories.SessionRepository;
import com.uade.pfi.core.utils.TransportMeStringCreator;

public class PublicTransportTrackerServiceImpl implements
		PublicTransportTrackerService {
	private Log logger = LogFactory.getLog(PublicTransportTrackerServiceImpl.class);
	
	@Autowired
	private SessionRepository sessionsRepo;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.uade.pfi.core.service.PublicTransportTrackerService#retrieveLocations()
	 */
	public List<TransportSession> retrieveAllSessions() {
		logger.debug("retriveAllSessions()");
		List<TransportSession> sessions = sessionsRepo.findActiveSessions();
		logger.debug("sessions: " + TransportMeStringCreator.toString(sessions));
		return sessions;
	}

	public void updatePosition(Location location, String sessionId) {
		logger.debug("updatePosition(), session id: "+ sessionId);
		logger.debug("location: "+ TransportMeStringCreator.toString(location).toString());
		validateLocation(location);
		location.setTrackedOn(new Date());
		sessionsRepo.trackLocation(sessionId, location);
		logger.debug("Location " + TransportMeStringCreator.toString(location) + " tracked to session: " + sessionId);
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
	
	public void setSessionsRepository(SessionRepository repo) {
		this.sessionsRepo = repo;
	}
	

	public List<TransportSession> retrieveSessions(Location myLocation) {
		return sessionsRepo.findActiveSessions(myLocation);
	}

	public String checkIn(String transportId) {
		logger.debug("cheking in transportName: " + transportId);
		if(transportId== null || transportId.trim().equals(""))
			throw new IllegalArgumentException("Transport Id Should not be Null.");
		TransportSession session = new TransportSession(transportId);
		TransportSession savedSession = sessionsRepo.save(session);
		return savedSession.getId();
	}


}
