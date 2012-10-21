package com.uade.pfi.api.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.uade.pfi.api.dto.LocationDTO;
import com.uade.pfi.api.dto.TransportLocationDTO;

public class MapsHelper {

	public static LocationDTO getCenter(List<TransportLocationDTO> locations){
		if(locations==null || locations.size()==0){
			return new LocationDTO(0,0);
		}
			
		//Ordeno la lista de menor a mayor Longitude:
		Collections.sort(locations, new Comparator<TransportLocationDTO>() {
			public int compare(TransportLocationDTO p1, TransportLocationDTO p2) {
				if(p1.getLocation().getLongitude()>=p2.getLocation().getLongitude())
					return 1;
				return -1;
			}
		});

		TransportLocationDTO maxWestPoint = locations.get(0);
		TransportLocationDTO maxEastPoint = locations.get(locations.size() - 1);

		//Ordeno la lista de menor a mayor Latitude:
		Collections.sort(locations, new Comparator<TransportLocationDTO>() {
			public int compare(TransportLocationDTO p1, TransportLocationDTO p2) {
				if(p1.getLocation().getLatitude()>=p2.getLocation().getLatitude())
					return 1;
				return -1;
			}
		});

		TransportLocationDTO maxSouthPoint = locations.get(0);
		TransportLocationDTO maxNorthPoint = locations.get(locations.size() - 1);

		Float centerLat = (float) 0;
		Float centerLong = (float) 0;
		if(locations.size()>0){
			centerLat =  (maxSouthPoint.getLocation().getLatitude() + maxNorthPoint.getLocation().getLatitude()) / 2;
			centerLong = (maxWestPoint.getLocation().getLongitude() + maxEastPoint.getLocation().getLongitude()) / 2;
		}
		
		LocationDTO result = new LocationDTO();
		result.setLatitude(centerLat);
		result.setLongitude(centerLong);
		return result;
	}

}
