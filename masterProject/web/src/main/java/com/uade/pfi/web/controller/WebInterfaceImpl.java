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

import com.uade.pfi.api.dto.TransportLocationDTO;
import com.uade.pfi.api.dto.TransportLocationListDTO;
import com.uade.pfi.api.utils.MapsHelper;
import com.uade.pfi.core.beans.TransportSession;
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
		TransportLocationListDTO dto = new TransportLocationListDTO();
		logger.debug("using service: " + service);
		List<TransportSession> sessions = service.retrieveAllSessions();
		List<TransportLocationDTO> locationsDTOList = converter.convert(sessions);
		
		dto.setTransports(locationsDTOList.toArray(new TransportLocationDTO[0]));
		dto.setCenter(MapsHelper.getCenter(locationsDTOList));
		
		if(locationsDTOList.size()>0){
			dto.setZoom(15);
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("locationsList", dto);
		
		return new ModelAndView("map",parameters);
	}

}
