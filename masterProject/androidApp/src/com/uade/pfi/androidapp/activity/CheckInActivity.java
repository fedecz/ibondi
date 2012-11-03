/**
 * 
 */
package com.uade.pfi.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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
		configureView();
	}
	
	private void configureView() {
		configureTransportTypeSpinner();
		configureLineSpinner();
		configureBranchSpinner();
		configureHeadingSpinner();
	}

	private void configureTransportTypeSpinner() {
		String[] transportTypeList = getTransportTypeArray();
		Spinner spinner = getTransportTypeSpinner();
		spinner.setAdapter(createTransportTypeSpinner(transportTypeList));
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	if (position == 0) {
		    		//Do nothing
		    	} else {
		    		//Fire the other spinner population
		    	}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		});
	}

	private String[] getTransportTypeArray() {
		// TODO Ver como ir a buscar los datos
		return new String[]{"Colectivo", "Tren"};
	}

	private SpinnerAdapter createTransportTypeSpinner(String[] transportTypes) {
		return new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, transportTypes);
	}

	
	
	private void configureLineSpinner() {
		// TODO Auto-generated method stub
		
	}

	private void configureBranchSpinner() {
		// TODO Auto-generated method stub
		
	}

	private void configureHeadingSpinner() {
		// TODO Auto-generated method stub
		
	}

	public void checkIn(View v){
		Spinner transportTypeSpinner = getTransportTypeSpinner();
		Spinner lineSpinner = getLineSpinner();
		Spinner branchSpinner = getBranchSpinner();
		Spinner headingSpinner = getHeadingSpinner();
		
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

	private Spinner getHeadingSpinner() {
		return (Spinner) findViewById(R.id.headingType_spinner);
	}

	private Spinner getBranchSpinner() {
		return (Spinner) findViewById(R.id.branch_spinner);
	}

	private Spinner getLineSpinner() {
		return (Spinner) findViewById(R.id.line_spinner);
	}

	private Spinner getTransportTypeSpinner() {
		return (Spinner) findViewById(R.id.transportType_spinner);
	}

}
