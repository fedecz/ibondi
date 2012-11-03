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
 * @author luke.skywalker (Chewaca)
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

	private void configureLineSpinner() {
		getLineSpinner().setEnabled(false);
	}

	private void configureBranchSpinner() {
		getBranchSpinner().setEnabled(false);
	}

	private void configureHeadingSpinner() {
		getHeadingSpinner().setEnabled(false);
	}
	
	private void configureTransportTypeSpinner() {
		SpinnerAdapter arrayAdapter = getAdapterFor(aTransportTypeArray());
		OnItemSelectedListener transportTypeListener = createTransportTypeListener();
		enableSpinner(getTransportTypeSpinner(), arrayAdapter, transportTypeListener);
	}

	private OnItemSelectedListener createTransportTypeListener() {
		return new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	if (position == 0) {
		    		//Do nothing, it's the header
		    	} else {
					//Fire the other spinner population
		    		String transportType = (String)((Spinner)selectedItemView).getSelectedItem();
		    		enableLineSpinnerWithData(transportType);
		    	}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		};
	}

	protected void enableLineSpinnerWithData(String transportType) {
		SpinnerAdapter arrayAdapter = getAdapterFor(getTransportLineArray());
		OnItemSelectedListener selectionListener = createLineSelectionListener();
		enableSpinner(getLineSpinner(), arrayAdapter, selectionListener);
	}

	private void enableSpinner(Spinner spinner, SpinnerAdapter adapter, OnItemSelectedListener listener) {
		spinner.setEnabled(true);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(listener);
	}

	private OnItemSelectedListener createLineSelectionListener() {
		return new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	if (position == 0) {
		    		//Do nothing, it's the header
		    	} else {
					//Fire the other spinner population
		    		String selectedLine = (String)((Spinner)selectedItemView).getSelectedItem();
		    		enableBranchSpinnerWithData(selectedLine);
		    	}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		};
	}

	protected void enableBranchSpinnerWithData(String aSelectedLine) {
		SpinnerAdapter arrayAdapter = getAdapterFor(aBranchLineArrayFor(aSelectedLine));
		OnItemSelectedListener selectionListener = createBranchSelectionListener();
		enableSpinner(getBranchSpinner(), arrayAdapter, selectionListener);
	}

	private OnItemSelectedListener createBranchSelectionListener() {
		return new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	if (position == 0) {
		    		//Do nothing, it's the header
		    	} else {
					//Fire the other spinner population
		    		String selectedBranch = (String)((Spinner)selectedItemView).getSelectedItem();
		    		enableHeadingSpinnerWithData(selectedBranch);
		    	}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		};
	}

	protected void enableHeadingSpinnerWithData(String aSelectedBranch) {
		SpinnerAdapter arrayAdapter = getAdapterFor(aHeadingArrayFor(aSelectedBranch));
		enableSpinner(getHeadingSpinner(), arrayAdapter, null);
	}

	private SpinnerAdapter getAdapterFor(String[] transportTypes) {
		return new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, transportTypes);
	}

	private String[] aHeadingArrayFor(String aSelectedBranch) {
		// Ver como ir a buscar el servicio que nos trae esto
		return new String[] {"La Boca","Olivos"};
	}

	private String[] aBranchLineArrayFor(String selectedLine) {
		// TODO Ver como devolvemos esto
		return new String[] {"SR","UCA","Boca"};
	}

	private String[] getTransportLineArray() {
		// ver como corno ir a buscar los datos al servicio 
		return new String[]{"152","59"};
	}

	private String[] aTransportTypeArray() {
		// TODO Ver como ir a buscar los datos
		return new String[]{"Colectivo", "Tren"};
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
