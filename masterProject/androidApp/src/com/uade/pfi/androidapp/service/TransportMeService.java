package com.uade.pfi.androidapp.service;

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

import com.uade.pfi.androidapp.server.ServerFacade;
import com.uade.pfi.api.dto.SessionCheckInDTO;
import com.uade.pfi.api.dto.SessionUpdateDto;

public class TransportMeService extends Service {
	private ServerFacade server;
	private String sessionId;
	
	
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


	public int onStartCommand(Intent intent, int flags, int startId) {
		String transportId = intent.getExtras().getString("transportId");
		SessionCheckInDTO dto = new SessionCheckInDTO();
		dto.setTransportId(transportId);
		sessionId = server.checkIn(dto);
		setupGPSProvider();
		Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
		return START_STICKY;
	};
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		server = ServerFacade.getInstace(this.getBaseContext());
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
		Double latitude = newLocation.getLatitude();
		Double longitude = newLocation.getLongitude();
		SessionUpdateDto dto = new SessionUpdateDto();
		dto.setSessionId(sessionId);
		dto.setLatitude(latitude);
		dto.setLongitude(longitude);
		server.postLocation(dto);
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		locationManager.removeUpdates(locationListener);
		Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
	}

}
