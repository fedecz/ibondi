package com.uade.pfi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uade.pfi.core.dto.TransportLocation;
import com.uade.pfi.core.service.PublicTransportTrackerService;

@Controller
public class MobileInterface {
	@Autowired
	private PublicTransportTrackerService service;

	@RequestMapping(value="/location/get.json")
	public @ResponseBody Integer count(){
		System.out.println("get accessed");
		return service.retrieveLocations().size();
	}
	
	@RequestMapping(value="/location/getList.json")
	public @ResponseBody TransportLocation[] list(){
		System.out.println("get accessed");
		return service.retrieveLocations().toArray(new TransportLocation[0]);
	}
	
	@RequestMapping("/location/post.json")
	public @ResponseBody Boolean postLocation(@RequestBody TransportLocation location){
		System.out.println("post accessed");
		service.updatePosition(location);
		return true;
	}
	
}
