package com.uade.pfi.core;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

import com.uade.pfi.core.service.PublicTransportTrackerService;
import com.uade.pfi.core.service.PublicTransportTrackerServiceImpl;


public class PublicTransportTrackerTest {

	PublicTransportTrackerService service;

	@Before
	public void setup(){
		service = new PublicTransportTrackerServiceImpl();
	}

	@Test
	public void elServicioDebeDevolverListasPosicionesDeLosTransportes(){
		List<TransportLocation> locations=service.retrieveLocations();

		assertNotNull(locations);
	}

	@Test
	public void elServicioDebeDejarUpdetearMiPosicionEnElMedioDeTransporte(){
		TransportLocation location = new TransportLocation(2,3,"60");

		service.updatePosition(location);
	}

	@Test
	public void elServicioDebeGuardarLaPosicionYVerlaEnElMapa(){
		List<TransportLocation> locations=service.retrieveLocations();
		assertNotNull(locations);
		assertEquals(0, locations.size());
		
		TransportLocation location = new TransportLocation(2,3,"60");
		service.updatePosition(location);
		locations=service.retrieveLocations();
		
		assertEquals(1, locations.size());
		TransportLocation newLocation = locations.get(0);
		assertEquals(2, newLocation.getLocation().getLatitude());
		assertEquals(3, newLocation.getLocation().getLongitude());
		assertEquals("60", newLocation.getName());
	}
	
	@Test
	public void elServicioTieneQueGenerarUnPromedioDeLasPosicionesAnteElMismoTransporte(){
		TransportLocation location1 = new TransportLocation(2,3,"60");
		TransportLocation location2 = new TransportLocation(4,6,"60");
		TransportLocation location3 = new TransportLocation(1,2,"59");
		
		service.updatePosition(location1);
		service.updatePosition(location2);
		service.updatePosition(location3);
		List<TransportLocation> locations = service.retrieveLocations();
		
		assertNotNull(locations);
		assertEquals(2, locations.size());
	}





}
