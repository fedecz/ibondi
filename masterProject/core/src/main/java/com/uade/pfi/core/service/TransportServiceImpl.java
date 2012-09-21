package com.uade.pfi.core.service;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.dao.repositories.TransportRespository;

/**
 * CRUD Service for Transport 
 *
 */
public class TransportServiceImpl implements CrudService<Transport> {
	
	private TransportRespository repo;

	public void add(Transport transport) {
		repo.save(transport);
	}

	public void update(Transport transport) {
		repo.save(transport);
	}

	public void remove(Transport transport) {
		repo.delete(transport);
	}

	public Transport get(Transport transport) {
		return repo.findOne(transport.getId());
	}
}
