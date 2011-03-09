package com.uade.pfi.androidapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.uade.pfi.androidapp.R;
import com.uade.pfi.androidapp.overlays.TransportItemizedOverlay;
import com.uade.pfi.core.dto.TransportLocation;

public class TransportMapActivity extends MapActivity {

	private MapView mapView;
	private String BASE_URL = null;
	private RestTemplate restTemplate;


	List<Overlay> mapOverlays;
	Drawable drawable;
	//TODO rename testItemizedOverlay
	TransportItemizedOverlay itemizedOverlay;


	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}	


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BASE_URL = this.getString(R.string.baseUrl);
		setContentView(R.layout.map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);



		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(acceptableMediaTypes);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		String url = BASE_URL + "/web/location/getList.json";

		restTemplate =  new RestTemplate();

		ResponseEntity<TransportLocation[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, TransportLocation[].class);
		TransportLocation[] result = responseEntity.getBody();



		// Displays OverlayItems
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.iconbus);
		itemizedOverlay = new TransportItemizedOverlay(drawable);

		for (int i = 0; i < result.length; i++) {
			TransportLocation t = result[i];

			GeoPoint point = new GeoPoint((int)(t.getLocation().getLatitude() * 1E6), (int)(t.getLocation().getLongitude() * 1E6));
			OverlayItem overlayitem = new OverlayItem(point, t.getName(), t.getName());
			itemizedOverlay.addOverlay(overlayitem);
		}
		mapOverlays.add(itemizedOverlay);
		// Displays OverlayItems

	}

	@Override
	protected void onStart() {
		super.onStart();



	}

}