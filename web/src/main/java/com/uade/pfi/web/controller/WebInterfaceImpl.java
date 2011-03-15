package com.uade.pfi.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dto.TransportLocationDTO;
import com.uade.pfi.core.service.PublicTransportTrackerService;


@Controller
public class WebInterfaceImpl {
	private Logger logger = LoggerFactory.getLogger(WebInterfaceImpl.class);
	
	@Resource
	private PublicTransportTrackerService service;

	@RequestMapping("index.htm")
	@Transactional
	public ModelAndView index(){
		logger.debug("using service: " + service);
		List<TransportSession> sessions = service.retrieveAllSessions();
		List<TransportLocationDTO> locations = new ArrayList<TransportLocationDTO>();
		for (TransportSession session : sessions) {
			TransportLocationDTO dto = new TransportLocationDTO(session.getName(), session.getLastKnownLocation().getLatitude(), session.getLastKnownLocation().getLongitude());
			locations.add(dto);
		}
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
	
//	@RequestMapping(value="update.htm",method=RequestMethod.GET)
//	public String update(){
//		return "update";
//	}
//	
//	@RequestMapping(value="update.htm",method=RequestMethod.POST)
//	public ModelAndView processUpdate(String name, Float latitude, Float longitude){
//		
//		service.updatePosition(new Location(latitude,longitude),name);
//		return new ModelAndView("update","msg","Location sent");
//	}
}
