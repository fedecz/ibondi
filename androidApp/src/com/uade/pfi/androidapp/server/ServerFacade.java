/**
 * 
 */
package com.uade.pfi.androidapp.server;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.content.Context;

import com.uade.pfi.androidapp.R;
import com.uade.pfi.core.dto.LocationDTO;
import com.uade.pfi.core.dto.TransportLocationDTO;
import com.uade.pfi.core.facade.MobileInterface;

/**
 * @author chiwi
 *
 */
public class ServerFacade implements MobileInterface {

	private static ServerFacade instance;
	private RestTemplate restTemplate;
	private HttpHeaders requestHeaders;
	private Context context;
	private String BASE_URL;


	private ServerFacade(Context context){
		this.context = context;
		BASE_URL = context.getString(R.string.baseUrl);
		restTemplate = new RestTemplate();
		requestHeaders = new HttpHeaders();
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
	public String checkIn(String transportName) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(transportName, requestHeaders);
		ResponseEntity<String> exchange = restTemplate.exchange(BASE_URL + context.getString(R.string.checkIn), HttpMethod.POST, requestEntity, String.class);
		return exchange.getBody();
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.facade.MobileInterface#getAllLocations()
	 */
	@Override
	public TransportLocationDTO[] getAllLocations() {
		HttpEntity<TransportLocationDTO[]> requestEntity = new HttpEntity<TransportLocationDTO[]>(requestHeaders);
		ResponseEntity<TransportLocationDTO[]> exchange = restTemplate.exchange(BASE_URL + context.getString(R.string.getAllLocationsFromServer), HttpMethod.POST, requestEntity, TransportLocationDTO[].class);
		return exchange.getBody();
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.facade.MobileInterface#getLocations(com.uade.pfi.core.dto.LocationDTO)
	 */
	@Override
	public TransportLocationDTO[] getLocations(LocationDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uade.pfi.core.facade.MobileInterface#postLocation(com.uade.pfi.core.dto.TransportLocationDTO)
	 */
	@Override
	public Boolean postLocation(TransportLocationDTO location) {
		HttpEntity<TransportLocationDTO> requestEntity = new HttpEntity<TransportLocationDTO>(location, requestHeaders);
		ResponseEntity<Boolean> exchange = restTemplate.exchange(BASE_URL + context.getString(R.string.postLocationToServer), HttpMethod.POST, requestEntity, Boolean.class);
		return exchange.getBody();
	}

}
