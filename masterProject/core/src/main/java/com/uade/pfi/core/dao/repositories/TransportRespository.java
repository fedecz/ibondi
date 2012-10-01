package com.uade.pfi.core.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uade.pfi.core.beans.Transport;

public interface TransportRespository extends MongoRepository<Transport, String> {
	
}
