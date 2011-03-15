/**
 * 
 */
package com.uade.pfi.core.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.criteria.ActiveSessionCriteriaStrategy;

/**
 * @author chiwi
 *
 */
public class HibernateSessionDaoImpl extends HibernateDaoSupport implements SessionDao {
	
	private ActiveSessionCriteriaStrategy activeSessionCriteriaStrategy;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TransportSession> retrieveActiveSessions() {
		if(activeSessionCriteriaStrategy==null)
			return getHibernateTemplate().loadAll(TransportSession.class);
		DetachedCriteria criteria = activeSessionCriteriaStrategy.getCriteria();
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@Transactional
	public Long insert(TransportSession session) {
		Long id = (Long) getHibernateTemplate().save(session);
		return id;
	}
	
	@Transactional(readOnly=true)
	public TransportSession get(Long sessionId) {
		return (TransportSession) getHibernateTemplate().get(TransportSession.class, sessionId);
	}
	
	public void save(Location location) {
		getHibernateTemplate().save(location);
	}
	
	public ActiveSessionCriteriaStrategy getActiveSessionCriteriaStrategy() {
		return activeSessionCriteriaStrategy;
	}
	public void setActiveSessionCriteriaStrategy(
			ActiveSessionCriteriaStrategy activeSessionCriteriaStrategy) {
		this.activeSessionCriteriaStrategy = activeSessionCriteriaStrategy;
	}

	public void save(TransportSession session) {
		getHibernateTemplate().saveOrUpdate(session);
		getHibernateTemplate().flush();
	}

}
