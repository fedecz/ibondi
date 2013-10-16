package org.ibondi.core.service;

import java.util.List;

import org.ibondi.api.enums.TransportTypeEnum;
import org.ibondi.core.beans.Transport;
import org.ibondi.core.repositories.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CRUD Service for Transport 
 *
 */
public class TransportCrudService implements CrudService<Transport> {
	
	@Autowired
	private TransportRepository repo;

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
	
	public void setRepo(TransportRepository repo) {
		this.repo = repo;
	}
	
	public TransportRepository getRepo() {
		return repo;
	}

	public List<Transport> getAllTransports() {
		return repo.findAll();
	}

	public Transport getTransportBy(String id) {
		return repo.findOne(id);
	}

	public List<Transport> getTransportsByName(String name) {
		return repo.findByName(name);
	}
	
	public List<Transport> getTransportsByType(String transportTypeName){
		TransportTypeEnum transportTypeBy = TransportTypeEnum.getTransportTypeBy(transportTypeName);
		return repo.findByTransportType(transportTypeBy);
	}
}
