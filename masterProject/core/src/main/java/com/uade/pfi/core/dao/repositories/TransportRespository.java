package com.uade.pfi.core.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uade.pfi.core.beans.Transport;

@Transactional
public interface TransportRespository extends MongoRepository<Transport, String> {
	
}
