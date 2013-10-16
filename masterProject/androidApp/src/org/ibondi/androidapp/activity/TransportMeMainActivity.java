package org.ibondi.androidapp.activity;

import org.ibondi.androidapp.location.MyLocationManager;
import org.ibondi.androidapp.service.TransportMeService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uadepfi.android.R;


public class TransportMeMainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new MyLocationManager(getApplicationContext());
		//TODO: en el onStart(), empezar a buscar la posicion actual ya sea
		//por gps o NETWORK
	}
	
	public void checkIn(View v){
		startActivity(new Intent(this,CheckInActivity.class));
	}
	
	public void checkOut(View v){
		stopService(new Intent(this, TransportMeService.class));
	}

	public void showMap(View v){
		startActivity(new Intent(this,TransportMapActivity.class));
	}

}
