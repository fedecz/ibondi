package com.uade.pfi.web.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.service.TransportCrudService;

/**
 * CRUD Controller for transports 
 * 
 * coordinates example: -34.582062,-58.436648
 * 
 */
@Controller
@SessionAttributes
public class TransportCrudController {

	@Autowired
	private TransportCrudService service;

	@RequestMapping("/transport/transportCrudRequestForm.htm")
	public ModelAndView showEmptyForm() {
		return new ModelAndView("transportCrudRequestForm");
	}

	@RequestMapping(value="/transport/addTransport.htm", method=RequestMethod.POST)
	public ModelAndView addContact(@ModelAttribute("transport") Transport transport) {
		Transport result = service.add(transport);
		return showTransportList();
	}

	@RequestMapping(value="/transport/showTransportList.htm")
	public ModelAndView showTransportList() {
		List<Transport> transportList = service.getAllTransports();
		return new ModelAndView("showTransportList", "transportList", transportList);
	}
}
