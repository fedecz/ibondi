/**
 * 
 */
package com.uade.pfi.core.repositories;

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
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.mapper.Converter;
import com.uade.pfi.core.repositories.mapreduce.DefaultMapReduceSessionConverter;
import com.uade.pfi.core.repositories.mapreduce.MapReduceSessionOutput;
import com.uade.pfi.core.utils.TransportMeStringCreator;

/**
 * @author fedec
 *
 */
public class SessionRepositoryCustomImpl implements
		CustomSessionRepository {
	private double radius = 0.0007; //(1º == 111km)

	private Log logger = LogFactory.getLog(SessionRepositoryCustomImpl.class);
	
	@Autowired
	private MongoOperations template;
	
	private Converter<MapReduceSessionOutput, List<TransportSession>> mapReduceConverter = new DefaultMapReduceSessionConverter();
	
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
		Query query = new Query(getCommonCriteria().and("lastKnownLocation")
					.withinSphere(new Circle(new Point(myLocation.getLatitude(),myLocation.getLongitude()), radius)));
		return executeMapReduceAndReturnResults(query);
	}
	
	
	private List<TransportSession> executeMapReduceAndReturnResults(Query query) {
		MapReduceResults<MapReduceSessionOutput> mapReduceResults = template.mapReduce(
				query, 
				"sessions", 
				"classpath:/mapReduce/map.js", 
				"classpath:/mapReduce/reduce.js",
				MapReduceSessionOutput.class);
		List<TransportSession> result = new ArrayList<TransportSession>(mapReduceResults.getCounts().getOutputCount());
		Iterator<MapReduceSessionOutput> iterator = mapReduceResults.iterator();
		while(iterator.hasNext()){
			MapReduceSessionOutput output = iterator.next();
			result.addAll(mapReduceConverter.convert(output));
		}
		return result;
	}
	
	private Criteria getCommonCriteria(){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, -10);
		return where("lastUpdated")
				.gt(calendar.getTime());
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}

}
