/**
 * 
 */
package com.uade.pfi.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.uade.pfi.androidapp.service.TransportMeService;
import com.uadepfi.android.R;
/**
 * @author chiwi
 *
 */
public class CheckInActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
	}
	
	public void checkIn(View v){
		EditText transportIdTextbox = (EditText) findViewById(R.id.checkInTransportIdTxtBox);
		final String transportId = transportIdTextbox.getText().toString();
		Intent intent = new Intent(this,TransportMeService.class);
		intent.putExtra("transportId", transportId);
		CheckInActivity.this.startService(intent);
		CheckInActivity.this.finish();
	}

}
