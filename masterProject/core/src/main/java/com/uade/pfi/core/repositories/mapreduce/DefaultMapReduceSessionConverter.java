/**
 * 
 */
package com.uade.pfi.core.repositories.mapreduce;

import java.util.ArrayList;
import java.util.List;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.mapper.Converter;

/**
 * @author fedec
 *
 */
public class DefaultMapReduceSessionConverter implements
		Converter<MapReduceSessionOutput, List<TransportSession>> {

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.mapper.Converter#convert(java.lang.Object)
	 */
	public List<TransportSession> convert(MapReduceSessionOutput value) {
		List<TransportSession> result = new ArrayList<TransportSession>(value.value.items.size());
		String transportId = value.id;
		for (MapReducePoint point : value.value.items) {
			TransportSession s = new TransportSession("0",transportId, new Location(point.x, point.y), new ArrayList<Location>(0), null);
			result.add(s);
		}
		return result;
	}

}
