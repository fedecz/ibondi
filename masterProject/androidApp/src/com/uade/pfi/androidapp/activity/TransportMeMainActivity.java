package com.uade.pfi.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.uade.pfi.androidapp.R;


public class TransportMeMainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button checkInButton = (Button) findViewById(R.id.checkInButton);
		checkInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMeMainActivity.this.startActivity(new Intent(getBaseContext(),CheckInActivity.class));
			}
		});
		Button checkOutButton = (Button) findViewById(R.id.checkOutButton);
		checkOutButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMeMainActivity.this.stopService(new Intent("com.uade.pfi.transportMe.TRANSPORT_ME_SERVICE"));
			}
		});
		
		Button mapButton = (Button) findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMeMainActivity.this.startActivity(new Intent(getBaseContext(),TransportMapActivity.class));
			}
		});
		
	}

}
