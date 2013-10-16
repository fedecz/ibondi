package org.ibondi.core.utils;

import java.util.concurrent.TimeUnit;

public class TimeWatch {
	private long startWatch;
	private long stopWatch;

	private TimeWatch(){
		this.startWatch = System.currentTimeMillis();
	}
	
	public static TimeWatch start(){
		return new TimeWatch();
	}
	
	public TimeWatch stop() {
		this.stopWatch = System.currentTimeMillis();
		return this;
	}
	
	public Long getTimeElapsed(TimeUnit timeUnit){
		return timeUnit.convert(stopWatch - startWatch, TimeUnit.MILLISECONDS);
	}

}
