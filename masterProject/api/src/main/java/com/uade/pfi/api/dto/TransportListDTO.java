package com.uade.pfi.api.dto;

public class TransportListDTO {
	private TransportDTO[] list = new TransportDTO[0];
	
	public TransportListDTO() {
	}
	
	public TransportDTO[] getList() {
		return list;
	}
	public void setList(TransportDTO[] list) {
		this.list = list;
	}

}
