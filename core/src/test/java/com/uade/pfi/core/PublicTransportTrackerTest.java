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
		assertEquals(new Integer(2), newLocation.getLocation().getLatitude());
		assertEquals(new Integer(3), newLocation.getLocation().getLongitude());
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
		for (TransportLocation location : locations) {
			if(location.getName().equals("60")){
				assertEquals(new Integer(3), location.getLocation().getLatitude());
				assertEquals(new Integer(4), location.getLocation().getLongitude());
			}
			if(location.getName().equals("59")){
				assertEquals(new Integer(1), location.getLocation().getLatitude());
				assertEquals(new Integer(2), location.getLocation().getLongitude());
			}
		}
	}
	
	@Test
	public void elNombreDelTransporteNoPuedeSerNuloOVacio(){
		TransportLocation location1 = new TransportLocation(2,3,null);
		TransportLocation location2 = new TransportLocation(2,3,"");
		
		try {
			service.updatePosition(location1);
			fail();
		} catch (Exception e) {
		}
		try {
			service.updatePosition(location2);
			fail();
		} catch (Exception e) {
		}
		
	}
	
	@Test
	public void lasCoordenadasDelTransporteNoPuedeSerNulas(){
		TransportLocation location1 = new TransportLocation(2,null,"59");
		TransportLocation location2 = new TransportLocation(null,3,"60");
		TransportLocation location3 = new TransportLocation(null,null,"152");
		
		try {
			service.updatePosition(location1);
			fail();
		} catch (Exception e) {
		}
		try {
			service.updatePosition(location2);
			fail();
		} catch (Exception e) {
		}
		try {
			service.updatePosition(location3);
			fail();
		} catch (Exception e) {
		}
	}





}
