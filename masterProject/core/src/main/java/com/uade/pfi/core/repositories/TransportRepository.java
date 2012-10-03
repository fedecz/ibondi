package com.uade.pfi.core.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uade.pfi.core.beans.Transport;

public interface TransportRepository extends MongoRepository<Transport, String> {
	
	/**
	 * Find transport lines by the name in mongo repository
	 */
	List<Transport> findByName(String name);
	
}
