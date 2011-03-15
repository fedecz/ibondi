/**
 * 
 */
package com.uade.pfi.core.test.mocks;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.criteria.ActiveSessionCriteriaStrategy;

/**
 * @author chiwi
 *
 */
public class ActiveSessionStrategyCriteriaMock implements
		ActiveSessionCriteriaStrategy {

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.dao.criteria.ActiveSessionCriteriaStrategy#getCriteria()
	 */
	public DetachedCriteria getCriteria() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.SECOND, -1);
		return DetachedCriteria.forClass(TransportSession.class).add(Restrictions.gt("lastUpdated", calendar.getTime()));
	}

}
