package com.uade.pfi.web.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.service.TransportCrudService;

/**
 * Transport crud controller
 * 
 */
@Controller
public class TransportCrudController {

	@Autowired
	private TransportCrudService service;

	@RequestMapping("transportCrudRequestForm.htm")
	public ModelAndView showEmptyForm() {
		return new ModelAndView("transportCrudRequestForm");
	}

	@RequestMapping(value="addTransport.htm", method=RequestMethod.POST)
	public ModelAndView addContact(@ModelAttribute("transport") Transport transport) {
		service.add(transport);
		return showTransportList();
	}
	
	@RequestMapping(value="showTransportList.htm")
	public ModelAndView showTransportList() {
		List<Transport> transportList = service.getAllTransports();
		return new ModelAndView("showTransportList", "transportList", transportList);
	}
	
	@RequestMapping(value="showTransportListFor.htm", method=RequestMethod.GET)
	public ModelAndView showTransportListFor(@ModelAttribute("lineName") String name) {
		List<Transport> transportList = service.getTransportsByName(name);
		return new ModelAndView("showTransportList", "transportList", transportList);
	}
}
