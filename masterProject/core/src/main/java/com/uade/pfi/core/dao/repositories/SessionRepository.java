/**
 * 
 */
package com.uade.pfi.core.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uade.pfi.core.beans.TransportSession;

/**
 * @author chiwi
 *
 */
public interface SessionRepository extends MongoRepository<TransportSession, String>, CustomSessionRepository{

}
