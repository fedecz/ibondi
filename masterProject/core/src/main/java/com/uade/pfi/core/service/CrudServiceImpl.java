package com.uade.pfi.core.service;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.dao.CrudDao;

/**
 * CRUD Service for Transport and other elements 
 *
 */
public class CrudServiceImpl implements CrudService {
	
	private CrudDao crudDao;
	
	public void addTransport(Transport aNewTransport) {
		crudDao.insertTransport(aNewTransport);
	}

	public void updateTransport(Transport aNewTransport) {
		crudDao.updateTransport(aNewTransport);
	}

	public void removeTransport(Transport transportToBeRemoved) {
		crudDao.removeTransport(transportToBeRemoved);
	}

	public Transport getTransport(Transport transportExample) {
		return crudDao.getByExample(transportExample);
	}

	public void setCrudDao(CrudDao crudDao) {
		this.crudDao = crudDao;
	}

}
