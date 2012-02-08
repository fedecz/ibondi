/**
 * 
 */
package com.uade.pfi.core.dao.criteria;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.data.mongodb.core.query.Criteria;



/**
 * @author chiwi
 *
 */
public class DefaultActiveSessionCriteriaStrategy implements
		ActiveSessionCriteriaStrategy<Criteria> {

	public Criteria getCriteria() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, -10);
		return Criteria.where("lastUpdated").gt(calendar.getTime());
	}

}
