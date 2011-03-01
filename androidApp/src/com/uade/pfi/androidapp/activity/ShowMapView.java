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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.uade.pfi.androidapp.R;
import com.uade.pfi.androidapp.utils.DrawItemizedOverlay;
import com.uade.pfi.core.dto.TransportLocation;

public class ShowMapView extends MapActivity {
	
	LinearLayout linearLayout;
	MapView mapView;
	private String BASE_URL = "http://186.136.91.211:8080";
	
	
	List<Overlay> mapOverlays;
	Drawable drawable;
	//TODO rename testItemizedOverlay
	DrawItemizedOverlay itemizedOverlay;
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        
        
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(acceptableMediaTypes);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
	    //String url = BASE_URL + "/web/location/get.json";
		String url = BASE_URL + "/web/location/getList.json";
		
	    RestTemplate restTemplate =  new RestTemplate();
	    
	    //ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Integer.class);
	    //Integer result = responseEntity.getBody();
	    
	    ResponseEntity<List> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, List.class);
	    List<TransportLocation> result = responseEntity.getBody();
	   
	    System.out.println("Result: "+result.toString());
	    
	    
	    // Displays OverlayItems
	    mapOverlays = mapView.getOverlays();
	    drawable = this.getResources().getDrawable(R.drawable.icon);
	    itemizedOverlay = new DrawItemizedOverlay(drawable);
	    
	    
	    //TODO obtener un elemento del TransportLocation y crear un GeoPoint
	    GeoPoint point = new GeoPoint(19240000,-99120000);
	    OverlayItem overlayitem = new OverlayItem(point, "", "");
	    
	    itemizedOverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedOverlay);
	 // Displays OverlayItems
	    
	    
	    
        Button quitButton = (Button) findViewById(R.id.quitButton);
    	quitButton.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			 finish();
    		}
    	});
    	
	}	
}