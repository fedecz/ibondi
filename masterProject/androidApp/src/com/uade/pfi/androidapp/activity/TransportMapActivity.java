package com.uade.pfi.androidapp.activity;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.uade.pfi.androidapp.overlays.TransportItemizedOverlay;
import com.uade.pfi.androidapp.server.ServerFacade;
import com.uade.pfi.core.dto.TransportLocationDTO;
import com.uade.pfi.core.dto.TransportLocationListDTO;
import com.uadepfi.android.R;

public class TransportMapActivity extends MapActivity {

	private MapView mapView;
	private Drawable drawable;
	private List<Overlay> mapOverlays;
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
	protected void onStart() {
		super.onStart();
		server=ServerFacade.getInstace(this.getBaseContext());
		TransportLocationListDTO locations = getLocations();
		addLocationsToMap(locations);
	}


	private void addLocationsToMap(TransportLocationListDTO locations) {
		// Displays OverlayItems
		mapOverlays = mapView.getOverlays();
		if(drawable==null)
			drawable = this.getResources().getDrawable(R.drawable.iconbus);
		TransportItemizedOverlay itemizedOverlay = new TransportItemizedOverlay(drawable,getBaseContext());

		for (TransportLocationDTO location : locations.getTransports()) {
			GeoPoint point = generateGeoPoint(location);
			OverlayItem overlayitem = generateOverlayItem(location, point);
			itemizedOverlay.addOverlay(overlayitem);
		}
		mapOverlays.add(itemizedOverlay);
	}


	private OverlayItem generateOverlayItem(TransportLocationDTO location, GeoPoint point) {
		OverlayItem overlayitem = new OverlayItem(point, "transport",location.getTransportId());
		return overlayitem;
	}


	private GeoPoint generateGeoPoint(TransportLocationDTO location) {
		GeoPoint point = new GeoPoint((int)(location.getLatitude() * 1E6), (int)(location.getLongitude() * 1E6));
		return point;
	}
	
	private TransportLocationListDTO getLocations(){
		return server.getAllLocations();
	}

}