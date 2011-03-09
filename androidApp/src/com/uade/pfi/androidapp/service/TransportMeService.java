package com.uade.pfi.androidapp.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.uade.pfi.core.dto.TransportLocation;

public class TransportMeService extends Service {
	private String BASE_URL = "http://chiwi.homelinux.com:8080";
	
	private RestTemplate restTemplate;
	private HttpHeaders requestHeaders;
	
	
	private LocationManager locationManager;
	private LocationListener locationListener;
	private Listener listener = new Listener() {
		@Override
		public void onGpsStatusChanged(int event) {
			String msg =null;
			switch(event){
			case GpsStatus.GPS_EVENT_STARTED: msg = "GPS Started"; break;
			case GpsStatus.GPS_EVENT_STOPPED: msg = "GPS Stopped";break;
			case GpsStatus.GPS_EVENT_FIRST_FIX: msg = "GPS First Fix";
			}
			if(msg!=null)
				Toast.makeText(getApplicationContext(),msg , Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
		createRestTemplateAndHeader();
		setupGPSProvider();
		Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
	}

	private void setupGPSProvider() {
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location provider.
				makeUseOfNewLocation(location);
			}

			public void onProviderEnabled(String provider) {}

			public void onProviderDisabled(String provider) {}

			public void onStatusChanged(String provider, int status,Bundle extras) {}
		};

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 30, locationListener);
		locationManager.addGpsStatusListener(listener);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


	private void makeUseOfNewLocation(Location newLocation) {
		
		Float latitude = (float) (newLocation.getLatitude());
		Float longitud = (float) (newLocation.getLongitude());
		TransportLocation location = new TransportLocation(latitude, longitud, newLocation.getProvider());
		
		HttpEntity<TransportLocation> requestEntity = new HttpEntity<TransportLocation>(location, requestHeaders);
		
		ResponseEntity<Boolean> exchange = restTemplate.exchange(BASE_URL + "/web/location/post.json", HttpMethod.POST, requestEntity, Boolean.class);
		Toast.makeText(getApplicationContext(), "Location sent: " + exchange.getBody(), Toast.LENGTH_SHORT).show();
	}

	private void createRestTemplateAndHeader() {
		restTemplate = new RestTemplate();
		requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application","json"));
	}	


	@Override
	public void onDestroy() {
		super.onDestroy();
		locationManager.removeUpdates(locationListener);
		Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
	}

}
