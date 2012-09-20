package com.uade.pfi.core.service;

import com.uade.pfi.core.dao.CrudDao;
import com.uade.pfi.core.dto.TransportDTO;

/**
 * CRUD Service for Transport and other elements 
 *
 */
public class CrudServiceImpl implements CrudService {
	
	private CrudDao crudDao;
	
	public void addTransport(TransportDTO aNewTransport) {
		
		crudDao.insertTransport(aNewTransport);

	}

	public void updateTransport(TransportDTO aNewTransport) {
		
		crudDao.updateTransport(aNewTransport);

	}

	public void removeTransport(TransportDTO transportToBeRemoved) {

		crudDao.removeTransport(transportToBeRemoved);

	}

	public TransportDTO getTransport(TransportDTO transportExample) {
		TransportDTO transportDTO = crudDao.getByExample(transportExample);
		
		return transportDTO;
	}


}
