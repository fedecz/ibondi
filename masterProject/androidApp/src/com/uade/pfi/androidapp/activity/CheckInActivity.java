/**
 * 
 */
package com.uade.pfi.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.uade.pfi.androidapp.R;

/**
 * @author chiwi
 *
 */
public class CheckInActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkin);
		Button okButton = (Button) findViewById(R.id.okCheckInButton);
		okButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText transportNameTextbox = (EditText) findViewById(R.id.transportName);
				final String transportName = transportNameTextbox.getText().toString();
				Intent intent = new Intent("com.uade.pfi.transportMe.TRANSPORT_ME_SERVICE");
				intent.putExtra("transportName", transportName);
				CheckInActivity.this.startService(intent);
				CheckInActivity.this.finish();		
			}
		});

	}

}
