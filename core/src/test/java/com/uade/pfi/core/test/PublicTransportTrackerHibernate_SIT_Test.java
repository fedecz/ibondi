package com.uade.pfi.core.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.SessionDao;
import com.uade.pfi.core.exception.InvalidSessionException;
import com.uade.pfi.core.service.PublicTransportTrackerService;
import com.uade.pfi.core.utils.TransportMeStringCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PublicTransportTrackerHibernate_SIT_Test {

	@Resource(name="transportTrackerService")
	PublicTransportTrackerService service;

	@Autowired
	ApplicationContext context;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Resource(name="locationsDao")
	SessionDao locationDao;

	@Test
	@Transactional
	public void serviceShouldAllowCheckIn(){
		Long sessionId = service.checkIn("152");

		assertNotNull(sessionId);
		assertEquals(1, jdbcTemplate.queryForInt("select count(*) from session"));
		System.out.println(jdbcTemplate.queryForInt("select id from session"));
	}

	@Test
	@Transactional
	public void serviceShouldAllowCheckInTwice(){
		Long sessionId = service.checkIn("152");
		Long sessionId2 = service.checkIn("168");

		assertNotNull(sessionId);
		assertNotNull(sessionId2);
		assertEquals(2, jdbcTemplate.queryForInt("select count(*) from session"));
		System.out.println(sessionId);
		System.out.println(sessionId2);
	}

	@Test
	@Transactional
	public void serviceShouldAllowToPostANewLocationsToASessionWithOtherLocations(){
		Long sessionId = service.checkIn("152");
		Location location1 = new Location(2,3);
		Location location2 = new Location(4,5);
		Location location3 = new Location(6,7);
		service.updatePosition(location1, sessionId);
		service.updatePosition(location2, sessionId);
		service.updatePosition(location3, sessionId);

		Location newLocation = new Location(8,9);
		service.updatePosition(newLocation, sessionId);

		TransportSession session = locationDao.get(sessionId);
		assertNotNull(session);
		assertEquals(4, session.getLocations().size());

	}


	@Test
	@Transactional
	public void serviceShouldAllowToPostANewLocationsOnANewSession(){
		Long sessionId = service.checkIn("152");
		Location location = new Location(2,3);

		service.updatePosition(location, sessionId);

		TransportSession session = locationDao.get(sessionId);
		assertEquals(1, session.getLocations().size());
		System.out.println(sessionId);
	}


	@Test(expected=InvalidSessionException.class)
	@Transactional
	public void serviceShouldFailWhenPostingAnInvalidSessionId(){
		Location location = new Location(2,3);
		long sessionId = 123l;// invalid id

		service.updatePosition(location, sessionId); 

		fail();
	}

	@Test
	@Transactional
	public void serviceShouldRetrieveAllActiveSessionsWithDefaultCriteria(){
		Long sessionId1 = service.checkIn("152");
		service.updatePosition(new Location(2, 3), sessionId1);
		Long sessionId2 = service.checkIn("68");
		service.updatePosition(new Location(4, 5), sessionId2);
		Long sessionId3 = service.checkIn("314");
		service.updatePosition(new Location(5, 6), sessionId3);

		List<TransportSession> sessions = service.retrieveAllSessions();

		assertNotNull(sessions);
		assertEquals(3, sessions.size());
		for (TransportSession session : sessions) {
			assertNotNull(session);
			assertNotNull("lastUpdatedDate should not be null",session.getLastUpdated());
		}
	}

	@Test
	@Transactional
	public void serviceShouldRetrieveAllActiveSessionsWithMockUpCriteria() throws InterruptedException{
		PublicTransportTrackerService service = context.getBean("transportTrackerServiceWithMockedDao", PublicTransportTrackerService.class);
		Long sessionId1 = service.checkIn("152");
		service.updatePosition(new Location(1, 2), sessionId1);
		Thread.sleep(1100); //a little more than 1 second.
		Long sessionId2 = service.checkIn("68");
		service.updatePosition(new Location(3, 4), sessionId2);
		Long sessionId3 = service.checkIn("314"); 
		service.updatePosition(new Location(5, 6), sessionId3);

		// tranport 152 should be "invalid" , then not returned by the call
		List<TransportSession> sessions = service.retrieveAllSessions();

		assertNotNull(sessions);
		assertEquals(2, sessions.size());
		for (TransportSession session : sessions) {
			assertNotNull(session);
			assertNotNull("lastUpdatedDate should not be null",session.getLastUpdated());
		}
	}

	@Test
	@Transactional
	public void serviceShouldRetrieveAllActiveSessionsWithOrderedLocations() throws InterruptedException{
		Long sessionId1 = service.checkIn("152");
		service.updatePosition(new Location(2, 3), sessionId1);
		service.updatePosition(new Location(4, 5), sessionId1);
		service.updatePosition(new Location(6, 7), sessionId1);

		List<TransportSession> sessions = service.retrieveAllSessions();

		assertNotNull(sessions);
		assertEquals(1, sessions.size());
		TransportSession session = sessions.get(0);
		assertNotNull(session);
		assertEquals(3, session.getLocations().size());
		assertNotNull("lastUpdatedDate should not be null",session.getLastUpdated());
		Location latestLocation = session.getLastKnownLocation();
		System.out.println("LastKnownLocation: "+latestLocation.getTrackedOn().getTime());
		for (Location location : session.getLocations()) {
			System.out.println("Location: "+location.getTrackedOn().getTime());
			assertEquals(true, latestLocation.getTrackedOn().getTime()>=location.getTrackedOn().getTime());
		}
	}


}
