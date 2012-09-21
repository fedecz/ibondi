package com.uade.pfi.core.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.criteria.ActiveSessionCriteriaStrategy;
import com.uade.pfi.core.dao.criteria.DefaultActiveSessionCriteriaStrategy;

//FIXME: implementar esta clase como repository. Los custom Methods hacerlos con esto:
//http://static.springsource.org/spring-data/data-mongo/docs/current/reference/html/#repositories.single-repository-behaviour
public class MongoDBSessionsDao implements SessionDao {
	private Log logger = LogFactory.getLog(MongoDBSessionsDao.class);
	
	private MongoTemplate template;
	private static final ActiveSessionCriteriaStrategy<Criteria> activeSessionCriteria = new DefaultActiveSessionCriteriaStrategy();
	
	public List<TransportSession> retrieveActiveSessions() {
		logger.debug("retrieveing all sessions");
		List<TransportSession> list = template.find(new Query(activeSessionCriteria.getCriteria()), TransportSession.class);
		if(list==null)
			list=new ArrayList<TransportSession>();
		logger.debug("returning sessions:" + list);
		logger.debug("count: " + list.size());
		return list;
	}

	public TransportSession get(String sessionId) {
		logger.debug("getting session id:" + sessionId);
		TransportSession session = template.findOne(new Query(where("id").is(sessionId)), TransportSession.class);
		if(session == null){
			logger.debug("session is null");
			return null;
		}
		logger.debug("found session: " + session.toString());
		return session;
	}

	public String insert(TransportSession session) {
		logger.debug("inserting session: " + session.toString());
		//FIXME: sacar el UUID y usar el ObjectId nativo de Mongo... http://www.mongodb.org/display/DOCS/Object+IDs
		session.setId(UUID.randomUUID().toString());
		template.save(session,"sessions");
		logger.debug("returning id: " + session.getId());
		return session.getId();
	}

	public void save(TransportSession session) {
		logger.debug("saving session");
		template.save(session);
		logger.debug("saving session: done");
	}

	
	public MongoTemplate getTemplate() {
		return template;
	}
	public void setTemplate(MongoTemplate template) {
		this.template = template;
	}

	public void setLatestLocationTo(String sessionId, Location location) {
		logger.debug("before setting lastKNownLocation.");
		WriteResult result = template.updateFirst(new Query(where("id").is(sessionId)), new Update().set("lastKnownLocation", location),"sessions");
		logger.info(result.toString());
	}

	public void addLocationToList(String sessionId, Location location) {
		logger.debug("before adding location to list");
		WriteResult result = template.updateFirst(new Query(where("id").is(sessionId)), new Update().addToSet("locations", location),"sessions");
		logger.info(result.toString());
	}

	public void updateTime(String sessionId) {
		logger.debug("before updataing Sessions time");
		WriteResult result = template.updateFirst(new Query(where("id").is(sessionId)), new Update().set("lastUpdated", new Date()),"sessions");
		logger.info(result.toString());
	}


}
