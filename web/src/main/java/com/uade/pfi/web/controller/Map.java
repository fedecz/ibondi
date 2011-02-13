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
	public String update(){
		return "update";
	}
	
	@RequestMapping(value="update.htm",method=RequestMethod.POST)
	public ModelAndView processUpdate(String name, String latitude, String longitude){
		TransportLocation location = new TransportLocation(Float.parseFloat(latitude), Float.parseFloat(longitude), name);
		service.updatePosition(location);
		return new ModelAndView("update","msg","Location sent");
	}
}
