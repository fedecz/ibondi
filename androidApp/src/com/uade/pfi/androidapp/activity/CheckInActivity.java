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
				EditText transportName = (EditText) findViewById(R.id.transportName);
				Intent intent = new Intent("com.uade.pfi.transportMe.TRANSPORT_ME_SERVICE");
				intent.putExtra("transportName", transportName.getText().toString());
				CheckInActivity.this.startService(intent);
				CheckInActivity.this.finish();
			}
		});

	}

}
