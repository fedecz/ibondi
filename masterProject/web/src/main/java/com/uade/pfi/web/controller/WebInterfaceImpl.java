package com.uade.pfi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dto.TransportLocationDTO;
import com.uade.pfi.core.mapper.TransportSessionToTransportLocationDTOConverter;
import com.uade.pfi.core.service.PublicTransportTrackerService;


@Controller
public class WebInterfaceImpl {
	private Log logger = LogFactory.getLog(WebInterfaceImpl.class);
	
	@Autowired
	private PublicTransportTrackerService service;
	private TransportSessionToTransportLocationDTOConverter converter = new TransportSessionToTransportLocationDTOConverter();


	@RequestMapping("index.htm")
	public ModelAndView index(){
		logger.debug("using service: " + service);
		List<TransportSession> sessions = service.retrieveAllSessions();
		List<TransportLocationDTO> locations = converter.convert(sessions);
		logger.debug("index() accessed. Returned items: " + locations.size());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("locations", locations);
		
		Integer mapZoom = 2;
		Float centerLat = (float) 0;
		Float centerLong = (float) 0;
		if(locations.size()>0){
			mapZoom = 15;
			centerLat =  locations.get(0).getLatitude();
			centerLong = locations.get(0).getLongitude();
		}
		
		parameters.put("mapZoom", mapZoom);
		parameters.put("centerLat", centerLat);
		parameters.put("centerLong", centerLong);
		
		return new ModelAndView("map",parameters);
	}

}
