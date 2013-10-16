package org.ibondi.core.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibondi.core.aop.TimeWatched;
import org.ibondi.core.beans.Location;
import org.ibondi.core.beans.TransportSession;
import org.ibondi.core.repositories.SessionRepository;
import org.ibondi.core.utils.TransportMeStringCreator;
import org.springframework.beans.factory.annotation.Autowired;

public class PublicTransportTrackerServiceImpl implements
		PublicTransportTrackerService {
	private Log logger = LogFactory.getLog(PublicTransportTrackerServiceImpl.class);
	
	@Autowired
	private SessionRepository sessionsRepo;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.uade.pfi.core.service.PublicTransportTrackerService#retrieveLocations()
	 */
	@TimeWatched
	public List<TransportSession> retrieveAllSessions() {
		logger.debug("retriveAllSessions()");
		List<TransportSession> sessions = sessionsRepo.findActiveSessions();
		logger.debug("sessions: " + TransportMeStringCreator.toString(sessions));
		return sessions;
	}

	@TimeWatched
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
//		if(location.getLatitude()==null || location.getLongitude()==null)
//			throw new IllegalArgumentException("Latitude or longitude cannot be null");
	}

	
	public void setSessionsRepository(SessionRepository repo) {
		this.sessionsRepo = repo;
	}
	

	@TimeWatched
	public List<TransportSession> retrieveSessions(Location myLocation) {
		return sessionsRepo.findActiveSessions(myLocation);
	}

	@TimeWatched
	public String checkIn(String transportId) {
		logger.debug("cheking in transportName: " + transportId);
		if(transportId== null || transportId.trim().equals(""))
			throw new IllegalArgumentException("Transport Id Should not be Null.");
		TransportSession session = new TransportSession(transportId);
		TransportSession savedSession = sessionsRepo.save(session);
		return savedSession.getId();
	}

	public TransportSession findSession(String sessionId) {
		return sessionsRepo.findOne(sessionId);
	}


}
