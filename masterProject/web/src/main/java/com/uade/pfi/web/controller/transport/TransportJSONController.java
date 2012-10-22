package com.uade.pfi.web.controller.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uade.pfi.api.dto.TransportDTO;
import com.uade.pfi.api.dto.TransportListDTO;
import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.mapper.Converter;
import com.uade.pfi.core.mapper.TransportToTransportDTOConverter;
import com.uade.pfi.core.service.TransportCrudService;

@Controller
@RequestMapping("/transports")
public class TransportJSONController {
	@Autowired
	private TransportCrudService service;
	private Converter<Transport, TransportDTO> converter = new TransportToTransportDTOConverter();
	
	@RequestMapping(value="getAll.json")
	public @ResponseBody TransportListDTO getAllJson() {
		List<Transport> allTransports = service.getAllTransports();
		TransportListDTO list = new TransportListDTO();
		TransportDTO[] dtos = new TransportDTO[allTransports.size()];
		int i = 0;
		for (Transport t : allTransports) {
			dtos[i++]=converter.convert(t);
		}
		list.setList(dtos);
		return list;
	}
	
	@RequestMapping(value="delete/{id}.json", method=RequestMethod.POST)
	public @ResponseBody String deleteTransportJson(@PathVariable String id) {
		Transport t = new Transport();
		t.setId(id);
		service.remove(t);
		return id;
	}
}
