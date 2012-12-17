/**
 * 
 */
package com.uade.pfi.androidapp.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;

import com.uade.pfi.api.dto.LocationDTO;
import com.uade.pfi.api.dto.SessionCheckInDTO;
import com.uade.pfi.api.dto.SessionUpdateDto;
import com.uade.pfi.api.dto.TransportListDTO;
import com.uade.pfi.api.dto.TransportLocationListDTO;
import com.uade.pfi.api.dto.TransportTypeListDTO;
import com.uade.pfi.api.facade.MobileInterface;
import com.uadepfi.android.R;

/**
 * @author chiwi
 *
 */
public class ServerFacade implements MobileInterface {

	private static ServerFacade instance;
	private RestTemplate restTemplate;
	private Context context;
	private String BASE_URL;


	private ServerFacade(Context context){
		this.context = context;
		BASE_URL = context.getString(R.string.baseURL);
		restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application","json"));
	}

	public static ServerFacade getInstace(Context context){
		if(instance==null)
			return new ServerFacade(context);
		return instance;
	}


	/* (non-Javadoc)
	 * @see com.uade.pfi.core.facade.MobileInterface#checkIn(java.lang.String)
	 */
	@Override
	public String checkIn(SessionCheckInDTO checkIn) {
		String sessionId = restTemplate.postForObject(BASE_URL+context.getString(R.string.checkInURL), checkIn, String.class);
		return sessionId;
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.facade.MobileInterface#getAllLocations()
	 */
	@Override
	public TransportLocationListDTO getAllLocations() {
		TransportLocationListDTO result = restTemplate.getForObject(BASE_URL+context.getString(R.string.getAllLocationsFromServerURL), TransportLocationListDTO.class);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.facade.MobileInterface#getLocations(com.uade.pfi.core.dto.LocationDTO)
	 */
	@Override
	public TransportLocationListDTO getLocations(LocationDTO myLocation) {
		TransportLocationListDTO result = restTemplate.postForObject(BASE_URL+context.getString(R.string.getLocationsNearMeFromServerURL),myLocation, TransportLocationListDTO.class);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.facade.MobileInterface#postLocation(com.uade.pfi.core.dto.TransportLocationDTO)
	 */
	@Override
	public Boolean postLocation(SessionUpdateDto updateLocation) {
		return restTemplate.postForObject(BASE_URL+context.getString(R.string.postLocationToServerURL), updateLocation, Boolean.class);
	}

	@Override
	public TransportListDTO getTransportListBy(String transportType) {
		return restTemplate.postForObject(BASE_URL + context.getString(R.string.getTransportListByFromServerURL), transportType, TransportListDTO.class);
	}

	@Override
	public TransportTypeListDTO getTransportTypeList(String locale) {
		return restTemplate.postForObject(BASE_URL + context.getString(R.string.getTransportTypeListByFromServerURL), locale, TransportTypeListDTO.class);
	}
}
