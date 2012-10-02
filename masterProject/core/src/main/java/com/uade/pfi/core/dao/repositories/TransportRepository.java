package com.uade.pfi.core.dao.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uade.pfi.core.beans.Transport;

@Transactional
public interface TransportRepository extends MongoRepository<Transport, String> {
	
	/**
	 * Find transport lines by the name in mongo repository
	 */
	List<Transport> findByName(String name);
	
}
