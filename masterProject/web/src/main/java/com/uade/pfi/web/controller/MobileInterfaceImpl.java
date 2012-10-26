package com.uade.pfi.web.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uade.pfi.api.dto.LocationDTO;
import com.uade.pfi.api.dto.SessionCheckInDTO;
import com.uade.pfi.api.dto.SessionUpdateDto;
import com.uade.pfi.api.dto.TransportDTO;
import com.uade.pfi.api.dto.TransportLocationDTO;
import com.uade.pfi.api.dto.TransportLocationListDTO;
import com.uade.pfi.api.facade.MobileInterface;
import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.mapper.TransportSessionToTransportLocationDTOConverter;
import com.uade.pfi.core.mapper.TransportToTransportDTOConverter;
import com.uade.pfi.core.service.PublicTransportTrackerService;
import com.uade.pfi.core.service.TransportCrudService;
import com.uade.pfi.core.utils.TimeWatch;
import com.uade.pfi.core.utils.TransportMeStringCreator;

@Controller
public class MobileInterfaceImpl implements MobileInterface {
	
	private Log logger = LogFactory.getLog(MobileInterfaceImpl.class);
	
	@Autowired
	private PublicTransportTrackerService trackerService;
	
	@Autowired
	private TransportCrudService crudService;
	
	@Autowired
	private TransportSessionToTransportLocationDTOConverter transportSessionConverter = new TransportSessionToTransportLocationDTOConverter();

	@Autowired
	private TransportToTransportDTOConverter transportToDTOConverter;
	
	@RequestMapping(value="/getAllLocations.json")
	public @ResponseBody TransportLocationListDTO getAllLocations(){
		logger.debug("[getAllLocations()] using service: " + trackerService);
		List<TransportSession> sessions = trackerService.retrieveAllSessions();
		List<TransportLocationDTO> locations = transportSessionConverter.convert(sessions);
		logger.debug("getAllLocations() finished. Returned items: " + locations.size());
		TransportLocationListDTO list = new TransportLocationListDTO();
		list.setTransports(locations.toArray(new TransportLocationDTO[0]));
		return list;
	}
	
	@RequestMapping(value="/getLocations.json")
	public TransportLocationListDTO getLocations(@RequestBody LocationDTO myLocation) {
		throw new RuntimeException("To be Implemented");
	}
	
	@RequestMapping("/postLocation.json")
	public @ResponseBody Boolean postLocation(@RequestBody SessionUpdateDto location){
		logger.debug("[postLocation()] received TransportLocationDTO: " + TransportMeStringCreator.toString(location));
		trackerService.updatePosition(new Location(location.getLatitude(),location.getLongitude()),location.getSessionId());
		logger.debug("position updated to session: " + location.getSessionId());
		return true;
	}


	@RequestMapping(value="/checkIn.json")
	public @ResponseBody String checkIn(@RequestBody SessionCheckInDTO sessionCheckin) {
		logger.debug("[checkIn()] received transportName: " + sessionCheckin.getTransportId());
		String id = trackerService.checkIn(sessionCheckin.getTransportId());
		logger.debug("returning sessionId: " + id);
		return id;
	}
	
	@RequestMapping(value="/transportListBy.json")
	public @ResponseBody List<TransportDTO> getTransportListBy(@RequestBody String transportType) {
		logger.debug("[getListOfLinesFrom(" + transportType + ")]" );
		TimeWatch watch = TimeWatch.start();
		List<Transport> transports = crudService.getTransportsByType(transportType);
		List<TransportDTO> transportDTOs = transportToDTOConverter.createTransportDTOListFrom(transports);
		logger.debug("Transports found: " + transports.size() + " in (" + watch.stop().getTimeElapsed(TimeUnit.MILLISECONDS) + " ms.)");
		return transportDTOs;
	}
	
}
