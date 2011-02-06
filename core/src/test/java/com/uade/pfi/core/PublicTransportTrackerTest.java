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
		List<Location> locations=service.retrieveLocations();
		
		assertNotNull(locations);
	}
	
}
