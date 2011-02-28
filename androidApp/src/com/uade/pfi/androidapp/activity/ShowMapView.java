package com.uade.pfi.androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.uade.pfi.androidapp.R;

public class ShowMapView extends MapActivity {
	
	LinearLayout linearLayout;
	MapView mapView;
	ZoomControls zoomControls;

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("OnCreate");
        setContentView(R.layout.map);
        //linearLayout = (LinearLayout) findViewById(R.id.mainlayout);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        Toast.makeText(getApplicationContext(),mapView.getMapCenter().toString(), Toast.LENGTH_SHORT).show();
        //zoomControls = (ZoomControls) mapView.getZoomControls(); 
	}	
	
}