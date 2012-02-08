/**
 * 
 */
package com.uade.pfi.core.test.mocks;

import com.uade.pfi.core.dao.criteria.ActiveSessionCriteriaStrategy;

/**
 * @author chiwi
 *
 */
public class ActiveSessionStrategyCriteriaMock implements
		ActiveSessionCriteriaStrategy {

	public Object getCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.dao.criteria.ActiveSessionCriteriaStrategy#getCriteria()
	 */
//	public DetachedCriteria getCriteria() {
//		Calendar calendar = new GregorianCalendar();
//		calendar.add(Calendar.SECOND, -1);
//		return DetachedCriteria.forClass(TransportSession.class).add(Restrictions.gt("lastUpdated", calendar.getTime()));
//	}

}
