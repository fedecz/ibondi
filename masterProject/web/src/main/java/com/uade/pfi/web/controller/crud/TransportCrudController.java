package com.uade.pfi.web.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/transports")
public class TransportCrudController {

	@Autowired
	private TransportCrudService service;

	@RequestMapping("transportCrudRequestForm.html")
	public ModelAndView showEmptyForm() {
		return new ModelAndView("transportCrudRequestForm");
	}

	@RequestMapping(value="addTransport.html", method=RequestMethod.POST)
	public ModelAndView addContact(@ModelAttribute("transport") Transport transport) {
		service.add(transport);
		return showTransportList();
	}
	
	@RequestMapping(value="show.html")
	public ModelAndView showTransportList() {
		List<Transport> transportList = service.getAllTransports();
		return new ModelAndView("transports/showAll", "transportList", transportList);
	}
	
	@RequestMapping(value="show/{id}.html", method=RequestMethod.GET)
	public ModelAndView showTransportFor(@PathVariable String id) {
		Transport t = new Transport();
		t.setId(id);
		return new ModelAndView("transports/showTransport","transport",service.get(t));
	}
	
	@RequestMapping(value="delete/{id}.html", method=RequestMethod.GET)
	public ModelAndView deleteTransport(@PathVariable String id) {
		Transport t = new Transport();
		t.setId(id);
		service.remove(t);
		return showTransportList();
	}
}
