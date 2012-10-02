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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import static org.springframework.data.mongodb.core.mapreduce.MapReduceOptions.options;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.utils.TransportMeStringCreator;

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
	 * @see com.uade.pfi.core.dao.repositories.CustomSessionRepository#findActiveSessions()
	 */
	public List<TransportSession> findActiveSessions() {
		logger.debug("retrieveing all sessions");
		Query query = new Query(getCommonCriteria());
		return executeMapReduceAndReturnResults(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.uade.pfi.core.dao.repositories.CustomSessionRepository#findActiveSessions(com.uade.pfi.core.beans.Location)
	 */
	public List<TransportSession> findActiveSessions(Location myLocation) {
		logger.debug("retrieveing all sessions for location: " + TransportMeStringCreator.toString(myLocation));
		Query query = new Query(getCommonCriteria().andOperator(new Criteria("lastKnownLocation")
					.withinSphere(new Circle(new Point(myLocation.getLatitude(),myLocation.getLongitude()), 100))));
		return executeMapReduceAndReturnResults(query);
	}
	
	
	private List<TransportSession> executeMapReduceAndReturnResults(Query query) {
		MapReduceResults<TransportSession> mapReduceResults = template.mapReduce(
				query, 
				"sessions", 
				"classpath:/mapReduce/map.js", 
				"classpath:/mapReduce/reduce.js",
				options().finalizeFunction("classpath:/mapReduce/finalize.js").outputTypeInline(), 
				TransportSession.class);
		List<TransportSession> result = new ArrayList<TransportSession>(mapReduceResults.getCounts().getOutputCount());
		Iterator<TransportSession> iterator = mapReduceResults.iterator();
		while(iterator.hasNext()){
			TransportSession s = iterator.next();
			result.add(s);
		}
		return result;
	}
	
	private Criteria getCommonCriteria(){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, -10);
		return where("lastUpdated")
				.gt(calendar.getTime());
	}

}
