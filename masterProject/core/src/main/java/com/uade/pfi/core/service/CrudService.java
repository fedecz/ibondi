package com.uade.pfi.core.service;

import com.uade.pfi.core.dto.TransportDTO;

public interface CrudService {
	
	void addTransport(TransportDTO aNewTransport);
	
	void updateTransport(TransportDTO aNewTransport);
	
	void removeTransport(TransportDTO transportToBeRemoved);
	
	TransportDTO getTransport(TransportDTO transportExample);
	
}
