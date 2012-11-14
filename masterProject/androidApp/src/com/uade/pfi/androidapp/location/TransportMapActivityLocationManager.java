package com.uade.pfi.androidapp.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class TransportMapActivityLocationManager {
	private LocationManager locationManager;
	private String provider;
	private LocationListener locationListener;


	public TransportMapActivityLocationManager(LocationListener locationListener) {
		this.locationListener = locationListener;
	}


	public void setupAndStartListening(Context context){
		// Get the location manager
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		// Initialize the location fields
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			locationListener.onLocationChanged(location);
		} 
	}
	

	public void resumeGettingLocations() {
		locationManager.requestLocationUpdates(provider, 5000, 10, locationListener);
	}


	public void stopGettingLocations() {
	    locationManager.removeUpdates(locationListener);
	}
}
