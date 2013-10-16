package org.ibondi.web.controller.transport;

import java.util.List;

import org.ibondi.api.dto.TransportDTO;
import org.ibondi.api.dto.TransportListDTO;
import org.ibondi.core.beans.Transport;
import org.ibondi.core.mapper.Converter;
import org.ibondi.core.mapper.TransportToTransportDTOConverter;
import org.ibondi.core.service.TransportCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
