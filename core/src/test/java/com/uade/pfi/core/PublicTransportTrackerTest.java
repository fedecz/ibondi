package com.uade.pfi.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uade.pfi.core.service.PublicTransportTrackerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:core-dao.xml","classpath:core-ds.xml","classpath:core-service.xml"})
@Transactional
public class PublicTransportTrackerTest {

	@Autowired
	PublicTransportTrackerService service;


	@Test
	public void elServicioDebeDevolverListasPosicionesDeLosTransportes(){
		List<TransportLocation> locations=service.retrieveLocations();

		assertNotNull(locations);
	}

	@Test
	public void elServicioDebeDejarUpdetearMiPosicionEnElMedioDeTransporte(){
		TransportLocation location = new TransportLocation(new Float(2),new Float(3),"60");

		service.updatePosition(location);
	}

	@Test
	public void elServicioDebeGuardarLaPosicionYVerlaEnElMapa(){
		List<TransportLocation> locations=service.retrieveLocations();
		assertNotNull(locations);
		assertEquals(2, locations.size());
		
		TransportLocation location = new TransportLocation(new Float(2),new Float(3),"60");
		service.updatePosition(location);
		locations=service.retrieveLocations();
		
		assertEquals(3, locations.size());
		TransportLocation newLocation = locations.get(locations.size()-1);
		assertEquals(new Float(2), newLocation.getLocation().getLatitude());
		assertEquals(new Float(3), newLocation.getLocation().getLongitude());
		assertEquals("60", newLocation.getName());
	}
	
	@Test
	@Ignore(value="Por ahora lo hacemos simple.")
	public void elServicioTieneQueGenerarUnPromedioDeLasPosicionesAnteElMismoTransporte(){
		TransportLocation location1 = new TransportLocation(new Float(2),new Float(3),"60");
		TransportLocation location2 = new TransportLocation(new Float(4),new Float(6),"60");
		TransportLocation location3 = new TransportLocation(new Float(1),new Float(2),"59");
		
		service.updatePosition(location1);
		service.updatePosition(location2);
		service.updatePosition(location3);
		List<TransportLocation> locations = service.retrieveLocations();
		
		assertNotNull(locations);
		assertEquals(2, locations.size());
		for (TransportLocation location : locations) {
			if(location.getName().equals("60")){
				assertEquals(new Float(3), location.getLocation().getLatitude());
				assertEquals(new Float(4.5), location.getLocation().getLongitude());
			}
			if(location.getName().equals("59")){
				assertEquals(new Float(1), location.getLocation().getLatitude());
				assertEquals(new Float(2), location.getLocation().getLongitude());
			}
		}
	}
	
	@Test
	public void elNombreDelTransporteNoPuedeSerNuloOVacio(){
		TransportLocation location1 = new TransportLocation(new Float(2),new Float(3),null);
		TransportLocation location2 = new TransportLocation(new Float(2),new Float(3),"");
		
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
		TransportLocation location1 = new TransportLocation(new Float(2),null,"59");
		TransportLocation location2 = new TransportLocation(null,new Float(3),"60");
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
