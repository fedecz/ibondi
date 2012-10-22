package com.uade.pfi.androidapp.activity;

import java.util.Arrays;
import java.util.List;

import android.graphics.drawable.Drawable;
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
import com.uade.pfi.androidapp.overlays.TransportItemizedOverlay;
import com.uade.pfi.androidapp.server.ServerFacade;
import com.uade.pfi.api.dto.LocationDTO;
import com.uade.pfi.api.dto.TransportLocationDTO;
import com.uade.pfi.api.dto.TransportLocationListDTO;
import com.uade.pfi.api.utils.MapsHelper;
import com.uadepfi.android.R;

public class TransportMapActivity extends MapActivity {

	private MapView mapView;
	private Drawable iconBus;
	private ServerFacade server;

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_map);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.show_map_refresh:
	            getNewTransportsAndSetupMap();
	            return true;
	        case R.id.show_map_filter:
	            Toast.makeText(this, "No implementado!!", Toast.LENGTH_SHORT).show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	private void getNewTransportsAndSetupMap() {
		mapView.getOverlays().clear();
		TransportLocationListDTO list = getLocations();
		MapController mapController = mapView.getController();
		if(list!=null && list.getTransports().length>0){
			addLocationsToMap(list);
			LocationDTO center = MapsHelper.getCenter(Arrays.asList(list.getTransports()));
			mapController.setCenter(generateGeoPoint(center));
			mapController.setZoom(15);
		}else{
			//TODO se deberia centrar en donde está el tipo parado, no en 0,0
			mapController.setCenter(generateGeoPoint(new LocationDTO(0,0)));
			mapController.setZoom(2);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.show_map_menu, menu);
	    return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		server=ServerFacade.getInstace(this.getBaseContext());
		getNewTransportsAndSetupMap();
	}


	private void addLocationsToMap(TransportLocationListDTO locations) {
		// Displays OverlayItems
		List<Overlay> overlays = mapView.getOverlays();
		if(iconBus==null)
			iconBus = this.getResources().getDrawable(R.drawable.iconbus);
		TransportItemizedOverlay itemizedOverlay = new TransportItemizedOverlay(iconBus,getBaseContext());

		for (TransportLocationDTO transportLocation : locations.getTransports()) {
			GeoPoint point = generateGeoPoint(transportLocation.getLocation());
			OverlayItem overlayitem = generateOverlayItem(transportLocation, point);
			itemizedOverlay.addOverlay(overlayitem);
		}
		overlays.add(itemizedOverlay);
	}


	private OverlayItem generateOverlayItem(TransportLocationDTO location, GeoPoint point) {
		OverlayItem overlayitem = new OverlayItem(point, "transport",location.getTransportId());
		return overlayitem;
	}


	private GeoPoint generateGeoPoint(LocationDTO dto) {
		GeoPoint point = new GeoPoint((int)(dto.getLatitude() * 1E6), (int)(dto.getLongitude() * 1E6));
		return point;
	}
	
	private TransportLocationListDTO getLocations(){
		try {
			return server.getAllLocations();
		} catch (Exception e) {
			return new TransportLocationListDTO();
		}
	}

}