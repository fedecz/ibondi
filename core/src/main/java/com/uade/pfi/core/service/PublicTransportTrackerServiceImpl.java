package com.uade.pfi.core.service;

import java.util.List;

import com.uade.pfi.core.TransportLocation;
import com.uade.pfi.core.dao.LocationDao;

public class PublicTransportTrackerServiceImpl implements
		PublicTransportTrackerService {
	
	private LocationDao dao;
	
	public List<TransportLocation> retrieveLocations() {
		return dao.retrieveLocations();
	}

	public void updatePosition(TransportLocation location) {
		validateTransportLocation(location);
//		this.mergeOrInsertPosition(location);
		dao.storeLocation(location);
	}

	private void validateTransportLocation(TransportLocation location) {
		if(location==null)
			throw new IllegalArgumentException("TransportLocation cannot be null");
		if(location.getName()==null || location.getName().trim().length()==0)
			throw new IllegalArgumentException("TransportLocation cannot be empty");
		validateLocation(location);
	}

	private void validateLocation(TransportLocation location) {
		if(location.getLocation()==null)
			throw new IllegalArgumentException("Location cannot be null");
		if(location.getLocation().getLatitude()==null || location.getLocation().getLongitude()==null)
			throw new IllegalArgumentException("Latitude or longitude cannot be null");
	}

	
	/**
	 * Hay que implementar "haversine".
	 * http://www.movable-type.co.uk/scripts/latlong.html
	 * http://www.codecodex.com/wiki/Calculate_Distance_Between_Two_Points_on_a_Globe
	 * 
	 * @param aLocation
	 */
//	private void mergeOrInsertPosition(TransportLocation aLocation) {
//		TransportLocation oldLocation = locations.get(aLocation.getName());
//		if(oldLocation!=null){
//			Location newLocation = new Location();
//			newLocation.setLatitude((oldLocation.getLocation().getLatitude() + aLocation.getLocation().getLatitude()) / 2);
//			newLocation.setLongitude((oldLocation.getLocation().getLongitude() + aLocation.getLocation().getLongitude()) / 2);
//			oldLocation.setLocation(newLocation);
//			return;	
//		}
//		locations.put(aLocation.getName(), aLocation);
//	}
	
	public void setDao(LocationDao dao) {
		this.dao = dao;
	}
	public LocationDao getDao() {
		return dao;
	}


}
