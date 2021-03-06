package org.ibondi.core.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.util.List;

import org.ibondi.core.beans.Location;
import org.ibondi.core.beans.TransportSession;
import org.ibondi.core.service.PublicTransportTrackerService;
import org.ibondi.core.service.PublicTransportTrackerServiceImpl;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PublicTransportTrackerTest {


	@Test
	@Ignore
	public void elServicioDebeDevolverListasPosicionesDeTODOSLosTransportes(){
		PublicTransportTrackerServiceImpl service = new PublicTransportTrackerServiceImpl();
//		service.setSessionsDao(new SessionDao() {
//			public String insert(TransportSession session) {return null;}
//			public List<TransportSession> retrieveActiveSessions() {
//				TransportSession loc = new TransportSession();
//				ArrayList<TransportSession> list = new ArrayList<TransportSession>();
//				list.add(loc);
//				return list;
//			}
//			public void save(TransportSession session) {}
//			public TransportSession get(String sessionId) {
//				return null;
//			}
//			public void setLatestLocationTo(String sessionId, Location location) {}
//			public void addLocationToList(String sessionId, Location location) {}
//			public void updateTime(String sessionId) {}
//		});
		List<TransportSession> sessions=service.retrieveAllSessions();

		assertNotNull(sessions);
		assertEquals(1, sessions.size());
	}

	@Test
	public void elServicioDebeDejarUpdetearMiPosicionEnElMedioDeTransporte(){
		PublicTransportTrackerService service = createService();
		String sessionId = service.checkIn("60");
		Location location = new Location(2,3);

		service.updatePosition(location,sessionId);
	}

	@Test
	@Ignore("hasta que se resuelva el 'lastKnownLocation' de session")
	public void elServicioDebeGuardarLaPosicionYVerlaEnElMapa(){
		PublicTransportTrackerService service = createService();
		List<TransportSession> sessions=service.retrieveAllSessions();
		assertNotNull(sessions);
		assertEquals(0, sessions.size());

		String sessionid1 = service.checkIn("60");
		service.updatePosition(new Location(2,3),sessionid1);
		sessions=service.retrieveAllSessions();

		assertEquals(1, sessions.size());
		TransportSession newSession = sessions.get(sessions.size()-1);
		//		assertEquals(new Float(2), newSession.getLocation().getLatitude());
		//		assertEquals(new Float(3), newSession.getLocation().getLongitude());
		assertEquals("60", newSession.getTransportId());
	}

	@Test
	@Ignore(value="Por ahora lo hacemos simple.")
	public void elServicioTieneQueGenerarUnPromedioDeLasPosicionesAnteElMismoTransporte(){
		//TODO completar.
	}

	@Test(expected=IllegalArgumentException.class)
	public void lasCoordenadasNoPuedenEstarIncompletas(){
		PublicTransportTrackerService service = createService();

		service.updatePosition(new Location(),"x");

		fail();
	}


	@Test
	@Ignore
	public void serviceShouldAllowCheckIn(){
		PublicTransportTrackerServiceImpl service = createService();
//		service.setSessionsDao(new SessionDao() {
//			public String insert(TransportSession session) {
//				return "x";
//			}
//			public List<TransportSession> retrieveActiveSessions() {
//				return null;
//			}
//			public void save(TransportSession session) {}
//			public TransportSession get(String sessionId) {
//				return null;
//			}
//			public void setLatestLocationTo(String sessionId, Location location) {}
//			public void addLocationToList(String sessionId, Location location) {}
//			public void updateTime(String sessionId) {}
//		});
		
		String sessionId = service.checkIn("152");

		assertNotNull(sessionId);
		assertEquals("x", sessionId);
	}


	@Test(expected=IllegalArgumentException.class)
	public void serviceShouldNotAllowEmptyCheckIn(){
		PublicTransportTrackerServiceImpl service = createService();
		
		service.checkIn("");

		fail();
	}

	@Test(expected=IllegalArgumentException.class)
	public void serviceShouldNotAllowNullCheckIn(){
		PublicTransportTrackerServiceImpl service = createService();
		
		service.checkIn(null);

		fail();
	}
	
	
	private PublicTransportTrackerServiceImpl createService() {
		PublicTransportTrackerServiceImpl service = new PublicTransportTrackerServiceImpl();
//		service.setSessionsDao(new SessionsDaoMock());
		return service;
	}


}
