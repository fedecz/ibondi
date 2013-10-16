package org.ibondi.core.mapper;

import java.util.ArrayList;
import java.util.List;

import org.ibondi.api.dto.TransportDTO;
import org.ibondi.core.beans.Transport;

public class TransportToTransportDTOConverter implements Converter<Transport, TransportDTO> {

	public TransportDTO convert(Transport t) {
		TransportDTO dto = new TransportDTO();
		dto.setId(t.getId());
		dto.setBranch(t.getBranch());
		dto.setHeading(t.getHeading().toString());
		dto.setName(t.getName());
		dto.setTransportType(t.getTransportType());
		return dto;
	}
	
	public List<TransportDTO> createTransportDTOListFrom(List<Transport> transports) {
		List<TransportDTO> listOfDTOs = new ArrayList<TransportDTO>(transports.size()); 
		for (Transport transport : transports) {
			listOfDTOs.add(convert(transport));
		}
		return listOfDTOs;
	}

}
