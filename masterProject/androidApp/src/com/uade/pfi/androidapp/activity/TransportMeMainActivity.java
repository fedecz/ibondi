package com.uade.pfi.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uade.pfi.androidapp.service.TransportMeService;
import com.uadepfi.android.R;


public class TransportMeMainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
