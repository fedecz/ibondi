/**
 * 
 */
package com.uade.pfi.core.dao.criteria;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.uade.pfi.core.beans.TransportSession;

/**
 * @author chiwi
 *
 */
public class DefaultActiveSessionCriteriaStrategy implements
		ActiveSessionCriteriaStrategy {

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.dao.criteria.ActiveSessionCriteriaStrategy#getCriteria()
	 */
	public DetachedCriteria getCriteria() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, -10);
		return DetachedCriteria.forClass(TransportSession.class).add(Restrictions.gt("lastUpdated", calendar.getTime()));
	}

}
