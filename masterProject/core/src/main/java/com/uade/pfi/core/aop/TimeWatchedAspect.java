/**
 * 
 */
package com.uade.pfi.core.aop;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.uade.pfi.core.utils.TimeWatch;

/**
 * @author fedec
 *
 */
@Aspect
public class TimeWatchedAspect {
	private Log logger = LogFactory.getLog(TimeWatchedAspect.class);

	@Around("@annotation(com.uade.pfi.core.aop.TimeWatched)")
	public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable  {
		TimeWatch time = TimeWatch.start();
		try {
			return joinPoint.proceed();
		} catch (IllegalArgumentException e) {
			throw e;
		} finally{
			time.stop();
			if(logger.isInfoEnabled())
				logger.info("Time " + joinPoint.getSignature().toShortString() + ": " + time.getTimeElapsed(TimeUnit.MILLISECONDS) + " ms");
		}
	}

}
