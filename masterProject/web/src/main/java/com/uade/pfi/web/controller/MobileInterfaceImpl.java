package com.uade.pfi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dto.LocationDTO;
import com.uade.pfi.core.dto.TransportLocationDTO;
import com.uade.pfi.core.facade.MobileInterface;
import com.uade.pfi.core.service.PublicTransportTrackerService;
import com.uade.pfi.core.utils.TransportMeStringCreator;

@Controller
public class MobileInterfaceImpl implements MobileInterface {
	
	private Log logger = LogFactory.getLog(MobileInterfaceImpl.class);
	
	@Autowired
	private PublicTransportTrackerService service;

	
	@RequestMapping(value="/getAllLocations.json")
	@Transactional
	public @ResponseBody TransportLocationDTO[] getAllLocations(){
		logger.debug("[getAllLocations()] using service: " + service);
		List<TransportSession> sessions = service.retrieveAllSessions();
		List<TransportLocationDTO> locations = new ArrayList<TransportLocationDTO>();
		for (TransportSession session : sessions) {
			TransportLocationDTO dto = new TransportLocationDTO(session.getName(), session.getLastKnownLocation().getLatitude(), session.getLastKnownLocation().getLongitude());
			locations.add(dto);
		}
		logger.debug("getAllLocations() finished. Returned items: " + locations.size());
		return locations.toArray(new TransportLocationDTO[0]);
	}
	
	@RequestMapping(value="/getLocations.json")
	@Transactional
	public TransportLocationDTO[] getLocations(@RequestBody LocationDTO myLocation) {
		throw new RuntimeException("To be Implemented");
	}
	
	@RequestMapping("/postLocation.json")
	@Transactional
	public @ResponseBody Boolean postLocation(@RequestBody TransportLocationDTO location){
		logger.debug("[postLocation()] received TransportLocationDTO: " + TransportMeStringCreator.toString(location));
		service.updatePosition(new Location(location.getLatitude(),location.getLongitude()),location.getSession());
		logger.debug("position updated to session: " + location.getSession());
		return true;
	}


	@RequestMapping(value="/checkIn.json")
	@Transactional
	public @ResponseBody String checkIn(@RequestBody String transportName) {
		logger.debug("[checkIn()] received transportName: " + transportName);
		String id = service.checkIn(transportName);
		logger.debug("returning sessionId: " + id);
		return id;
	}
	
}
