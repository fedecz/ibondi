/**
 * 
 */
package org.ibondi.core.repositories;

import org.ibondi.core.beans.TransportSession;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author chiwi
 *
 */
public interface SessionRepository extends MongoRepository<TransportSession, String>, CustomSessionRepository{

}
