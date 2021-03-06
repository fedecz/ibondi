package org.ibondi.core.repositories;

import java.util.List;

import org.ibondi.api.enums.TransportTypeEnum;
import org.ibondi.core.beans.Transport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportRepository extends MongoRepository<Transport, String> {
	
	/**
	 * Find transport lines by the name in mongo repository
	 */
	List<Transport> findByName(String name);
	
	/**
	 * Find transport list by type in mongo repo
	 */
	List<Transport> findByTransportType(TransportTypeEnum transportTypeEnum);
}
