package org.ibondi.androidapp.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MyLocationManager {
	private static Location currentBestLocation;
	
	public MyLocationManager(Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 50,new GPSLocationListener());
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 50,new NetworkLocationListener());
	}

	public synchronized static Location getCurrentBestLocation() {
		return currentBestLocation;
	}
	public synchronized static void setCurrentBestLocation(Location currentBestLocation) {
		MyLocationManager.currentBestLocation = currentBestLocation;
	}
	
	class GPSLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
//			if(LocationUtils.isBetterLocation(location, currentBestLocation)){
				setCurrentBestLocation(location);
//			}
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}}
	class NetworkLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
//			if(LocationUtils.isBetterLocation(location, currentBestLocation)){
				setCurrentBestLocation(location);
//			}
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}}
}
