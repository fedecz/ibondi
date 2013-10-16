package org.ibondi.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibondi.api.dto.LocationDTO;
import org.ibondi.api.dto.SessionCheckInDTO;
import org.ibondi.api.dto.SessionUpdateDto;
import org.ibondi.api.dto.TransportDTO;
import org.ibondi.api.dto.TransportListDTO;
import org.ibondi.api.dto.TransportLocationDTO;
import org.ibondi.api.dto.TransportLocationListDTO;
import org.ibondi.api.dto.TransportTypeListDTO;
import org.ibondi.api.enums.TransportTypeEnum;
import org.ibondi.api.facade.MobileInterface;
import org.ibondi.core.aop.TimeWatched;
import org.ibondi.core.beans.Location;
import org.ibondi.core.beans.Transport;
import org.ibondi.core.beans.TransportSession;
import org.ibondi.core.mapper.TransportSessionToTransportLocationDTOConverter;
import org.ibondi.core.mapper.TransportToTransportDTOConverter;
import org.ibondi.core.service.PublicTransportTrackerService;
import org.ibondi.core.service.TransportCrudService;
import org.ibondi.core.utils.TransportMeStringCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody TransportLocationListDTO getLocations(@RequestBody LocationDTO myLocation) {
		Location l = new Location(myLocation.getLatitude(), myLocation.getLongitude());
		List<TransportSession> sessions = trackerService.retrieveSessions(l);
		List<TransportLocationDTO> locations = transportSessionConverter.convert(sessions);
		TransportLocationListDTO list = new TransportLocationListDTO();
		list.setTransports(locations.toArray(new TransportLocationDTO[0]));
		list.setCenter(myLocation);
		list.setZoom(10);
		return list;
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
	
	@TimeWatched
	@RequestMapping(value="/transportListBy.json")
	public @ResponseBody TransportListDTO getTransportListBy(@RequestBody String transportType) {
		List<Transport> transports = crudService.getTransportsByType(transportType);
		List<TransportDTO> transportDTOs = transportToDTOConverter.createTransportDTOListFrom(transports);
		return new TransportListDTO(transportDTOs);
	}
	
	@RequestMapping(value="/transportTypeList.json")
	public @ResponseBody TransportTypeListDTO getTransportTypeList(@RequestBody String locale) {
		List<String> transportTypes = TransportTypeEnum.getTranportTypeEnumAsStringList();
		return new TransportTypeListDTO(transportTypes);
	}
	
}
