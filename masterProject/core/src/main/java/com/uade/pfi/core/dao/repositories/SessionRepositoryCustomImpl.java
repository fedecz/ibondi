/**
 * 
 */
package com.uade.pfi.core.dao.repositories;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;

/**
 * @author fedec
 *
 */
public class SessionRepositoryCustomImpl implements
		CustomSessionRepository {
	private Log logger = LogFactory.getLog(SessionRepositoryCustomImpl.class);
	
	@Autowired
	private MongoOperations template;
	
	/* (non-Javadoc)
	 * @see com.uade.pfi.core.dao.repositories.SessionRepositoryCustomRepository#trackLocation(java.lang.String, com.uade.pfi.core.beans.Location)
	 */
	public void trackLocation(String sessionId, Location location) {
		template.updateFirst(query(where("id").is(sessionId)), new Update()
				.set("lastKnownLocation", location)
				.set("lastUpdated", new Date())
				.push("locations", location)
			,TransportSession.class);

	}

	/*
	 * (non-Javadoc)
	 * @see com.uade.pfi.core.dao.repositories.CustomSessionRepository#retrieveActiveSessions()
	 */
	public List<TransportSession> findActiveSessions() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, -10);
		logger.debug("retrieveing all sessions");
		List<TransportSession> activeSessions = template.find(new Query(where("lastUpdated").gt(calendar.getTime())), TransportSession.class);
		if(activeSessions==null)
			activeSessions=new ArrayList<TransportSession>();
		logger.debug("returning sessions:" + activeSessions);
		logger.debug("count: " + activeSessions.size());
		return activeSessions;
	}

	public List<TransportSession> findActiveSessions(Location myLocation) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, -10);
		logger.debug("retrieveing all sessions");
		Query query = new Query(where("lastUpdated")
				.gt(calendar.getTime())
				.andOperator(new Criteria("lastKnownLocation")
					.withinSphere(new Circle(new Point(myLocation.getLatitude(),myLocation.getLongitude()), 100))));
		List<TransportSession> activeSessions = template.find(query
			, TransportSession.class);
		MapReduceResults<TransportSession> mapReduceResults = template.mapReduce(query, "sessions", "classpath:map.js", "classpath:reduce.js",TransportSession.class);
		if(activeSessions==null)
			activeSessions=new ArrayList<TransportSession>();
		logger.debug("returning sessions:" + activeSessions);
		logger.debug("count: " + activeSessions.size());
		return activeSessions;
	}

}
