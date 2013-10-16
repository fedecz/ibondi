package org.ibondi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibondi.api.dto.TransportLocationDTO;
import org.ibondi.api.dto.TransportLocationListDTO;
import org.ibondi.api.utils.MapsHelper;
import org.ibondi.core.beans.TransportSession;
import org.ibondi.core.mapper.TransportSessionToTransportLocationDTOConverter;
import org.ibondi.core.service.PublicTransportTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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
	
	@RequestMapping("path.htm")
	public String showPath(@RequestParam String sessionId, Model model){
		TransportSession session = service.findSession(sessionId);
		model.addAttribute("session", session);
		model.addAttribute("center", session.getLastKnownLocation());
		
		return "path";
	}

}
