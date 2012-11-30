package com.uade.pfi.api.dto;

import java.util.List;

public class TransportListDTO {
	private TransportDTO[] list = new TransportDTO[0];
	
	public TransportListDTO() {
	}
	
	public TransportListDTO(List<TransportDTO> transportDTOs) {
		list = new TransportDTO[transportDTOs.size()];
		list = transportDTOs.toArray(list);
	}

	public TransportDTO[] getList() {
		return list;
	}
	public void setList(TransportDTO[] list) {
		this.list = list;
	}

}
