package com.uade.pfi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uade.pfi.core.Location;
import com.uade.pfi.core.TransportLocation;
import com.uade.pfi.core.service.PublicTransportTrackerService;


@Controller
public class Map {
	
	@Autowired
	private PublicTransportTrackerService service;

	@RequestMapping("index.htm")
	public ModelAndView index(){
		List<TransportLocation> locations = service.retrieveLocations();
		return new ModelAndView("map","locations",locations);
	}
	
	@RequestMapping(value="update.htm",method=RequestMethod.GET)
	public ModelAndView update(){
		TransportLocation location = new TransportLocation();
		location.setLocation(new Location());
		return new ModelAndView("update","loc",location);
	}
	
	@RequestMapping(value="update.htm",method=RequestMethod.POST)
	public String processUpdate(TransportLocation location){
		service.updatePosition(location);
		return "update";
	}
}
