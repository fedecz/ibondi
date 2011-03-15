/**
 * 
 */
package com.uade.pfi.core.dao.criteria;

import org.hibernate.criterion.DetachedCriteria;

/**
 * @author chiwi
 *
 */
public interface ActiveSessionCriteriaStrategy {

	DetachedCriteria getCriteria();

}
