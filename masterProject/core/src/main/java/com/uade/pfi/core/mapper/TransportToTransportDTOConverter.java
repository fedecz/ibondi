package com.uade.pfi.core.mapper;

import com.uade.pfi.api.dto.TransportDTO;
import com.uade.pfi.core.beans.Transport;

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

}
