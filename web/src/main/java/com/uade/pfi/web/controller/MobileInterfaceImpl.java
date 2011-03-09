package com.uade.pfi.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uade.pfi.core.dto.TransportLocation;
import com.uade.pfi.core.service.PublicTransportTrackerService;
import com.uade.pfi.core.utils.TransportLocationStringCreator;

@Controller
public class MobileInterfaceImpl implements MobileInterface {
	private Logger logger = LoggerFactory.getLogger(MobileInterfaceImpl.class);
	@Autowired
	private PublicTransportTrackerService service;

	/* (non-Javadoc)
	 * @see com.uade.pfi.web.controller.MobileInterface#count()
	 */
	@RequestMapping(value="/location/get.json")
	public @ResponseBody Integer count(){
		int size = service.retrieveLocations().size();
		logger.debug("count() accessed. Returned: " + size);
		return size;
	}
	
	/* (non-Javadoc)
	 * @see com.uade.pfi.web.controller.MobileInterface#list()
	 */
	@RequestMapping(value="/location/getList.json")
	public @ResponseBody TransportLocation[] list(){
		TransportLocation[] locations = service.retrieveLocations().toArray(new TransportLocation[0]);
		logger.debug("list() accessed. Returned items: " + locations.length);
		return locations;
	}
	
	/* (non-Javadoc)
	 * @see com.uade.pfi.web.controller.MobileInterface#postLocation(com.uade.pfi.core.dto.TransportLocation)
	 */
	@RequestMapping("/location/post.json")
	public @ResponseBody Boolean postLocation(@RequestBody TransportLocation location){
		logger.debug("Post() accessed. " + TransportLocationStringCreator.convert(location));
		service.updatePosition(location);
		return true;
	}
	
}
