package com.uade.pfi.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uade.pfi.core.dao.LocationDao;
import com.uade.pfi.core.dto.TransportLocation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class LocationDaoImplTester {
	
	@Autowired
	LocationDao dao;
	
	
	
	@Test
	public void daoDebePoderDevolverLosLocations(){
		
		List<TransportLocation> locations = dao.retrieveLocations();
		
		assertNotNull(locations);
		assertEquals(2, locations.size());
		TransportLocation transportLocation = locations.get(0);
		assertNotNull(transportLocation);
		assertEquals("60", transportLocation.getName());
		assertEquals((float)-34.49023889, transportLocation.getLocation().getLatitude());
		assertEquals((float)-58.50026111, transportLocation.getLocation().getLongitude());
		TransportLocation transportLocation2 = locations.get(1);
		assertNotNull(transportLocation2);
		assertEquals("59", transportLocation2.getName());
		assertEquals((float)-34.48916944, transportLocation2.getLocation().getLatitude());
		assertEquals((float)-58.50085, transportLocation2.getLocation().getLongitude());
	}

	@Test
	public void daoDebePoderGuardarLosLocations(){
		TransportLocation location = new TransportLocation((float)2, (float)3, "42");
		
		dao.storeLocation(location);
		
		List<TransportLocation> locations = dao.retrieveLocations();
		assertNotNull(locations);
		TransportLocation transportLocation = locations.get(locations.size()-1);
		assertNotNull(transportLocation);
		assertEquals((float)2, transportLocation.getLocation().getLatitude());
		assertEquals((float)3, transportLocation.getLocation().getLongitude());
		assertEquals("42", transportLocation.getName());
	}
}
