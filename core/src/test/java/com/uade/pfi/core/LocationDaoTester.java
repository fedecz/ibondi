package com.uade.pfi.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.uade.pfi.core.dao.LocationDao;

import static junit.framework.Assert.*;

public class LocationDaoTester {

	
	private LocationDao dao;
	
	@Before
	public void setup(){
		dao = new LocationDaoMock();
	}
	
	
	@Test
	public void daoDebePoderGuardarLosLocations(){
		TransportLocation location = new TransportLocation((float)2, (float)3, "42");
		TransportLocation location2 = new TransportLocation((float)2, (float)3, "43");

		dao.storeLocation(location);
		dao.storeLocation(location2);
		
		List<TransportLocation> locations = ((LocationDaoMock) dao).getLocations();
		assertNotNull(locations);
		TransportLocation transportLocation = locations.get(0);
		assertNotNull(transportLocation);
		assertEquals((float)2, transportLocation.getLocation().getLatitude());
		assertEquals((float)3, transportLocation.getLocation().getLongitude());
		assertEquals("42", transportLocation.getName());
	}
	
	
	@Test
	public void daoDebePoderDevolverLosLocations(){
		TransportLocation location = new TransportLocation((float)2, (float)3, "42");
		TransportLocation location2 = new TransportLocation((float)2, (float)3, "43");
		dao.storeLocation(location);
		dao.storeLocation(location2);
		
		List<TransportLocation> locations = dao.retrieveLocations();
		
		assertNotNull(locations);
		assertEquals(2, locations.size());
		TransportLocation transportLocation = locations.get(0);
		assertNotNull(transportLocation);
		assertEquals((float)2, transportLocation.getLocation().getLatitude());
		assertEquals((float)3, transportLocation.getLocation().getLongitude());
		assertEquals("42", transportLocation.getName());
		TransportLocation transportLocation2 = locations.get(1);
		assertNotNull(transportLocation2);
		assertEquals((float)2, transportLocation2.getLocation().getLatitude());
		assertEquals((float)3, transportLocation2.getLocation().getLongitude());
		assertEquals("43", transportLocation2.getName());
	}
	
	
	
	
	class LocationDaoMock implements LocationDao{
		
		List<TransportLocation> locations = new ArrayList<TransportLocation>();

		public void storeLocation(TransportLocation location) {
			locations.add(location);
		}
		
		public List<TransportLocation> getLocations() {
			return locations;
		}

		public List<TransportLocation> retrieveLocations() {
			return getLocations();
		}
		
	}
}
