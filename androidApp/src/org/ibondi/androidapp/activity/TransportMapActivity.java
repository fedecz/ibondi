package org.ibondi.androidapp.activity;

import java.util.List;

import org.ibondi.androidapp.location.MyLocationManager;
import org.ibondi.androidapp.overlays.TransportItemizedOverlay;
import org.ibondi.androidapp.overlays.WhereIAmOverlay;
import org.ibondi.androidapp.server.ServerFacade;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.uade.pfi.api.dto.LocationDTO;
import com.uade.pfi.api.dto.TransportLocationDTO;
import com.uade.pfi.api.dto.TransportLocationListDTO;
import com.uadepfi.android.R;

public class TransportMapActivity extends MapActivity {

	private MapView mapView;
	private Drawable iconBus;
	private Drawable myLocationIcon;
	private ServerFacade server;

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	/* STANDARD ACTIVITY LIFECYCLE METHODS */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_map);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		server = ServerFacade.getInstace(this.getBaseContext());
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateMap();
	}

	/* END STANDARD ACTIVITY LIFECYCLE METHODS */

	/* CONTEXT MENU METHODS */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.show_map_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.show_map_refresh:
			updateMap();
			return true;
		case R.id.show_map_filter:
			Toast.makeText(this, "No implementado!!", Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* END CONTEXT MENU METHODS */

	private void updateMap() {
		LocationDTO locDto = null;
		Location currentBestLocation = MyLocationManager.getCurrentBestLocation();
		if (currentBestLocation != null) {
			locDto = new LocationDTO(currentBestLocation.getLatitude(),
					currentBestLocation.getLongitude());
		} else {
			locDto = new LocationDTO(0, 0);
		}
		getNewTransportsAndSetupMap(locDto);
	}

	private void getNewTransportsAndSetupMap(LocationDTO myLocation) {
		mapView.getOverlays().clear();
		TransportLocationListDTO list = getLocations(myLocation);
		MapController mapController = mapView.getController();
		if (list != null && list.getTransports().length > 0) {
			addLocationsToMap(list);
		} 
		configureCenter(myLocation, mapView);
		mapController.setZoom(16);
	}

	private void configureCenter(LocationDTO myLocation, MapView mapView) {
		mapView.getController().setCenter(generateGeoPoint(myLocation));
		if (myLocationIcon == null)
			myLocationIcon = this.getResources().getDrawable(R.drawable.mylocation);
		GeoPoint whereIAmPoint = generateGeoPoint(myLocation);
		WhereIAmOverlay whereIAm = new WhereIAmOverlay(myLocationIcon, new OverlayItem(whereIAmPoint, "Here I am", "Here I Am"));
		mapView.getOverlays().add(whereIAm);
	}

	private void addLocationsToMap(TransportLocationListDTO locations) {
		// Displays OverlayItems
		List<Overlay> overlays = mapView.getOverlays();
		if (iconBus == null)
			iconBus = this.getResources().getDrawable(R.drawable.iconbus);
		
		TransportItemizedOverlay itemizedOverlay = new TransportItemizedOverlay(
				iconBus, getBaseContext());

		for (TransportLocationDTO transportLocation : locations.getTransports()) {
			GeoPoint point = generateGeoPoint(transportLocation.getLocation());
			OverlayItem overlayitem = generateOverlayItem(transportLocation,
					point);
			itemizedOverlay.addOverlay(overlayitem);
		}
		overlays.add(itemizedOverlay);
	}

	private OverlayItem generateOverlayItem(TransportLocationDTO location,
			GeoPoint point) {
		OverlayItem overlayitem = new OverlayItem(point, "transport",
				location.getTransportId());
		return overlayitem;
	}

	private GeoPoint generateGeoPoint(LocationDTO dto) {
		GeoPoint point = new GeoPoint((int) (dto.getLatitude() * 1E6),
				(int) (dto.getLongitude() * 1E6));
		return point;
	}

	private TransportLocationListDTO getLocations(LocationDTO myLocation) {
		try {
			return server.getLocations(myLocation);
		} catch (Exception e) {
			return new TransportLocationListDTO();
		}
	}

}