package com.uade.pfi.core.dao;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.dao.repositories.TransportRespository;

public class MongoDBCrudDao implements CrudDao {

	private TransportRespository transportRepository;
	
	
	public Transport insertTransport(Transport transportEntity) {
		return transportRepository.save(transportEntity);
		
	}

	public void updateTransport(Transport transportEntity) {
		transportRepository.save(transportEntity);
	}

	public void removeTransport(Transport transportEntity) {
		transportRepository.delete(transportEntity);
	}

	public Transport getByExample(Transport transportEntity) {
		return transportRepository.findOne(transportEntity.getId());
	}
	
	public void setTransportRepository(TransportRespository transportRepository) {
		this.transportRepository = transportRepository;
	}
	
	
}
