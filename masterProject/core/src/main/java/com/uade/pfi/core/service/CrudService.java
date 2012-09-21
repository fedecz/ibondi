package com.uade.pfi.core.service;

import com.uade.pfi.core.beans.Transport;

public interface CrudService {
	
	void addTransport(Transport aNewTransport);
	
	void updateTransport(Transport aNewTransport);
	
	void removeTransport(Transport transportToBeRemoved);
	
	Transport getTransport(Transport transportExample);
	
}
