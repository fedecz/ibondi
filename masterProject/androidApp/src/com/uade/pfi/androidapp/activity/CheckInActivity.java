/**
 * 
 */
package com.uade.pfi.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

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
		Spinner transportTypeSpinner = (Spinner) findViewById(R.id.transportType_spinner);
		Spinner lineSpinner = (Spinner) findViewById(R.id.line_spinner);
		Spinner branchSpinner = (Spinner) findViewById(R.id.branch_spinner);
		Spinner headingSpinner = (Spinner) findViewById(R.id.headingType_spinner);
		String transportType = (String) transportTypeSpinner.getSelectedItem();
		String line = (String) lineSpinner.getSelectedItem();
		String branch = (String) branchSpinner.getSelectedItem();
		String heading = (String) headingSpinner.getSelectedItem();
		
		Intent intent = new Intent(this,TransportMeService.class);
		intent.putExtra("transportType", transportType);
		intent.putExtra("line", line);
		intent.putExtra("branch", branch);
		intent.putExtra("heading", heading);
		CheckInActivity.this.startService(intent);
		CheckInActivity.this.finish();
	}

}
