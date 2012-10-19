package com.uade.pfi.core.mapper;

import java.util.ArrayList;
import java.util.List;

import com.uade.pfi.api.dto.TransportLocationDTO;
import com.uade.pfi.core.beans.TransportSession;

public class TransportSessionToTransportLocationDTOConverter implements Converter<TransportSession,TransportLocationDTO>, ListConverter<TransportSession,TransportLocationDTO>{

	public TransportLocationDTO convert(TransportSession value) {
		TransportLocationDTO dto = new TransportLocationDTO();
		dto.setTransportId(value.getTransportId());
		if(value.getLastKnownLocation()!=null){
			dto.setLatitude(value.getLastKnownLocation().getLatitude());
			dto.setLongitude(value.getLastKnownLocation().getLongitude());
		}
		return dto;
	}

	public List<TransportLocationDTO> convert(List<TransportSession> sessions) {
		List<TransportLocationDTO> locations = new ArrayList<TransportLocationDTO>();
		for (TransportSession session : sessions) {
			locations.add(this.convert(session));
		}
		return locations;
	}

}
