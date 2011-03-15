package com.uade.pfi.core.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.SessionDao;
import com.uade.pfi.core.service.PublicTransportTrackerService;
import com.uade.pfi.core.service.PublicTransportTrackerServiceImpl;
import com.uade.pfi.core.test.mocks.SessionsDaoMock;

public class PublicTransportTrackerTest {


	@Test
	public void elServicioDebeDevolverListasPosicionesDeTODOSLosTransportes(){
		PublicTransportTrackerServiceImpl service = new PublicTransportTrackerServiceImpl();
		service.setDao(new SessionDao() {
			public Long insert(TransportSession session) {return null;}
			public List<TransportSession> retrieveActiveSessions() {
				TransportSession loc = new TransportSession();
				ArrayList<TransportSession> list = new ArrayList<TransportSession>();
				list.add(loc);
				return list;
			}
			public TransportSession get(Long sessionId) {
				return null;
			}
			public void save(TransportSession session) {}
			public void save(Location location) {}
		});
		List<TransportSession> sessions=service.retrieveAllSessions();

		assertNotNull(sessions);
		assertEquals(1, sessions.size());
	}

	@Test
	public void elServicioDebeDejarUpdetearMiPosicionEnElMedioDeTransporte(){
		PublicTransportTrackerService service = createService();
		Long sessionId = service.checkIn("60");
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

		Long sessionid1 = service.checkIn("60");
		service.updatePosition(new Location(2,3),sessionid1);
		sessions=service.retrieveAllSessions();

		assertEquals(1, sessions.size());
		TransportSession newSession = sessions.get(sessions.size()-1);
		//		assertEquals(new Float(2), newSession.getLocation().getLatitude());
		//		assertEquals(new Float(3), newSession.getLocation().getLongitude());
		assertEquals("60", newSession.getName());
	}

	@Test
	@Ignore(value="Por ahora lo hacemos simple.")
	public void elServicioTieneQueGenerarUnPromedioDeLasPosicionesAnteElMismoTransporte(){
		//TODO completar.
	}

	@Test(expected=IllegalArgumentException.class)
	public void lasCoordenadasNoPuedenEstarIncompletas(){
		PublicTransportTrackerService service = createService();

		service.updatePosition(new Location(),0l);

		fail();
	}


	@Test
	public void serviceShouldAllowCheckIn(){
		PublicTransportTrackerServiceImpl service = createService();
		service.setDao(new SessionDao() {
			public Long insert(TransportSession session) {
				return 0l;
			}
			public List<TransportSession> retrieveActiveSessions() {
				return null;
			}
			public TransportSession get(Long sessionId) {
				return null;
			}
			public void save(TransportSession session) {}
			public void save(Location location) {}
		});
		
		Long sessionId = service.checkIn("152");

		assertNotNull(sessionId);
		assertEquals(new Long(0), sessionId);
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
		service.setDao(new SessionsDaoMock());
		return service;
	}


}
