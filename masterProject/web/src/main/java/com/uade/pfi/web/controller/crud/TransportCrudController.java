package com.uade.pfi.web.controller.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.enums.HeadingEnum;
import com.uade.pfi.core.enums.TransportTypeEnum;
import com.uade.pfi.core.service.TransportCrudService;

/**
 * CRUD Controller for transports 
 * 
 * -34.582062,-58.436648
 * 
 */
@Controller
public class TransportCrudController {
	
	@Autowired
	private TransportCrudService service;
	
	@RequestMapping("transportCrudRequestForm.htm")
	public String showForm() {
		return "transportCrudRequestForm";
	}
	
	@RequestMapping("transportCrudRequest.htm")
	public ModelAndView getTransportList(Model model) {
		Transport transport = createTransport(model);
		service.add(transport);
		List<Transport> transports = service.getAllTransports();
		
		Map<String, List<Transport>> responseModel = new HashMap<String, List<Transport>>();
		responseModel.put("transports", transports);
		return new ModelAndView("showTransportList", responseModel);
	}

	private Transport createTransport(Model model) {
		Map<String, Object> transportCrudRequestMap = model.asMap();
		String name = (String) transportCrudRequestMap.get("name");
		String branch = (String) transportCrudRequestMap.get("branch");
		HeadingEnum heading = createHeading((String) transportCrudRequestMap.get("heading"));
		TransportTypeEnum transportType  = createTransportType((String) transportCrudRequestMap.get("type"));
		return new Transport(name, branch, heading, transportType);
	}

	private HeadingEnum createHeading(String headingAsString) {
		return HeadingEnum.getHeadingFrom(headingAsString);
	}
	
	private TransportTypeEnum createTransportType(String transportTypeAsString) {
		return TransportTypeEnum.createTransportType(transportTypeAsString);
	}
	

	
}
