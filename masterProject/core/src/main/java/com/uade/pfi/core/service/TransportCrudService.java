package com.uade.pfi.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.dao.repositories.TransportRespository;

/**
 * CRUD Service for Transport 
 *
 */
public class TransportCrudService implements CrudService<Transport> {
	
	@Autowired
	private TransportRespository repo;

	public Transport add(Transport transport) {
		return repo.save(transport);
	}

	public Transport update(Transport transport) {
		return repo.save(transport);
	}

	public void remove(Transport transport) {
		 repo.delete(transport);
	}

	public Transport get(Transport transport) {
		return repo.findOne(transport.getId());
	}
	
	public void setRepo(TransportRespository repo) {
		this.repo = repo;
	}
	
	public TransportRespository getRepo() {
		return repo;
	}
}
