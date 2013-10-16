/**
 * 
 */
package org.ibondi.core.repositories.mapreduce;

import java.util.ArrayList;
import java.util.List;

import org.ibondi.core.beans.Location;
import org.ibondi.core.beans.TransportSession;
import org.ibondi.core.mapper.Converter;

/**
 * @author fedec
 *
 */
public class DefaultMapReduceSessionConverter implements
		Converter<MapReduceSessionOutput, List<TransportSession>> {

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.mapper.Converter#convert(java.lang.Object)
	 */
	public List<TransportSession> convert(MapReduceSessionOutput mapReduceOutput) {
		List<TransportSession> result = new ArrayList<TransportSession>(mapReduceOutput.value.items.size());
		String transportId = mapReduceOutput.id;
		for (MapReducePoint point : mapReduceOutput.value.items) {
			TransportSession s = new TransportSession(transportId, new Location(point.y, point.x));
			result.add(s);
		}
		return result;
	}

}
